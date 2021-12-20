package com.d4u.shopping.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide

import com.d4u.shopping.interfaces.IItemClickListenrer
import com.oviesmarterware.ovie.utils.TimeUtils
import com.d4u.shopping.MainCateogryModels
import com.d4u.shopping.R
import com.d4u.shopping.data.BookMarkModel
import com.d4u.shopping.data.HomeProductList
import com.d4u.shopping.databinding.ListItemProductsBinding
import com.d4u.shopping.utils.Constant
import java.util.*


class ProductListItemAdapter(
        var list: ArrayList<HomeProductList>, var dbList: List<BookMarkModel>,
        var context: Context, var iItemClickListener: IItemClickListenrer
) :
        RecyclerView.Adapter<ProductListItemAdapter.ViewHolder>() {

    private val TAG: String = javaClass.simpleName
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val listItemProductsBinding =
                ListItemProductsBinding.inflate(LayoutInflater.from(context), parent, false)

        return ViewHolder(listItemProductsBinding, list, iItemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list.get(position)!!, dbList, context)
//        Log.i(TAG, "onBindViewHolder: " + Gson().toJson(list))
        for (r in 0 until list.size) {
            for (s in 0 until dbList.size) {
                if (list.get(r).customer_id.equals(dbList.get(s).customerId)) {
                    list.get(r).bookMarkSelected = true
                    holder.binding.imgviewBookmark.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_bookmark_icon))
                } else {
                    list.get(r).bookMarkSelected = false
                    holder.binding.imgviewBookmark.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_bookmark_icon_white))

                }

            }

        }/*
        for (databaseList in dbList) {
            if (data.customer_id.equals(databaseList.customerId)) {
                data.bookMarkSelected = true
                binding.imgviewBookmark.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_bookmark_icon))
            } else {
                data.bookMarkSelected = false
                binding.imgviewBookmark.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_bookmark_icon_white))

            }*/
    }


    fun addItems(postItems: ArrayList<HomeProductList>) {
        list.addAll(postItems!!)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder constructor(
            val binding: ListItemProductsBinding,
            var list: ArrayList<HomeProductList>, var iItemClickListener: IItemClickListenrer
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bind(data: HomeProductList, dbList: List<BookMarkModel>, context: Context) {


            binding.txtviewProductTitle.text = data.business_name
            binding.txtviewSubCateogry.text = data.business_name

            Glide.with(context).load(Constant.CUSTOMER_BASED_PRODUCT + data.image).into(binding.imgviewProduct)
            Glide.with(context).load(Constant.BUSINESS_LOGO_PATH + data.business_logo).into(binding.productImgview)
            binding.txtviewDaysLeft.setText(TimeUtils.getCountOfDays(data.created_at, TimeUtils.currentDT,
                    "dd-MM-yyyy HH:MM:SS"))
            binding.txtviewPages.text = data.total_pages
            binding.txtviewFollowerValue.text = data.total_viewer_cnt
            binding.imgviewBookmark.setOnClickListener(this)
            binding.cardviewProductItem.setOnClickListener(this)
            binding.imgviewBookmark.setOnClickListener {
                if (!data.bookMarkSelected) {
                    binding.imgviewBookmark.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_bookmark_icon))
                    iItemClickListener.bookMarkClick(list.get(adapterPosition), binding.imgviewBookmark)
                } else {
                    binding.imgviewBookmark.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_bookmark_icon_white))

                }
            }

            /*  binding.txtviewCateogryName.setOnClickListener(this)
                       binding.imgvtiewFavIcon.setOnClickListener(this)
                       binding.cardviewFeatureRecipe.setOnClickListener(this)*/
        }

        override fun onClick(view: View?) {
            when (view) {
                binding.cardviewProductItem -> {
                    iItemClickListener.itemClick(adapterPosition, view, list.get(adapterPosition).customer_id, "", 2)
                }


            }


        }


    }
}
