package com.mufidz.nontonkuy.data

import com.mufidz.nontonkuy.utils.Const.BASE_URL
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit

object RetrofitClient {

    fun provideOkHttpClient(): OkHttpClient {
        val loggerInterceptor = HttpLoggingInterceptor { message ->
            Timber.tag("Network Request").d(message)
        }.also {
            it.redactHeader("Authorization")
            it.redactHeader("Cookies")
            it.level = HttpLoggingInterceptor.Level.BASIC
        }
        return OkHttpClient().newBuilder().apply {
            addInterceptor(loggerInterceptor)
            connectTimeout(15, TimeUnit.SECONDS)
            readTimeout(15, TimeUnit.SECONDS)
            writeTimeout(15, TimeUnit.SECONDS)
        }.build()
    }

    fun provideMoshiConverter(): MoshiConverterFactory {
        val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
        return MoshiConverterFactory.create(moshi)
    }

    fun apiServices(okHttpClient: OkHttpClient, factory: MoshiConverterFactory): ApiServices {
        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(factory)
            .build()
        return retrofit.create(ApiServices::class.java)
    }
}