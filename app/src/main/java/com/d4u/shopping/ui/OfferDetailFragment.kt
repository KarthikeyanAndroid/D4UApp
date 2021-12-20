package com.d4u.shopping.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.d4u.shopping.BaseFragment
import com.d4u.shopping.R
import com.d4u.shopping.adapter.OfferDetailOtherListAdapter
import com.d4u.shopping.adapter.OfferListAdapter
import com.d4u.shopping.data.OfferDetailsResponse
import com.d4u.shopping.databinding.FragmentOfferDetailBinding
import com.d4u.shopping.interfaces.IItemClickListenrer
import com.d4u.shopping.viewmodel.CategoryViewModel
import com.oviesmarterware.ovie.utils.APIResult
import com.oviesmarterware.ovie.utils.CustomProgressDialog
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [OfferDetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class OfferDetailFragment : BaseFragment(), IItemClickListenrer {
    private lateinit var offerListAdapter: OfferListAdapter
    private lateinit var offerDetailOtherListAdapter: OfferDetailOtherListAdapter
    private lateinit var customProgressDialog: CustomProgressDialog

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var fragmentOfferBindingDetailFragment: FragmentOfferDetailBinding
    private var TAG: String = javaClass.name

    @Inject
    lateinit var categoryViewModel: CategoryViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val offerId = requireArguments().getString("offerId").toString()

        categoryViewModel.getOfferDetails(offerId)
        offerDetailResult()
        fragmentOfferBindingDetailFragment =
            FragmentOfferDetailBinding.inflate(inflater, container, false)
        fragmentOfferBindingDetailFragment.offerTxtview.setOnClickListener {
            fragmentOfferBindingDetailFragment.offerTxtview.background =
                ContextCompat.getDrawable(
                    requireActivity(),
                    R.drawable.selected_textview
                )

        }
        return fragmentOfferBindingDetailFragment.root
//        return inflater.inflate(R.layout.fragment_offer_detail, container, false)
    }

    private fun offerDetailResult() {

        categoryViewModel.offerDetailLiveData.observe(viewLifecycleOwner, {
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

                    it.data.let { data2 -> offerDetailData(data2) }


                }
            }
        })
    }

    private fun offerDetailData(data2: OfferDetailsResponse?) {

        val dt = SimpleDateFormat("yyyy-MM-dd")
        val date: Date = dt.parse(data2!!.offers.offers.validToDate)

        // *** same for the format String below

        // *** same for the format String below
        val dt1 = SimpleDateFormat("EEE, dd-MMM-yyyy")
        System.out.println(dt1.format(date))
        fragmentOfferBindingDetailFragment.txtviewTitle.text = data2!!.offers.offers.offerName
        fragmentOfferBindingDetailFragment.txtviewOfferValid.text = "Offer valid till " +
                dt1.format(date)
        if (data2 != null && data2.offers.offersCatelog != null && data2.offers.offersCatelog.size > 0) {
            lateinit
            var gridLayoutManager: GridLayoutManager
            fragmentOfferBindingDetailFragment.recyclerviewOtherCatlog.visibility = View.VISIBLE

            offerDetailOtherListAdapter =
                OfferDetailOtherListAdapter(data2!!.offers.offersCatelog, requireActivity(), this)
            gridLayoutManager = GridLayoutManager(
                requireActivity(), 2,
                RecyclerView.VERTICAL,
                false
            )
            fragmentOfferBindingDetailFragment.recyclerviewOtherCatlog.setLayoutManager(
                gridLayoutManager
            )

            // Set adapter on recycler view
            fragmentOfferBindingDetailFragment.recyclerviewOtherCatlog.setAdapter(
                offerDetailOtherListAdapter
            )
        } else {
            fragmentOfferBindingDetailFragment.recyclerviewOtherCatlog.visibility = View.GONE
        }
        if (data2.offers != null && data2.offers.otherOffers != null && data2.offers.otherOffers.size > 0) {
            fragmentOfferBindingDetailFragment.offerRelatedRecyclerview.visibility = View.VISIBLE

            offerListAdapter = OfferListAdapter(data2.offers.otherOffers, requireActivity(), this)
            var horizontalLayout = LinearLayoutManager(
                requireActivity(),
                LinearLayoutManager.HORIZONTAL,
                false
            )
            fragmentOfferBindingDetailFragment.offerRelatedRecyclerview.setLayoutManager(
                horizontalLayout
            )

            // Set adapter on recycler view

            // Set adapter on recycler view
            fragmentOfferBindingDetailFragment.offerRelatedRecyclerview.setAdapter(offerListAdapter)
        } else {
            fragmentOfferBindingDetailFragment.offerRelatedRecyclerview.visibility = View.GONE
        }
    }


    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment OfferDetailFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            OfferDetailFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_PARAM1, param1)
                    putString(ARG_PARAM2, param2)
                }
            }
    }

    override fun itemClick(pos: Int, view: View, catId: String, businessId: String, state: Int) {
    }
}