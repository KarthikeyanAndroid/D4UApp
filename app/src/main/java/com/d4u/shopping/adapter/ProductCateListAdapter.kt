package com.d4u.shopping.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.d4u.shopping.interfaces.IItemClickListenrer
import com.d4u.shopping.MainCateogryModels
import com.d4u.shopping.databinding.ListItemProductsBinding
import com.d4u.shopping.databinding.ListItemProductsCateogriesBinding
import java.util.*


class ProductCateListAdapter(
        var list: ArrayList<MainCateogryModels>,
        var context: Context, var iItemClickListener: IItemClickListenrer
) :
    RecyclerView.Adapter<ProductCateListAdapter.ViewHolder>() {

    private val TAG: String = javaClass.simpleName
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val listProductCateogryBinding =
            ListItemProductsCateogriesBinding.inflate(LayoutInflater.from(context), parent, false)



        return ViewHolder(listProductCateogryBinding, list, iItemClickListener)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list.get(position)!!, context)
//        Log.i(TAG, "onBindViewHolder: " + Gson().toJson(list))
    }

    fun addItems(postItems: ArrayList<MainCateogryModels>) {
        list.addAll(postItems!!)
        notifyDataSetChanged()
    }

    override fun getItemCount(): Int = list.size

    class ViewHolder constructor(
        val binding: ListItemProductsCateogriesBinding,
        var list: ArrayList<MainCateogryModels>, var iItemClickListener: IItemClickListenrer
    ) :

        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bind(data: MainCateogryModels, context: Context) {

            binding.txtviewProductTitle.text = data.cateogryName
            binding.imgviewProduct.setImageResource( data.offerImg)
            binding.productImgview.setImageResource(data.img)

            /*  binding.txtviewCateogryName.setOnClickListener(this)
               binding.imgvtiewFavIcon.setOnClickListener(this)
               binding.cardviewFeatureRecipe.setOnClickListener(this)*/
        }

        override fun onClick(view: View?) {
            when (view) {
            /*    binding.txtviewCateogryName -> {
                    iItemClickListener.itemClick(adapterPosition,view)
                }*/


            }


        }


    }
}
