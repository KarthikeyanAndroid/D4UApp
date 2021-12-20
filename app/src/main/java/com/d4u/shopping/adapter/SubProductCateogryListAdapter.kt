package com.d4u.shopping.adapter

import android.content.Context
import android.util.Log
import android.util.Log.i
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView

import com.d4u.shopping.interfaces.IItemClickListenrer
import com.d4u.shopping.MainCateogryModels
import com.d4u.shopping.R
import com.d4u.shopping.databinding.LikeItemBinding
import com.d4u.shopping.databinding.ListCateogryItemBinding
import java.util.*


class SubProductCateogryListAdapter(
        var list: ArrayList<MainCateogryModels>,
        var context: Context, var iItemClickListener: IItemClickListenrer
) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val TYPE_HEADER: Int = 0
    private val TYPE_LIST: Int = 1
    private val TAG: String = javaClass.simpleName
    private var backGroundDrawable: IntArray = intArrayOf(R.drawable.gradient1_color, R.drawable.gradient2_color, R.drawable.gradient3_color)
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_HEADER) {
            val likeItemBinding = LikeItemBinding.inflate(LayoutInflater.from(context), parent, false)
            return VHeader(likeItemBinding)
        } else {
            val listHomeGridBinding =
                    ListCateogryItemBinding.inflate(LayoutInflater.from(context), parent, false)



            return ViewHolder(listHomeGridBinding, list, iItemClickListener, backGroundDrawable)
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        when (holder) {
            is ViewHolder -> {
                holder.bind(list.get(position)!!, context, position)
            }


        }
//        holder.bind(list.get(position)!!, context)
//        Log.i(TAG, "onBindViewHolder: " + G`son().toJson(list))
    }

    fun addItems(postItems: ArrayList<MainCateogryModels>) {
        list.addAll(postItems!!)
        notifyDataSetChanged()
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0)
            return TYPE_LIST
        else
            return TYPE_LIST
//        return super.getItemViewType(position)
    }

    override fun getItemCount(): Int = list.size
    class VHeader(itemView: LikeItemBinding) : RecyclerView.ViewHolder(itemView.root) {
    }

    class ViewHolder constructor(
            val binding: ListCateogryItemBinding,
            var list: ArrayList<MainCateogryModels>, var iItemClickListener: IItemClickListenrer,
            var backGroundDrawable: IntArray) :

            RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(data: MainCateogryModels, context: Context, position: Int) {

            when (position % 3) {

                0 -> {
                    binding.txtviewCateogryName.setBackgroundResource(R.drawable.gradient1_color)

                }

                2 -> binding.txtviewCateogryName.setBackgroundResource(R.drawable.gradient2_color)
                else->
                 binding.txtviewCateogryName.setBackgroundResource(R.drawable.gradient3_color)
            }
            binding.txtviewCateogryName.text = data.cateogryName
            binding.txtviewCateogryName.setOnClickListener(this)
            /* binding.imgvtiewFavIcon.setOnClickListener(this)
             binding.cardviewFeatureRecipe.setOnClickListener(this)
  */
        }

        override fun onClick(view: View?) {
            when (view) {
                binding.txtviewCateogryName -> {
                    iItemClickListener.itemClick(adapterPosition, view,"","",2)
                }


            }


        }


    }
}
