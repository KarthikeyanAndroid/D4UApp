package com.touchizen.modules

import android.app.Application
import androidx.room.Room
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import com.d4u.shopping.db.D4UDatabase
import com.d4u.shopping.db.D4UdaoAccess
import com.d4u.shopping.repository.BookMarkRepository
import com.d4u.shopping.repository.CategoryRepository
import com.d4u.shopping.repository.CustomerRepository
import com.d4u.shopping.utils.Constant
import com.d4u.shopping.app.webservice.APIServices

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

/**
 * Created by Karthikeyan on 27-01-2021.
 */
@Module
class RetrofitModule {
    @Singleton
    @Provides
    fun provideOKHttpClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .readTimeout(1200, TimeUnit.SECONDS)
                .connectTimeout(1200, TimeUnit.SECONDS)
                .build()

    }

    @Provides
    @Singleton
    @Named("interceptor")
    fun getOkHttpCleint(httpLoggingInterceptor: HttpLoggingInterceptor?): OkHttpClient? {
        return OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor!!)
                .build()
    }


    @Singleton
    @Provides
    fun provideGSON(): GsonConverterFactory {
        return GsonConverterFactory.create()
    }

    @Provides
    @Singleton
    fun provideDatabase(application: Application?): D4UDatabase {
        return Room.databaseBuilder(application!!,
                D4UDatabase::class.java, "D4UDatabase.db")
                .fallbackToDestructiveMigration()
                .build()
    }

    @Provides
    @Singleton
    fun getHttpLoggingInterceptor(): HttpLoggingInterceptor {
        val httpLoggingInterceptor = HttpLoggingInterceptor()
        httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
        return httpLoggingInterceptor
    }

    @Provides
    @Singleton
    fun provideUserDao(database: D4UDatabase): D4UdaoAccess? {
        return database.d4uDdaoAccess()
    }

    @Singleton
    @Provides
    fun provideRetrofit(
            gsonConverterFactory: GsonConverterFactory,
            okHttpClient: OkHttpClient
    ): Retrofit {
        return Retrofit.Builder()
                .baseUrl(Constant.BASEURL)
                .addConverterFactory(gsonConverterFactory)
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .client(okHttpClient)
                .build()
    }

    @Provides
    @Singleton
    fun provideApiInterface(retrofit: Retrofit): APIServices {
        return retrofit.create(APIServices::class.java)
    }

    @Provides
    fun providecategoryRepository(apiServices: APIServices): CategoryRepository {
        return CategoryRepository(apiServices)
    }

    @Provides
    fun provideCustomerRepository(apiServices: APIServices): CustomerRepository {
        return CustomerRepository(
                apiServices
        )
    }

    @Provides
    fun provideBookMarkRepository(database: D4UDatabase): BookMarkRepository {
        return BookMarkRepository(database)
    }

}