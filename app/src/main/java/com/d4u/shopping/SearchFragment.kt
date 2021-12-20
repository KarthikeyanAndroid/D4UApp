package com.d4u.shopping

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.d4u.shopping.adapter.StaggeredListItemAdapter
import com.d4u.shopping.interfaces.IItemClickListenrer
import com.d4u.shopping.databinding.FragmentSearchBinding


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [SearchFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class SearchFragment : Fragment(), IItemClickListenrer {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    lateinit var searchBinding: FragmentSearchBinding
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
        searchBinding = FragmentSearchBinding.inflate(inflater, container, false)
        staggeredRecyclerview()
        return searchBinding.root
    }

    private fun staggeredRecyclerview() {
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
        val metrics = DisplayMetrics()
         requireActivity().getWindowManager().getDefaultDisplay().getMetrics(metrics)
        val cardWidth = metrics.xdpi.toInt() //CardWidth==1Inch.

        val spans = Math.floor((searchBinding.recyStaggerdProducts.context.getResources().getDisplayMetrics().widthPixels / cardWidth.toFloat()).toDouble()+1).toInt()
//        sglm.setSpanCount(spans)

        var staggeredGridLayoutManager = StaggeredGridLayoutManager(spans, LinearLayoutManager.VERTICAL)
        searchBinding.recyStaggerdProducts.layoutManager = staggeredGridLayoutManager
        var staggeredListItemAdapter = StaggeredListItemAdapter(mainCateogryModels, requireActivity(), this)
        searchBinding.recyStaggerdProducts.adapter = staggeredListItemAdapter
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment SearchFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                SearchFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }



    override fun itemClick(pos: Int, view: View, catId: String,businessId:String,stage:Int) {
    }
}