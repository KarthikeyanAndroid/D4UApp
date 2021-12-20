package com.d4u.shopping.adapter

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.d4u.shopping.adapter.MainCateogryListAdapter
import com.d4u.shopping.interfaces.IItemClickListenrer
import com.d4u.shopping.R
import com.d4u.shopping.data.OfferCategory
import com.d4u.shopping.utils.Constant


/**
 * Created by Karthikeyan V on 06,November,2021
 */
class RecyclerViewAdapter(context: Context, list: ArrayList<OfferCategory>, iItemClickListener: IItemClickListenrer
) :
        RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private var checkedPosition = 0
    private lateinit var iItemClickListener: IItemClickListenrer

    init {

        this.iItemClickListener = iItemClickListener
    }

    companion object {
        const val VIEW_TYPE_ONE = 1
        const val VIEW_TYPE_TWO = 2
    }

    private val context: Context = context
    var list: ArrayList<OfferCategory> = list

    private inner class View1ViewHolder(itemView: View, itemClickListenrer: IItemClickListenrer) :
            RecyclerView.ViewHolder(itemView) {
        var checked = true
        var message: ImageView = itemView.findViewById(R.id.circle_imgview)

        @RequiresApi(Build.VERSION_CODES.N)
        fun bind(position: Int) {
            if (checkedPosition == adapterPosition)
                message.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_thumb_filled_24))
            else
                message.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_thumb_outline_24))

            message.setOnClickListener {
                checkedPosition = adapterPosition

                Toast.makeText(context, "hi" + adapterPosition, Toast.LENGTH_SHORT).show()
                if (checkedPosition == 0) {
//                    iItemClickListener.itemClick(adapterPosition, message, "")
                    message.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_thumb_filled_24))
                    if (checked) {
                        Constant.CAT_ID="unlike"
                        Constant.BUSINESS_ID=""
                        checked = false
                        iItemClickListener.itemClick(adapterPosition, message, "unlike", "",0)
                        list.subList(1, list.size).clear()
                        // notifyDataSetChanged()
                    } else {
                        Constant.CAT_ID=""
                        Constant.BUSINESS_ID=""
                        checked = true
                        iItemClickListener.itemClick(adapterPosition, message, "", "",0)

                    }
                } else {
                    list.clear()

                    checkedPosition = -1
                    message.setImageDrawable(ContextCompat.getDrawable(context, R.drawable.ic_thumb_outline_24))
                }
                // checkedPosition = -1
                notifyDataSetChanged()
            }
            /*val recyclerViewModel = list[position]
            message.text = recyclerViewModel.category
        */
        }
    }

    private inner class View2ViewHolder(itemView: View) :
            RecyclerView.ViewHolder(itemView) {
        var message: TextView = itemView.findViewById(R.id.txtview_cateogry_name)

        private val textView: TextView

        //        private val imageView: ImageView
        fun bind(employee: OfferCategory) {
            if (checkedPosition == -1) {
                textView.setBackgroundResource(R.drawable.corner_grey_shape)

//                imageView.setVisibility(View.GONE)
            } else {
                if (checkedPosition == adapterPosition) {
                    textView.setBackgroundResource(R.drawable.gradient2_color)

//                    imageView.setVisibility(View.VISIBLE)
                } else {
                    textView.setBackgroundResource(R.drawable.corner_grey_shape)

//                    imageView.setVisibility(View.GONE)
                }
            }

            textView.setText(employee.category)
            textView.setOnClickListener({
                textView.setBackgroundResource(R.drawable.gradient2_color)
                Constant.CAT_ID = employee.id
                Constant.BUSINESS_ID = ""

                iItemClickListener.itemClick(adapterPosition - 1, textView, Constant.CAT_ID, Constant.BUSINESS_ID,0)

//                imageView.setVisibility(View.VISIBLE)
                if (checkedPosition != adapterPosition) {
                    notifyItemChanged(checkedPosition)
                    checkedPosition = adapterPosition
                }

            })
        }

        init {
            textView = itemView.findViewById(R.id.txtview_cateogry_name)

            //            imageView = itemView.findViewById(R.id.imageView)
        }


        /* fun bind(employee: OfferCategory) {
             val recyclerViewModel = list[position]
             message.text = recyclerViewModel.category




         }*/
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        if (viewType == VIEW_TYPE_ONE) {
            return View1ViewHolder(
                    LayoutInflater.from(context).inflate(R.layout.like_item, parent, false), iItemClickListener)
        }
        return View2ViewHolder(
                LayoutInflater.from(context).inflate(R.layout.list_cateogry_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is View1ViewHolder -> {
                holder.bind(position)

            }
            is View2ViewHolder -> {
                holder.bind(list.get(position - 1)!!)
            }


        }
    }

    private val TYPE_HEADER: Int = 0
    private val TYPE_LIST: Int = 1
    override fun getItemViewType(position: Int): Int {
        if (position == 0)
            return TYPE_LIST
        else
            return TYPE_HEADER
    }
}