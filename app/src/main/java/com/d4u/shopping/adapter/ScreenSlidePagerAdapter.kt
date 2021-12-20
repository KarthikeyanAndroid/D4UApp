package com.d4u.shopping.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.d4u.shopping.ui.gallery.ImageSlideShow

class ScreenSlidePagerAdapter(fa:FragmentActivity): FragmentStateAdapter(fa) {
     var NUM_PAGES=5
    override fun getItemCount(): Int=NUM_PAGES

    override fun createFragment(position: Int): Fragment =ImageSlideShow()
}