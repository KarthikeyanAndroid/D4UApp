package com.d4u.shopping.ui.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.d4u.shopping.adapter.ProductListItemAdapter
import com.d4u.shopping.adapter.SubCateogryListAdapter
import com.d4u.shopping.adapter.SubCateogryWiseListAdapter
import com.d4u.shopping.interfaces.IItemClickListenrer
import com.google.gson.Gson
import com.oviesmarterware.ovie.utils.APIResult
import com.oviesmarterware.ovie.utils.CustomProgressDialog
import com.d4u.shopping.BaseFragment
import com.d4u.shopping.R
import com.d4u.shopping.adapter.RecyclerViewAdapter
import com.d4u.shopping.adapter.SearchIngredientAdapter
import com.d4u.shopping.data.*
import com.d4u.shopping.databinding.FragmentOffersBinding
import com.d4u.shopping.utils.Constant
import com.d4u.shopping.viewmodel.BookMarkViewModel
import com.d4u.shopping.viewmodel.CategoryViewModel
import com.d4u.shopping.viewmodel.CustomerViewModel
import javax.inject.Inject


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OffersFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OffersFragment : BaseFragment(), IItemClickListenrer {
    private var allOfferChecked: Boolean = true
    private lateinit var subcatWiseAdapter: SubCateogryWiseListAdapter
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private lateinit var customProgressDialog: CustomProgressDialog
    private lateinit var subCateogryListAdapter: SubCateogryListAdapter
    private lateinit var productListItemAdapter: ProductListItemAdapter
    private lateinit var mainCateogryAdapter: SearchIngredientAdapter
    private lateinit var horizontalLayout: LinearLayoutManager

    @Inject
    lateinit var bookMarkViewModel: BookMarkViewModel

    @Inject
    lateinit var customerViewModel: CustomerViewModel

    @Inject
    lateinit var categoryViewModel: CategoryViewModel

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var fragmentOfferBinding: FragmentOffersBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }

    }

    @Inject
    lateinit var bookListViewModel: BookMarkViewModel

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        fragmentOfferBinding =
                FragmentOffersBinding.inflate(inflater, container, false)
        fragmentOfferBinding.txtviewBookmarksShoppinglistOffers.setOnClickListener(View.OnClickListener {
            Navigation.findNavController(fragmentOfferBinding.txtviewBookmarksShoppinglistOffers)
                    .navigate(R.id.action_bottom_home_to_filterBottomSheetFragment);
        })
        fragmentOfferBinding.txtviewBookmarksOffers.setOnClickListener {
            Navigation.findNavController(fragmentOfferBinding.txtviewBookmarksShoppinglistOffers)
                    .navigate(R.id.action_bottom_home_to_nav_bookmarks);
        }

        if (isNetworkConnected()) {
            categoryViewModel.getHomeApiCall()
        }
        allOfferChecked = false
        homePageResult()
        getCatWiseProductResult()
        getBusinessWiseResult()
        getCustomerWiseOffer()

        fragmentOfferBinding.circleImgview.setOnClickListener {
            if (!allOfferChecked) {
                fragmentOfferBinding.circleImgview.setBackgroundColor(ContextCompat.getColor(requireActivity(), R.color.purple_800))
//                categoryViewModel.getCatWiseProductApi(Constant.CAT_ID)
                categoryViewModel.getHomeApiCall()
                allOfferChecked = true
            }
        }
        return fragmentOfferBinding.root
    }

    fun homePageResult() {
        categoryViewModel.categoryLiveData.observe(viewLifecycleOwner, {
            when (it.status) {

                APIResult.Status.LOADING -> {
                    customProgressDialog = CustomProgressDialog.show(requireActivity(), true, false)
                }
                APIResult.Status.ERROR -> {
                    Log.i(TAG, "customerResult: " + "Error")
                    customProgressDialog!!.dismiss()

                }
                APIResult.Status.SUCCESS -> {
                    customProgressDialog!!.dismiss()
                    Log.i(TAG, "homePageResult: ")
                    it.data.let { data -> if (data != null) categoryData(data) }
                    it.data2.let { data2 -> if (data2 != null) homeProductData(data2) }
                    it.data3.let { data3 -> if (data3 != null) customerListData(data3) }

                }
            }
        })
    }

    private fun categoryData(data: CategoryResModel?) {
        mainCateogry(data?.cateogryList!!)
        data.cateogryList
    }

    private fun getBusinessWiseResult() {
        categoryViewModel.categoryLiveData2.observe(viewLifecycleOwner, {
            when (it.status) {

                APIResult.Status.LOADING -> {
                    customProgressDialog = CustomProgressDialog.show(requireActivity(), true, false)
                }
                APIResult.Status.ERROR -> {
                    Log.i(TAG, "customerResult: " + "Error")
                    customProgressDialog!!.dismiss()

                }
                APIResult.Status.SUCCESS -> {
                    customProgressDialog!!.dismiss()



                    it.data2.let { data2 -> itemListProduct(data2?.offers!!.offers) }

                }
            }

        })
    }

    private fun getCustomerWiseOffer() {
        categoryViewModel.categoryLiveData3.observe(viewLifecycleOwner, {
            when (it.status) {

                APIResult.Status.LOADING -> {
                    customProgressDialog = CustomProgressDialog.show(requireActivity(), true, false)
                }
                APIResult.Status.ERROR -> {
                    Log.i(TAG, "customerResult: " + "Error")
                    customProgressDialog!!.dismiss()

                }
                APIResult.Status.SUCCESS -> {
                    customProgressDialog!!.dismiss()



                    it.data2.let { data2 -> itemListProduct(data2?.offers!!.offers) }

                }
            }

        })
    }

    private fun getCatWiseProductResult() {
        categoryViewModel.categoryLiveData1.observe(viewLifecycleOwner, {
            when (it.status) {

                APIResult.Status.LOADING -> {
                    customProgressDialog = CustomProgressDialog.show(requireActivity(), true, false)
                }
                APIResult.Status.ERROR -> {
                    Log.i(TAG, "customerResult: " + "Error")
                    customProgressDialog!!.dismiss()

                }
                APIResult.Status.SUCCESS -> {
                    customProgressDialog!!.dismiss()

                    it.data2.let { data2 -> homeProductData(data2) }


                }
            }

        })
    }

    /*
        fun customerResult() {
            customerViewModel.customerLiveData.observe(viewLifecycleOwner, Observer {

                when (it.status) {

                    APIResult.Status.LOADING -> {
                        customProgressDialog = CustomProgressDialog.show(requireActivity(), true, false)
    //                    showProgress(this)
                    }
                    APIResult.Status.ERROR -> {
                        Log.i(TAG, "customerResult: " + "Error")
                        customProgressDialog!!.dismiss()
    //                    hideProgress()

                    }
                    APIResult.Status.SUCCESS -> {
    //                    hideProgress()
                        customProgressDialog!!.dismiss()

                        it.data.let { data -> customerListData(data) }


                    }
                }
            })
        }
    */
    /*
    fun homeProductListResult() {
        categoryViewModel.homeProductLiveData.observe(viewLifecycleOwner, Observer {

            when (it.status) {

                APIResult.Status.LOADING -> {
                    customProgressDialog = CustomProgressDialog.show(requireActivity(), true, false)
//                    showProgress(this)
                }
                APIResult.Status.ERROR -> {
                    Log.i(TAG, "customerResult: " + "Error")
                    customProgressDialog!!.dismiss()
//                    hideProgress()

                }
                APIResult.Status.SUCCESS -> {
//                    hideProgress()
                    customProgressDialog!!.dismiss()

                    it.data.let { data -> homeProductData(data) }


                }
            }
        })
    }
*/

    private fun homeProductData(data: GetCatWiseHomeProductModel?) {

        itemListProduct(data!!)
    }

    fun itemListProduct(data: GetCatWiseHomeProductModel) {
        subCateogryCatWise(data.offers.business)
        itemListProduct(data.offers.offers)
    }

    private fun homeProductData(data: GetHomeProductModel?) {

        itemListProduct(data?.offers!!)
    }

    var TAG: String = javaClass.simpleName
    private fun customerListData(data: CustomerResponseModel?) {
        subCateogry(data?.customer!!)


        Log.i(TAG, "customerListData: " + Gson().toJson(data))
    }

    fun mainCateogry(categoryList: ArrayList<OfferCategory>) {

        recyclerViewAdapter = RecyclerViewAdapter(requireActivity(), categoryList, this)
        horizontalLayout = LinearLayoutManager(
                requireActivity(),
                LinearLayoutManager.HORIZONTAL,
                false
        )
        fragmentOfferBinding.recyclerviewMaincateogry.setLayoutManager(horizontalLayout)


        // Set adapter on recycler view
        fragmentOfferBinding.recyclerviewMaincateogry.setAdapter(recyclerViewAdapter)
    }

    fun subCateogryCatWise(businessList: ArrayList<BusinessList>) {
        if (businessList != null && businessList.size > 0) {
            subcatWiseAdapter = SubCateogryWiseListAdapter(businessList, requireActivity(), this)
            horizontalLayout = LinearLayoutManager(
                    requireActivity(),
                    LinearLayoutManager.HORIZONTAL,
                    false
            )
            fragmentOfferBinding.recyclerviewSubcateogry.setLayoutManager(horizontalLayout)


            // Set adapter on recycler view

            fragmentOfferBinding.recyclerviewSubcateogry.setAdapter(subcatWiseAdapter)
            fragmentOfferBinding.recyclerviewSubcateogry.visibility = View.VISIBLE

            fragmentOfferBinding.txtviewAllOffer.visibility = View.VISIBLE
            fragmentOfferBinding.circleImgview.visibility = View.VISIBLE

        } else {
            fragmentOfferBinding.recyclerviewSubcateogry.visibility = View.GONE
            fragmentOfferBinding.txtviewAllOffer.visibility = View.GONE
            fragmentOfferBinding.circleImgview.visibility = View.GONE
        }

    }

    fun subCateogry(customerArraylist: ArrayList<Customer>) {

        if (customerArraylist != null && customerArraylist.size > 0) {
            subCateogryListAdapter = SubCateogryListAdapter(customerArraylist, requireActivity(), this)
            horizontalLayout = LinearLayoutManager(
                    requireActivity(),
                    LinearLayoutManager.HORIZONTAL,
                    false
            )
            fragmentOfferBinding.recyclerviewSubcateogry.setLayoutManager(horizontalLayout)


            // Set adapter on recycler view

            fragmentOfferBinding.recyclerviewSubcateogry.setAdapter(subCateogryListAdapter)
            fragmentOfferBinding.recyclerviewSubcateogry.visibility = View.VISIBLE

            fragmentOfferBinding.txtviewAllOffer.visibility = View.VISIBLE
            fragmentOfferBinding.circleImgview.visibility = View.VISIBLE

        } else {
            fragmentOfferBinding.recyclerviewSubcateogry.visibility = View.GONE
            fragmentOfferBinding.txtviewAllOffer.visibility = View.GONE
            fragmentOfferBinding.circleImgview.visibility = View.GONE
        }
    }

    fun itemListProduct(homeProductList: ArrayList<HomeProductList>) {
        bookListViewModel.getAllBookmark().observe(viewLifecycleOwner, Observer { list ->

            Log.d(TAG, "updateBookList::observe")
            list.forEach {
                Log.d(TAG, "Book Name: ${it.businessLogo} - Book Price: ${it.businessName}")
            }
            if (list.isNotEmpty()) {
//                    if (list[0].bookCategoryID == selectedCategory?.categoryID)
            } else {

            }
            if (homeProductList != null && homeProductList.size > 0) {

                lateinit var gridLayoutManager: GridLayoutManager
                fragmentOfferBinding.recyclerviewCateogrylist.visibility = View.VISIBLE

                productListItemAdapter = ProductListItemAdapter(homeProductList, list, requireActivity(), this)
                gridLayoutManager = GridLayoutManager(
                        requireActivity(), 2,
                        RecyclerView.VERTICAL,
                        false
                )
                fragmentOfferBinding.recyclerviewCateogrylist.setLayoutManager(gridLayoutManager)

                // Set adapter on recycler view
                fragmentOfferBinding.recyclerviewCateogrylist.setAdapter(productListItemAdapter)
            } else {
                fragmentOfferBinding.recyclerviewCateogrylist.visibility = View.GONE
            }
            /*else
                    showBookList(listOf())*/
            Log.i(TAG, "onCreateView: " + list.size)
        })


    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OffersFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                OffersFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    override fun bookMarkClick(homeProductList: HomeProductList, view: View) {
        val bookMarkModel = BookMarkModel(homeProductList.offer_name, homeProductList.image, homeProductList.business_name, homeProductList.business_logo, homeProductList.total_pages, homeProductList.created_at, homeProductList.total_viewer_cnt, homeProductList.customer_id)

        bookMarkViewModel.addBookMark(bookMarkModel)
    }

    override fun itemClick(pos: Int, view: View, catId: String, businessId: String, stage: Int) {
        Log.i(TAG, "itemClick: " + catId)
        allOfferChecked = false

        Log.i(TAG, "itemClick: " + pos)
        if (stage == 0) {
            if (catId.isEmpty() && businessId.isEmpty()) {
                fragmentOfferBinding.recyclerviewSubcateogry.visibility = View.VISIBLE
                fragmentOfferBinding.recyclerviewCateogrylist.visibility = View.VISIBLE
                fragmentOfferBinding.circleImgview.visibility = View.VISIBLE
                fragmentOfferBinding.txtviewAllOffer.visibility = View.VISIBLE
                categoryViewModel.getHomeApiCall()
            } else if (catId.equals("unlike")) {
                fragmentOfferBinding.recyclerviewSubcateogry.visibility = View.GONE
                fragmentOfferBinding.recyclerviewCateogrylist.visibility = View.GONE
                fragmentOfferBinding.circleImgview.visibility = View.GONE
                fragmentOfferBinding.txtviewAllOffer.visibility = View.GONE
            } else if (businessId.isNotEmpty()) {
                categoryViewModel.getBusinessWise(catId, businessId)
                fragmentOfferBinding.circleImgview.setBackgroundColor(ContextCompat.getColor(requireActivity(), R.color.white))
            } else {

                categoryViewModel.getCatWiseProductApi(catId)
            }

        } else if (stage == 1) {
            categoryViewModel.getCustomerWiseProductId(businessId)
        } else if (stage == 2) {
            val bundle = bundleOf("offerId" to catId)
            Navigation.findNavController(view).navigate(R.id.action_bottom_home_to_offerDetailFragment, bundle)
        }

    }
}