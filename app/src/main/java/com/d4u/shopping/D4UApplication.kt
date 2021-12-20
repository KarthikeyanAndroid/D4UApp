package com.d4u.shopping

import com.d4u.shopping.app.di.components.AppComponent
import com.d4u.shopping.app.di.components.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class D4UApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {

        val appComponent: AppComponent = DaggerAppComponent.builder().application(this).build()
        return appComponent
    }





}