package com.watercanedeilvery.di.modules


import com.d4u.shopping.BookMarkFragment
import com.d4u.shopping.HomeActivity
import com.d4u.shopping.SplashActivity
import com.d4u.shopping.ui.FeedBackFragment
import com.d4u.shopping.ui.OfferDetailFragment
import com.d4u.shopping.ui.home.OffersFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

/**
 * Created by Karthikeyan on 27-01-2021.
 */
@Module
abstract class ViewBindingModule {
    @ContributesAndroidInjector
    abstract fun splashActivity(): SplashActivity

    @ContributesAndroidInjector
    abstract fun homeActivity(): HomeActivity

   /* @ContributesAndroidInjector
    abstract fun mainActivityBinding(): MainActivity
*/
    @ContributesAndroidInjector
    abstract fun offerFragmentBinding(): OffersFragment

    @ContributesAndroidInjector
    abstract fun feedbackFragmentBinding(): FeedBackFragment

    @ContributesAndroidInjector
    abstract fun bookMarkFragmentBinding(): BookMarkFragment

    @ContributesAndroidInjector
    abstract fun offerDetailFragment(): OfferDetailFragment

}

