package com.d4u.shopping.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.d4u.shopping.interfaces.IItemClickListenrer
import com.d4u.shopping.R
import com.d4u.shopping.data.GetCatWiseHomeProductModel
import com.d4u.shopping.data.HomeProductList
import com.d4u.shopping.data.OfferCategory
import com.d4u.shopping.data.OffersData
import com.d4u.shopping.databinding.ListCateogryItemBinding
import com.d4u.shopping.databinding.ListItemProductsCateogriesBinding
import java.util.*


/**
 * Created by Karthikeyan V on 02,November,2021
 */
class SearchIngredientAdapter(getFridgeIngredientDataArrayList: ArrayList<OfferCategory>, mContext: Context, var iItemClickListener: IItemClickListenrer) : RecyclerView.Adapter<SearchIngredientAdapter.ViewHolder?>() {
    private val mContext: Context
    var getFridgeIngredientDataArrayList: ArrayList<OfferCategory>
    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): ViewHolder {
        val listIngredientsSearchBinding: ListCateogryItemBinding = ListCateogryItemBinding.inflate(LayoutInflater.from(viewGroup.context), viewGroup, false)
        return ViewHolder(listIngredientsSearchBinding)
    }

    fun addSearchIngredients(getFridgeIngredientData: ArrayList<OfferCategory>) {
        getFridgeIngredientDataArrayList = getFridgeIngredientData
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, i: Int) {
        val getFridgeIngredientData: OfferCategory = getFridgeIngredientDataArrayList[i]
        viewHolder.binding.txtviewCateogryName.setText(getFridgeIngredientData.category)
/*
        if (getFridgeIngredientData.checked) {
            viewHolder.binding.txtviewCateogryName.setBackgroundResource(R.drawable.gradient2_color)

        } else {
            viewHolder.binding.txtviewCateogryName.setBackgroundResource(R.drawable.corner_grey_shape)

        }
        viewHolder.binding.getRoot().setOnClickListener { view ->
            viewHolder.selectedItemPos = i

            if (viewHolder.lastItemSelectedPos == -1) {
                viewHolder.lastItemSelectedPos = viewHolder.selectedItemPos
                getFridgeIngredientData.checked = true
                iItemClickListener.itemClick(viewHolder.adapterPosition, viewHolder.binding.txtviewCateogryName, getFridgeIngredientData.id)

            } else {
                getFridgeIngredientData.checked = false
                notifyItemChanged(viewHolder.lastItemSelectedPos)
                viewHolder.lastItemSelectedPos = viewHolder.selectedItemPos
            }

            notifyItemChanged(viewHolder.selectedItemPos)
            notifyDataSetChanged()
        }
*/
        if (viewHolder.selectedItemPos == -1) {
            viewHolder.binding.txtviewCateogryName.setBackgroundResource(R.drawable.corner_grey_shape)

        } else {
            if (viewHolder.selectedItemPos == viewHolder.adapterPosition) {
                viewHolder.binding.txtviewCateogryName.setBackgroundResource(R.drawable.gradient2_color)

            }else{
                viewHolder.binding.txtviewCateogryName.setBackgroundResource(R.drawable.corner_grey_shape)

            }
        }
        viewHolder.binding.txtviewCateogryName.setOnClickListener({
            if (viewHolder.selectedItemPos != viewHolder.adapterPosition) {
                notifyItemChanged(viewHolder.selectedItemPos)
                viewHolder.selectedItemPos = viewHolder.adapterPosition
            }
        })
    }

    override fun getItemCount(): Int {
        return getFridgeIngredientDataArrayList.size
    }

    class ViewHolder(itemView: ListCateogryItemBinding) : RecyclerView.ViewHolder(itemView.getRoot()) {
        val binding: ListCateogryItemBinding
        var selectedItemPos = 0
        var lastItemSelectedPos = 0

        init {
            binding = itemView
//            selectedItemPos = -1
            lastItemSelectedPos = -1

        }

    }

    init {
        this.mContext = mContext
        this.getFridgeIngredientDataArrayList = getFridgeIngredientDataArrayList

    }


}