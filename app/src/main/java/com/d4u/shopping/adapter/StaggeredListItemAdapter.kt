package com.d4u.shopping.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.d4u.shopping.interfaces.IItemClickListenrer
import com.d4u.shopping.MainCateogryModels
import com.d4u.shopping.databinding.ListCateogryItemBinding
import com.d4u.shopping.databinding.ListItemProductsBinding
import com.d4u.shopping.databinding.ListMainProductBinding
import java.util.*


class StaggeredListItemAdapter(
        var list: ArrayList<MainCateogryModels>,
        var context: Context, var iItemClickListener: IItemClickListenrer
) :
    RecyclerView.Adapter<StaggeredListItemAdapter.ViewHolder>() {

    private val TAG: String = javaClass.simpleName
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {

        val listItemProductsBinding =
            ListCateogryItemBinding.inflate(LayoutInflater.from(context), parent, false)



        return ViewHolder(listItemProductsBinding, list, iItemClickListener)
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
        val binding: ListCateogryItemBinding,
        var list: ArrayList<MainCateogryModels>, var iItemClickListener: IItemClickListenrer
    ) :

        RecyclerView.ViewHolder(binding.root), View.OnClickListener {
        fun bind(data: MainCateogryModels, context: Context) {

            binding.txtviewCateogryName.text = data.cateogryName

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
