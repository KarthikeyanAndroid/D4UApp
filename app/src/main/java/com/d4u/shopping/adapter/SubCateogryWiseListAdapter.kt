package com.d4u.shopping.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import com.d4u.shopping.interfaces.IItemClickListenrer
import com.d4u.shopping.MainCateogryModels
import com.d4u.shopping.R
import com.d4u.shopping.data.BusinessList
import com.d4u.shopping.data.Customer
import com.d4u.shopping.databinding.LikeItemBinding
import com.d4u.shopping.databinding.ListSubcateogryItemBinding
import com.d4u.shopping.utils.Constant


class SubCateogryWiseListAdapter(
        var list: ArrayList<BusinessList>,
        var context: Context, var iItemClickListener: IItemClickListenrer
) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var checkedPosition = -1

    private val TAG: String = javaClass.simpleName


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_HEADER) {
            val likeItemBinding = LikeItemBinding.inflate(LayoutInflater.from(context), parent, false)
            return VHeader(likeItemBinding, "")
        } else {
            val subcateogryItemBinding =
                    ListSubcateogryItemBinding.inflate(LayoutInflater.from(context), parent, false)
            return ViewHolder(subcateogryItemBinding, list, iItemClickListener)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is ViewHolder -> {
                holder.bind(list.get(position)!!, context)

            }
        }

//        Log.i(TAG, "onBindViewHolder: " + Gson().toJson(list))
    }

    fun addItems(postItems: ArrayList<BusinessList>) {
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

    inner class ViewHolder constructor(
            val binding: ListSubcateogryItemBinding,
            var list: ArrayList<BusinessList>, var iItemClickListener: IItemClickListenrer
    ) :

            RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        fun bind(data: BusinessList, context: Context) {
            if (checkedPosition == -1) {
                binding.root.setBackgroundColor(ContextCompat.getColor(context,R.color.white))

//                imageView.setVisibility(View.GONE)
            } else {
                if (checkedPosition == adapterPosition) {
                    binding.root.setBackgroundResource(R.drawable.gradient2_color_rect)

//                    imageView.setVisibility(View.VISIBLE)
                } else {
                    binding.root.setBackgroundColor(ContextCompat.getColor(context,R.color.white))

//                    imageView.setVisibility(View.GONE)
                }
            }
            binding.productImgview.setOnClickListener(this)

            binding.txtviewCompanyName.text = data.business_name
            Glide.with(context).load(Constant.BUSINESS_LOGO_PATH + data.business_logo).into(binding.productImgview)
        }

        override fun onClick(view: View?) {
            when (view) {
                binding.productImgview -> {
                    binding.root.setBackgroundResource(R.drawable.gradient2_color_rect)

                    Constant.BUSINESS_ID = list.get(adapterPosition).customer_id
                    iItemClickListener.itemClick(adapterPosition, view, Constant.CAT_ID, Constant.BUSINESS_ID,1)
                    if (checkedPosition != adapterPosition) {

                        notifyItemChanged(checkedPosition)
                        checkedPosition = adapterPosition

                    }
                }


            }


        }


    }
}
