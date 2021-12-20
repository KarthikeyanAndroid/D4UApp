package com.d4u.shopping.di.modules

import android.app.Application
import com.d4u.shopping.app.di.modules.SharedPreferenceModule
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


/**
 * Created by Karthikeyan V on 11,November,2021
 */
//@Module(includes = [SharedPreferenceModule::class])
class ApplicationModule(application: Application) {
    private val application: Application

   /* @Singleton
    @Provides
    fun providesApplication(): Application {
        return application
    }
*/
    init {
        this.application = application
    }
}