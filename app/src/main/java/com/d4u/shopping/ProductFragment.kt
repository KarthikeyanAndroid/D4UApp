package com.d4u.shopping

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.d4u.shopping.adapter.*
import com.d4u.shopping.interfaces.IItemClickListenrer
import com.d4u.shopping.databinding.FragmentProduct2Binding
import com.d4u.shopping.databinding.FragmentProductBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [ProductFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ProductFragment : Fragment(), IItemClickListenrer {
    private lateinit var productCateListAdapter: ProductCateListAdapter
    private lateinit var gridLayoutManager: GridLayoutManager
    private lateinit var subProductCateogryListAdapter: SubProductCateogryListAdapter
    private lateinit var mainProductListAdapter: MainProductListAdapter
    private lateinit var horizontalLayout: LinearLayoutManager

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var fragmentProductBinding: FragmentProduct2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentProductBinding = FragmentProduct2Binding.inflate(inflater, container, false)
        subCateogry()
        subCateogryItems()
        itemListProduct()
        fragmentProductBinding.searchviewProducts.setOnClickListener(View.OnClickListener {

            Navigation.findNavController(fragmentProductBinding.searchviewProducts)
                    .navigate(R.id.action_bottom_product_to_searchFragment);
        })
        return fragmentProductBinding.root/* inflater.inflate(R.layout.fragment_product2, container, false)*/
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ProductFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                ProductFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
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
        mainProductListAdapter = MainProductListAdapter(mainCateogryModels, requireActivity(), this)
        horizontalLayout = LinearLayoutManager(
                requireActivity(),
                LinearLayoutManager.HORIZONTAL,
                false
        )
        fragmentProductBinding.recyclerviewMainProducts.setLayoutManager(horizontalLayout)

        // Set adapter on recycler view

        // Set adapter on recycler view
        fragmentProductBinding.recyclerviewMainProducts.setAdapter(mainProductListAdapter)
    }



    fun subCateogryItems() {
        var mainCateogryModels = ArrayList<MainCateogryModels>()
        var mainCateogryModels1 = MainCateogryModels("Mobiles", true, R.drawable.product1,R.drawable.offer1)
        var mainCateogryModels2 = MainCateogryModels("Tv", true, R.drawable.product2,R.drawable.offer2)
        var mainCateogryModels3 = MainCateogryModels("Computers & Printers", true, R.drawable.product3,R.drawable.offer3)
        var mainCateogryModels4 = MainCateogryModels("Grocery", true, R.drawable.product4,R.drawable.offer4)
        var mainCateogryModels5 = MainCateogryModels("Baby Items", true, R.drawable.product5,R.drawable.offer5)
        var mainCateogryModels6 = MainCateogryModels("Gift", true, R.drawable.product3,R.drawable.offer3)
        mainCateogryModels.add(mainCateogryModels1)
        mainCateogryModels.add(mainCateogryModels2)
        mainCateogryModels.add(mainCateogryModels3)
        mainCateogryModels.add(mainCateogryModels4)
        mainCateogryModels.add(mainCateogryModels5)
        mainCateogryModels.add(mainCateogryModels6)
        subProductCateogryListAdapter = SubProductCateogryListAdapter(mainCateogryModels, requireActivity(), this)
        horizontalLayout = LinearLayoutManager(
                requireActivity(),
                LinearLayoutManager.HORIZONTAL,
                false
        )
        fragmentProductBinding.recyclerviewSubProducts.setLayoutManager(horizontalLayout)

        // Set adapter on recycler view

        // Set adapter on recycler view
        fragmentProductBinding.recyclerviewSubProducts.setAdapter(subProductCateogryListAdapter)
    }
    fun itemListProduct() {
        var mainCateogryModels = ArrayList<MainCateogryModels>()
        var mainCateogryModels1 = MainCateogryModels("All Offers", true, R.drawable.product1,R.drawable.offer1)
        var mainCateogryModels2 = MainCateogryModels("Electronic", true, R.drawable.product2,R.drawable.offer2)
        var mainCateogryModels3 = MainCateogryModels("Furniture", true, R.drawable.product3,R.drawable.offer3)
        var mainCateogryModels4 = MainCateogryModels("Grocery", true, R.drawable.product4,R.drawable.offer4)
        var mainCateogryModels5 = MainCateogryModels("Baby Items", true, R.drawable.product5,R.drawable.offer5)
        var mainCateogryModels6 = MainCateogryModels("Gift", true, R.drawable.product3,R.drawable.offer3)
        mainCateogryModels.add(mainCateogryModels1)
        mainCateogryModels.add(mainCateogryModels2)
        mainCateogryModels.add(mainCateogryModels3)
        mainCateogryModels.add(mainCateogryModels4)
        mainCateogryModels.add(mainCateogryModels5)
        mainCateogryModels.add(mainCateogryModels6)

        productCateListAdapter = ProductCateListAdapter(mainCateogryModels, requireActivity(), this)
        gridLayoutManager = GridLayoutManager(
                requireActivity(), 2,
                RecyclerView.VERTICAL,
                false
        )
        fragmentProductBinding.recyclerviewProductsList.setLayoutManager(gridLayoutManager)
//        productListItemAdapter.addItems(mainCateogryModels)
//        productListItemAdapter.notifyDataSetChanged()
        // Set adapter on recycler view

        // Set adapter on recycler view
        fragmentProductBinding.recyclerviewProductsList.setAdapter(productCateListAdapter)
    }




    override fun itemClick(pos: Int, view: View, catId: String,businessId:String,stage:Int) {
    }

}