package com.d4u.shopping.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.d4u.shopping.interfaces.IItemClickListenrer
import com.oviesmarterware.ovie.utils.TimeUtils
import com.d4u.shopping.MainCateogryModels
import com.d4u.shopping.data.BookMarkModel
import com.d4u.shopping.data.HomeProductList
import com.d4u.shopping.databinding.ListItemProductsBinding
import com.d4u.shopping.utils.Constant
import java.util.*


class BookMarkProductListItemAdapter(
        var list: List<BookMarkModel>,
        var context: Context, var iItemClickListener: IItemClickListenrer
) :
        RecyclerView.Adapter<BookMarkProductListItemAdapter.ViewHolder>() {

    private val TAG: String = javaClass.simpleName
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val listItemProductsBinding =
                ListItemProductsBinding.inflate(LayoutInflater.from(context), parent, false)



        return ViewHolder(listItemProductsBinding, list, iItemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list.get(position)!!, context)
//        Log.i(TAG, "onBindViewHolder: " + Gson().toJson(list))
    }

    fun addItems(postItems: ArrayList<BookMarkModel>) {
//        list.addAll(postItems!!)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder constructor(
            val binding: ListItemProductsBinding,
            var list: List<BookMarkModel>, var iItemClickListener: IItemClickListenrer
    ) :

            RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bind(data: BookMarkModel, context: Context) {

            binding.txtviewProductTitle.text = data.businessName
            binding.txtviewSubCateogry.text = data.offerName
            Glide.with(context).load(Constant.CUSTOMER_BASED_PRODUCT + data.offerImage).into(binding.imgviewProduct)
            Glide.with(context).load(Constant.BUSINESS_LOGO_PATH + data.businessLogo).into(binding.productImgview)
            binding.txtviewDaysLeft.setText(TimeUtils.getCountOfDays(data.createdAt, TimeUtils.currentDT,
                    "dd-MM-yyyy HH:MM:SS"))
            binding.txtviewPages.text = data.totalPages
            binding.txtviewFollowerValue.text = data.totalViewerCount
            binding.imgviewBookmark.setOnClickListener(this)
            binding.cardviewProductItem.setOnClickListener(this)
            /*  binding.txtviewCateogryName.setOnClickListener(this)
                   binding.imgvtiewFavIcon.setOnClickListener(this)
                   binding.cardviewFeatureRecipe.setOnClickListener(this)*/
        }

        override fun onClick(view: View?) {
            when (view) {
                binding.cardviewProductItem -> {
                    iItemClickListener.itemClick(adapterPosition, view, "", "",4)
                }
                binding.imgviewBookmark -> {
//                    iItemClickListener.bookMarkClick(list.get(adapterPosition), binding.imgviewBookmark)
                }


            }


        }


    }
}
