package com.d4u.shopping.ui

import android.app.Activity
import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.oviesmarterware.ovie.utils.APIResult
import com.oviesmarterware.ovie.utils.CustomProgressDialog
import com.d4u.shopping.BaseFragment
import com.d4u.shopping.R
import com.d4u.shopping.data.CommonResponseModel
import com.d4u.shopping.data.FeedBackParamModel
import com.d4u.shopping.databinding.FragmentFeedBackBinding
import com.d4u.shopping.viewmodel.CategoryViewModel
import com.d4u.shopping.viewmodel.FeedBackViewModel
import javax.inject.Inject


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [FeedBackFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class FeedBackFragment : BaseFragment() {
    private lateinit var customProgressDialog: CustomProgressDialog

    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var feedBackBinding: FragmentFeedBackBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    @Inject
    lateinit var feedBackViewModel: FeedBackViewModel
    lateinit var feedBackParamModel: FeedBackParamModel
    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        feedBackBinding = FragmentFeedBackBinding.inflate(inflater, container, false)
        feedBackBinding.txtviewCorrectWrongInfo.setOnClickListener(View.OnClickListener {
            showDialog()
        })
        feedBackBinding.btnSubmit.setOnClickListener{
            val name = feedBackBinding.txtviewName.text.toString()
            val email = feedBackBinding.txtviewEmail.text.toString()
            val details = feedBackBinding.txtviewDetails.text.toString()
            val phone = feedBackBinding.txtviewPhone.text.toString()
            if (validateEditValue(feedBackBinding.txtviewName, "Enter the name")) {
                if (validateEditValue(feedBackBinding.txtviewEmail, "Enter the mail")) {
                    if (validateEditValue(feedBackBinding.txtviewPhone, "Enter the phone number")) {
                        if (validateEditValue(feedBackBinding.txtviewDetails, "Enter the details")) {
                            feedBackParamModel = FeedBackParamModel(name, phone, email, details)
                            feedBackViewModel.getFeedBackApi(feedBackParamModel)
                            feedBackViewModel.feedbackLiveData.observe(viewLifecycleOwner, {
                                feedBackViewModel.feedbackLiveData.observe(viewLifecycleOwner, {
                                    when (it.status) {

                                        APIResult.Status.LOADING -> {
                                            customProgressDialog = CustomProgressDialog.show(requireActivity(), true, false)
//                    showProgress(this)
                                        }
                                        APIResult.Status.ERROR -> {
//                                        Log.i(TAG, "customerResult: " + "Error")
                                            customProgressDialog!!.dismiss()
//                    hideProgress()

                                        }
                                        APIResult.Status.SUCCESS -> {
//                    hideProgress()
                                            customProgressDialog!!.dismiss()
                                            it.data.let { data -> if (data != null) feedback(data) }
                                            /*it.data2.let { data2 -> if (data2 != null) homeProductData(data2) }
                                        it.data3.let { data3 -> if (data3 != null) customerListData(data3) }
*/
                                        }
                                    }
                                })

                            })
                        }
                    }
                }
            }
        }
        // Inflate the layout for this fragment
        return feedBackBinding.root
    }

    private fun feedback(data: CommonResponseModel) {

        Toast.makeText(requireActivity(), data.msg, Toast.LENGTH_SHORT).show()
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment FeedBackFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                FeedBackFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }


    fun showDialog() {
        val dialog = Dialog(requireActivity())
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.setContentView(R.layout.alert_dialog_feedback)

        /* val dialogButton: Button = dialog.findViewById(R.id.btn_dialog) as Button
         dialogButton.setOnClickListener(View.OnClickListener { dialog.dismiss() })*/
        dialog.show()
    }
}