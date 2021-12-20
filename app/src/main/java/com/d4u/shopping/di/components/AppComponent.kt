package com.d4u.shopping.app.di.components


import android.app.Application
import android.content.SharedPreferences
import com.d4u.shopping.D4UApplication
import com.d4u.shopping.di.modules.ApplicationModule
import com.watercanedeilvery.di.modules.ViewBindingModule
import com.d4u.shopping.app.di.modules.SharedPreferenceModule
import com.touchizen.modules.RetrofitModule

import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

/**
 * Created by Karthikeyan on 27-01-2021.
 */
@Singleton
@Component(
        modules = [AndroidSupportInjectionModule::class, ViewBindingModule::class,
            RetrofitModule::class/*, ApplicationModule::class*/, SharedPreferenceModule::class]
)
interface AppComponent : AndroidInjector<DaggerApplication> {


    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder
//        fun applicationModule(applicationModule: ApplicationModule): Builder
        fun build(): AppComponent
    }

//    fun inject(d4UApplication: D4UApplication)

    fun getSharedPrefs(): SharedPreferences?
}