package com.d4u.shopping

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import androidx.viewpager2.widget.ViewPager2.OnPageChangeCallback
import com.d4u.shopping.adapter.SliderItems
import com.d4u.shopping.adapter.ViewPagerAdapter
import com.d4u.shopping.databinding.ActivityScreenSlideBinding

class ScreenSlideActivity : AppCompatActivity() {
    lateinit var activityScreenSlideBinding: ActivityScreenSlideBinding
    lateinit var sliderHandler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityScreenSlideBinding = ActivityScreenSlideBinding.inflate(layoutInflater)
        setContentView(activityScreenSlideBinding.root)
//        if (sliderHandler == null)

        sliderHandler = Handler(Looper.getMainLooper()!!)

        /* val pagerAdapter = ScreenSlidePagerAdapter(this)
         activityScreenSlideBinding.viewPagerImgview.adapter = pagerAdapter*/
        var listSlider = ArrayList<SliderItems>()
        listSlider.add(SliderItems(R.drawable.product3))
        listSlider.add(SliderItems(R.drawable.product2))
        listSlider.add(SliderItems(R.drawable.product5))
        listSlider.add(SliderItems(R.drawable.product1))
        listSlider.add(SliderItems(R.drawable.product4))
        val viewPagerAdapter = ViewPagerAdapter(listSlider, activityScreenSlideBinding.viewPagerImgview, this)
        activityScreenSlideBinding.viewPagerImgview.adapter = viewPagerAdapter
        activityScreenSlideBinding.viewPagerImgview.clipToPadding = false
        activityScreenSlideBinding.viewPagerImgview.clipChildren = false
        activityScreenSlideBinding.viewPagerImgview.offscreenPageLimit = 3
        activityScreenSlideBinding.viewPagerImgview.getChildAt(0).setOverScrollMode(RecyclerView.OVER_SCROLL_NEVER)

        var compositePageTransformer = CompositePageTransformer()
        compositePageTransformer.addTransformer(MarginPageTransformer(40))
        compositePageTransformer.addTransformer(ViewPager2.PageTransformer { page, position ->
            var r = 1 - Math.abs(position)
            page.setScaleY(0.85f + r * 0.15f)
        })
        activityScreenSlideBinding.viewPagerImgview.setPageTransformer(compositePageTransformer)
        activityScreenSlideBinding.viewPagerImgview.registerOnPageChangeCallback(object : OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                progressStatus = 0
                activityScreenSlideBinding.contentProgress.progress = progressStatus
                sliderHandler.removeCallbacks(sliderRunnable)
/*                Thread {
                    progressStatus = 0
                    while (progressStatus < 50) {
                        progressStatus += 1
                        // Update the progress bar and display the
                        //current value in the text view
                        sliderHandler.post(Runnable {
                            activityScreenSlideBinding.contentProgress.setProgress(progressStatus)
//                    textView.setText(progressStatus.toString() + "/" + progressBar.getMax())
                        })
                        try {
                            // Sleep for 200 milliseconds.
                            Thread.sleep(100)
//                            sliderHandler.postDelayed(sliderRunnable, 1500) // slide duration 2 seconds

                        } catch (e: InterruptedException) {
                            e.printStackTrace()
                        } finally {

                        }
                    }
                    sliderHandler.postDelayed(sliderRunnable, 1000) // slide duration 2 seconds

                }.start()*/
                sliderHandler.postDelayed(sliderRunnable, 3000) // slide duration 2 seconds

            }

        })
    }

    var progressStatus = 0
    private val sliderRunnable = Runnable {
progressStatus=0
        activityScreenSlideBinding.viewPagerImgview.setCurrentItem(activityScreenSlideBinding.viewPagerImgview.getCurrentItem() + 1)
  progressStatus=0
    }

    override fun onPause() {
        super.onPause()
        sliderHandler.removeCallbacks(sliderRunnable)
    }

    override fun onResume() {
        super.onResume()
        sliderHandler.postDelayed(sliderRunnable, 2000)
    }

}