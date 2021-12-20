package com.d4u.shopping

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.d4u.shopping.adapter.BookMarkProductListItemAdapter
import com.d4u.shopping.adapter.ProductListItemAdapter
import com.d4u.shopping.interfaces.IItemClickListenrer
import com.d4u.shopping.data.BookMarkModel
import com.d4u.shopping.databinding.FragmentBookMarkBinding
import com.d4u.shopping.viewmodel.BookMarkViewModel
import java.util.ArrayList
import javax.inject.Inject

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [BookMarkFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class BookMarkFragment : BaseFragment(), IItemClickListenrer {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null
    private var TAG: String = javaClass.simpleName

    @Inject
    lateinit var bookListViewModel: BookMarkViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    lateinit var fragmentBookMarkBinding: FragmentBookMarkBinding
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        fragmentBookMarkBinding = FragmentBookMarkBinding.inflate(layoutInflater, container, false)
        bookListViewModel.getAllBookmark().observe(viewLifecycleOwner, Observer { list ->

            Log.d(TAG, "updateBookList::observe")
            list.forEach {
                Log.d(TAG, "Book Name: ${it.businessLogo} - Book Price: ${it.businessName}")
            }
            if (list.isNotEmpty()) {
//                    if (list[0].bookCategoryID == selectedCategory?.categoryID)
                showBookList(list)
                fragmentBookMarkBinding.recyclerviewBookmark.visibility = View.VISIBLE
            } else {
                fragmentBookMarkBinding.recyclerviewBookmark.visibility = View.GONE
            }
            /*else
                    showBookList(listOf())*/
            Log.i(TAG, "onCreateView: " + list.size)
        })
        return fragmentBookMarkBinding.root
//        return inflater.inflate(R.layout.fragment_book_mark, container, false)
    }

    private fun showBookList(list: List<BookMarkModel>?) {

        var bookMarkProductListItemAdapter = BookMarkProductListItemAdapter(list!!, requireActivity(), this)

        var gridLayoutManager = GridLayoutManager(
                requireActivity(), 2,
                RecyclerView.VERTICAL,
                false
        )

        fragmentBookMarkBinding.recyclerviewBookmark.setLayoutManager(gridLayoutManager)

        // Set adapter on recycler view
        fragmentBookMarkBinding.recyclerviewBookmark.setAdapter(bookMarkProductListItemAdapter)
    }

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment BookMarkFragment.
         */
        // TODO: Rename and change types and number of parameters
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
                BookMarkFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    override fun itemClick(pos: Int, view: View, catId: String, businessId: String, state: Int) {


    }
}