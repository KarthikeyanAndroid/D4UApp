package com.d4u.shopping.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.d4u.shopping.interfaces.IItemClickListenrer
import com.d4u.shopping.MainCateogryModels
import com.d4u.shopping.databinding.LikeItemBinding
import com.d4u.shopping.databinding.ListBookmarkItemsBinding


class BookMarkListAdapter(
        var list: ArrayList<MainCateogryModels>,
        var context: Context, var iItemClickListener: IItemClickListenrer
) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val TAG: String = javaClass.simpleName
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_HEADER) {
            val likeItemBinding = LikeItemBinding.inflate(LayoutInflater.from(context), parent, false)
            return VHeader(likeItemBinding, "")
        } else {
            val listBookmarkItemsBinding =
                    ListBookmarkItemsBinding.inflate(LayoutInflater.from(context), parent, false)



            return ViewHolder(listBookmarkItemsBinding, list, iItemClickListener)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
     when(holder)
     {
         is ViewHolder->
         {
             holder.bind(list.get(position), context)

         }
     }

//        Log.i(TAG, "onBindViewHolder: " + Gson().toJson(list))
    }

    fun addItems(postItems: ArrayList<MainCateogryModels>) {
        list.addAll(postItems!!)
        notifyDataSetChanged()
    }

    private val TYPE_HEADER: Int = 0
    private val TYPE_LIST: Int = 1
    override fun getItemViewType(position: Int): Int {
        if (position == 0)
            return TYPE_LIST
        else
            return TYPE_LIST
//        return super.getItemViewType(position)
    }

    override fun getItemCount(): Int = list.size
    class VHeader constructor(val binding: LikeItemBinding, var str: String) : RecyclerView.ViewHolder(binding.root) {
        fun bind(context: Context) {
            binding.circleImgview
        }
    }

    class ViewHolder constructor(
            val binding: ListBookmarkItemsBinding,
            var list: ArrayList<MainCateogryModels>, var iItemClickListener: IItemClickListenrer
    ) :

            RecyclerView.ViewHolder(binding.root), View.OnClickListener {
       companion object fun bind(data: MainCateogryModels, context: Context) {


            binding.txtviewProductTitle.text = data.cateogryName
            binding.txtviewSubCateogry.text = data.cateogryName
            binding.imgviewProduct.setImageResource(data.img)
        }

        override fun onClick(view: View?) {
            when (view) {
                /* binding.txtviewCateogryName -> {
                     iItemClickListener.itemClick(adapterPosition,view)
                 }*/


            }


        }


    }
}
