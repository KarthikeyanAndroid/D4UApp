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
import com.d4u.shopping.data.OfferDetailsResponse
import com.d4u.shopping.data.OffersCatelog
import com.d4u.shopping.databinding.ListItemOtherOfferBinding
import com.d4u.shopping.databinding.ListItemProductsBinding
import com.d4u.shopping.utils.Constant
import java.util.*


class OfferDetailOtherListAdapter(
    var list: ArrayList<OffersCatelog>,
    var context: Context, var iItemClickListener: IItemClickListenrer
) :
    RecyclerView.Adapter<OfferDetailOtherListAdapter.ViewHolder>() {

    private val TAG: String = javaClass.simpleName
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val listItemProductsBinding =
            ListItemOtherOfferBinding.inflate(LayoutInflater.from(context), parent, false)

        return ViewHolder(listItemProductsBinding, list, iItemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list.get(position)!!, context)
    }


    fun addItems(postItems: ArrayList<OffersCatelog>) {
        list.addAll(postItems!!)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder constructor(
        val binding: ListItemOtherOfferBinding,
        var list: ArrayList<OffersCatelog>, var iItemClickListener: IItemClickListenrer
    ) : RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bind(data: OffersCatelog, context: Context) {


            binding.txtviewCount.text = adapterPosition.plus(1).toString()

            Glide.with(context).load(Constant.CUSTOMER_BASED_PRODUCT + data.image)
                .into(binding.imgviewProduct)


//            binding.cardviewProductItem.setOnClickListener(this)

            /*  binding.txtviewCateogryName.setOnClickListener(this)
                       binding.imgvtiewFavIcon.setOnClickListener(this)
                       binding.cardviewFeatureRecipe.setOnClickListener(this)*/
        }

        override fun onClick(view: View?) {
            when (view) {
                binding.cardviewProductItem -> {
                    /*  iItemClickListener.itemClick(
                          adapterPosition,
                          view,
                          list.get(adapterPosition).customer_id,
                          "",
                          2
                      )*/
                }


            }


        }


    }
}
