package com.d4u.shopping.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

import com.d4u.shopping.interfaces.IItemClickListenrer
import com.d4u.shopping.MainCateogryModels
import com.d4u.shopping.R
import com.d4u.shopping.data.OfferCategory
import com.d4u.shopping.databinding.LikeItemBinding
import com.d4u.shopping.databinding.ListCateogryItemBinding
import com.d4u.shopping.utils.Constant
import java.util.*


class MainCateogryListAdapter(
        var list: ArrayList<OfferCategory>,
        var context: Context, var iItemClickListener: IItemClickListenrer
) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val TYPE_HEADER: Int = 0
    private val TYPE_LIST: Int = 1
    private val TAG: String = javaClass.simpleName
    var checkedPos = -1
    var selectedItemPos = -1
    var lastItemSelectedPos = -1

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == TYPE_HEADER) {

            val likeItemBinding = LikeItemBinding.inflate(LayoutInflater.from(context), parent, false)
            return VHeader(likeItemBinding)
        } else {

            val listHomeGridBinding =
                    ListCateogryItemBinding.inflate(LayoutInflater.from(context), parent, false)



            return ViewHolder(listHomeGridBinding, list, iItemClickListener, checkedPos, selectedItemPos, lastItemSelectedPos)
        }
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {


        when (holder) {
            is ViewHolder -> {
                holder.bind(list.get(position)!!, context, holder)

            }


        }
//        holder.bind(list.get(position)!!, context)
//        Log.i(TAG, "onBindViewHolder: " + G`son().toJson(list))
    }

    fun addItems(postItems: ArrayList<OfferCategory>) {
        list.addAll(postItems!!)
        notifyDataSetChanged()
    }

    fun notifyITemChange(position: Int) {
        notifyItemChanged(position)
    }

    override fun getItemViewType(position: Int): Int {
        if (position == 0)
            return TYPE_HEADER
        else
            return TYPE_LIST
//        return super.getItemViewType(position)
    }

    override fun getItemCount(): Int = list.size
    class VHeader constructor(itemView: LikeItemBinding) : RecyclerView.ViewHolder(itemView.root) {

    }


    class ViewHolder constructor(
            val binding: ListCateogryItemBinding,
            var list: ArrayList<OfferCategory>, var iItemClickListener: IItemClickListenrer, var checkedPos: Int,
            var selectedItemPos: Int, var lastItemSelectedPos: Int) :

            RecyclerView.ViewHolder(binding.root), View.OnClickListener {

        var checked: Boolean = false
        fun bind(data: OfferCategory, context: Context, holder: RecyclerView.ViewHolder) {
            binding.txtviewCateogryName.text = data.category
            binding.txtviewCateogryName.setOnClickListener(this)

            binding.txtviewCateogryName.setOnClickListener {


                selectedItemPos = adapterPosition
                if (lastItemSelectedPos == -1)
                    lastItemSelectedPos = selectedItemPos
                else {

//                    notifyItemChanged(lastItemSelectedPos)
                    lastItemSelectedPos = selectedItemPos
                }
//                notifyItemChanged(selectedItemPos)
                if (!checked) {
                    checked = true
                    binding.txtviewCateogryName.setBackgroundResource(R.drawable.gradient2_color)
                    data.checked = true
                    Constant.CAT_ID = data.id
                    Constant.BUSINESS_ID=""
                    iItemClickListener.itemClick(adapterPosition, binding.txtviewCateogryName, Constant.CAT_ID, Constant.BUSINESS_ID,0)
                } else {
                    checked = false
                    binding.txtviewCateogryName.setBackgroundResource(R.drawable.corner_grey_shape)
                    data.checked = false

                }
            }
            if (data.checked)
                binding.txtviewCateogryName.setBackgroundResource(R.drawable.gradient2_color)
            else
                binding.txtviewCateogryName.setBackgroundResource(R.drawable.corner_grey_shape)

            /* binding.imgvtiewFavIcon.setOnClickListener(this)
             binding.cardviewFeatureRecipe.setOnClickListener(this)
  */
            if (checkedPos == -1) {
                binding.txtviewCateogryName.setBackgroundResource(R.drawable.corner_grey_shape)

            } else {
                if (checkedPos == adapterPosition) {
                    binding.txtviewCateogryName.setBackgroundResource(R.drawable.gradient2_color)

                } else {
                    binding.txtviewCateogryName.setBackgroundResource(R.drawable.corner_grey_shape)

                }
            }
        }

        override fun onClick(view: View?) {
            when (view) {
                binding.txtviewCateogryName -> {
                    if (checkedPos != adapterPosition) {
                        checkedPos = getAdapterPosition();
                    }
                    binding.txtviewCateogryName.setBackgroundResource(R.drawable.gradient2_color)

//                    iItemClickListener.itemClick(adapterPosition, view)

                }


            }


        }


    }
}
