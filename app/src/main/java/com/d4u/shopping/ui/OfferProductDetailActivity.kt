package com.d4u.shopping.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.d4u.shopping.adapter.MainProductListAdapter
import com.d4u.shopping.adapter.OfferDetailCateListAdapter
import com.d4u.shopping.adapter.ProductCateListAdapter
import com.d4u.shopping.adapter.SubProductCateogryListAdapter
import com.d4u.shopping.interfaces.IItemClickListenrer
import com.d4u.shopping.MainCateogryModels
import com.d4u.shopping.R
import com.d4u.shopping.databinding.ActivityOfferProductDetailBinding

class OfferProductDetailActivity : AppCompatActivity(), IItemClickListenrer, View.OnClickListener {
    private lateinit var offerDetailCatListAdapter: OfferDetailCateListAdapter
    private lateinit var subProductCateogryListAdapter: SubProductCateogryListAdapter
    private lateinit var productCateListAdapter: ProductCateListAdapter
//    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var horizontalLayout: LinearLayoutManager
    private lateinit var mainProductListAdapter: MainProductListAdapter
    lateinit var offerProductDetailBinding: ActivityOfferProductDetailBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        offerProductDetailBinding = ActivityOfferProductDetailBinding.inflate(layoutInflater)
        setContentView(offerProductDetailBinding.root)
        subCateogry()
        subCateogryItems()
        itemListProduct()
        offerProductDetailBinding.imgviewBackBtn.setOnClickListener(this)
        offerProductDetailBinding.txtviewOffer.setOnClickListener(this)
        offerProductDetailBinding.txtviewProducts.setOnClickListener(this)
    }

    fun subCateogry() {
        var mainCateogryModels = ArrayList<MainCateogryModels>()
        var mainCateogryModels1 = MainCateogryModels("Electronics", true, R.drawable.electronics, R.drawable.offer1)
        var mainCateogryModels2 = MainCateogryModels("Food", true, R.drawable.diet, R.drawable.offer2)
        var mainCateogryModels3 = MainCateogryModels("Department Store", true, R.drawable.department, R.drawable.offer3)
        var mainCateogryModels4 = MainCateogryModels("Other items", true, R.drawable.cleaning, R.drawable.offer4)
        var mainCateogryModels5 = MainCateogryModels("Philips", true, R.drawable.product5, R.drawable.offer5)
        var mainCateogryModels6 = MainCateogryModels("Whirlpool", true, R.drawable.product2, R.drawable.offer3)
        mainCateogryModels.add(mainCateogryModels1)
        mainCateogryModels.add(mainCateogryModels2)
        mainCateogryModels.add(mainCateogryModels3)
        mainCateogryModels.add(mainCateogryModels4)
//        mainCateogryModels.add(mainCateogryModels6)
        mainProductListAdapter = MainProductListAdapter(mainCateogryModels, this, this)
        horizontalLayout = LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
        )
        offerProductDetailBinding.includeProduct.recyclerviewMainProducts.setLayoutManager(horizontalLayout)

        // Set adapter on recycler view

        // Set adapter on recycler view
        offerProductDetailBinding.includeProduct.recyclerviewMainProducts.setAdapter(mainProductListAdapter)
    }


    fun subCateogryItems() {
        var mainCateogryModels = ArrayList<MainCateogryModels>()
        var mainCateogryModels1 = MainCateogryModels("Mobiles", true, R.drawable.product1, R.drawable.offer1)
        var mainCateogryModels2 = MainCateogryModels("Tv", true, R.drawable.product2, R.drawable.offer2)
        var mainCateogryModels3 = MainCateogryModels("Computers & Printers", true, R.drawable.product3, R.drawable.offer3)
        var mainCateogryModels4 = MainCateogryModels("Grocery", true, R.drawable.product4, R.drawable.offer4)
        var mainCateogryModels5 = MainCateogryModels("Baby Items", true, R.drawable.product5, R.drawable.offer5)
        var mainCateogryModels6 = MainCateogryModels("Gift", true, R.drawable.product3, R.drawable.offer3)
        mainCateogryModels.add(mainCateogryModels1)
        mainCateogryModels.add(mainCateogryModels2)
        mainCateogryModels.add(mainCateogryModels3)
        mainCateogryModels.add(mainCateogryModels4)
        mainCateogryModels.add(mainCateogryModels5)
        mainCateogryModels.add(mainCateogryModels6)
        subProductCateogryListAdapter = SubProductCateogryListAdapter(mainCateogryModels, this, this)
        horizontalLayout = LinearLayoutManager(
                this,
                LinearLayoutManager.HORIZONTAL,
                false
        )
        offerProductDetailBinding.includeProduct.recyclerviewSubProducts.setLayoutManager(horizontalLayout)

        // Set adapter on recycler view

        // Set adapter on recycler view
        offerProductDetailBinding.includeProduct.recyclerviewSubProducts.setAdapter(subProductCateogryListAdapter)
    }

    fun itemListProduct() {
        var mainCateogryModels = ArrayList<MainCateogryModels>()
        var mainCateogryModels1 = MainCateogryModels("All Offers", true, R.drawable.product1, R.drawable.offer1)
        var mainCateogryModels2 = MainCateogryModels("Electronic", true, R.drawable.product2, R.drawable.offer2)
        var mainCateogryModels3 = MainCateogryModels("Furniture", true, R.drawable.product3, R.drawable.offer3)
        var mainCateogryModels4 = MainCateogryModels("Grocery", true, R.drawable.product4, R.drawable.offer4)
        var mainCateogryModels5 = MainCateogryModels("Baby Items", true, R.drawable.product5, R.drawable.offer5)
        var mainCateogryModels6 = MainCateogryModels("Gift", true, R.drawable.product3, R.drawable.offer3)
        mainCateogryModels.add(mainCateogryModels1)
        mainCateogryModels.add(mainCateogryModels2)
        mainCateogryModels.add(mainCateogryModels3)
        mainCateogryModels.add(mainCateogryModels4)
        mainCateogryModels.add(mainCateogryModels5)
        mainCateogryModels.add(mainCateogryModels6)
         lateinit var offergridLayoutManager: GridLayoutManager

        /* productCateListAdapter = ProductCateListAdapter(mainCateogryModels, this, this)
         gridLayoutManager = GridLayoutManager(
                 this, 2,
                 RecyclerView.VERTICAL,
                 false
         )*/
        offerDetailCatListAdapter = OfferDetailCateListAdapter(mainCateogryModels, this, this)
        offergridLayoutManager = GridLayoutManager(
                this, 2,
                RecyclerView.VERTICAL,
                false
        )
        offerProductDetailBinding.includeProduct.recyclerviewProductsList.setLayoutManager(offergridLayoutManager)
//        offerProductDetailBinding.recyclerviewSubOffers.setLayoutManager(offergridLayoutManager)
//        productListItemAdapter.addItems(mainCateogryModels)
//        productListItemAdapter.notifyDataSetChanged()
        // Set adapter on recycler view

        // Set adapter on recycler view
        offerProductDetailBinding.includeProduct.recyclerviewProductsList.setAdapter(offerDetailCatListAdapter)
//        offerProductDetailBinding.recyclerviewSubOffers.setAdapter(offerDetailCatListAdapter)
    }



    override fun onClick(view: View?) {
        when (view) {
            offerProductDetailBinding.imgviewBackBtn -> {
                finish()
            }
            offerProductDetailBinding.txtviewOffer -> {
                offerProductDetailBinding.txtviewProducts.setBackgroundResource((R.color.purple_800))

                offerProductDetailBinding.txtviewOffer.setBackgroundResource((R.drawable.drawable_bottom_line))
                offerProductDetailBinding.consOffer.visibility = View.VISIBLE;
                offerProductDetailBinding.includeProduct.nestedScrollOffers.visibility = View.GONE;
            }
            offerProductDetailBinding.txtviewProducts -> {
                offerProductDetailBinding.txtviewProducts.setBackgroundResource((R.drawable.drawable_bottom_line))

                offerProductDetailBinding.txtviewOffer.setBackgroundResource((R.color.purple_800))
                offerProductDetailBinding.consOffer.visibility = View.GONE;
                offerProductDetailBinding.includeProduct.nestedScrollOffers.visibility = View.VISIBLE;


            }
        }

    }

    override fun itemClick(pos: Int, view: View, catId: String,businessId:String,stage:Int) {
        TODO("Not yet implemented")
    }

}