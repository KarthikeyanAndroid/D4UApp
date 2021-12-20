package com.d4u.shopping.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2
import com.d4u.shopping.R
import com.d4u.shopping.databinding.ImgviewSliderItemBinding

class ViewPagerAdapter(var sliderItems: List<SliderItems>, var viewPager: ViewPager2, var context: Context) :
        RecyclerView.Adapter<ViewPagerAdapter.ViewPagerHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewPagerHolder {
        val imgviewSliderItemBinding = ImgviewSliderItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewPagerHolder(imgviewSliderItemBinding, sliderItems)

    }

    override fun onBindViewHolder(holder: ViewPagerHolder, position: Int) {
        holder.bindData(sliderItems.get(position))
    }

    override fun getItemCount(): Int = sliderItems.size

    class ViewPagerHolder constructor(val binding: ImgviewSliderItemBinding, sliderItems: List<SliderItems>) :
            RecyclerView.ViewHolder(binding.root) {

        fun bindData(sliderItems: SliderItems) {
            binding.imgviewSlider.setBackgroundResource(sliderItems.image)

        }
    }
}