package com.henrymoya.amobaapp.core

import android.content.Context
import com.henrymoya.amobaapp.BuildConfig
import com.henrymoya.amobaapp.BuildConfig.API_KEY
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit

object ProviderOkHttpClient {
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpClientBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
            okHttpClientBuilder.addInterceptor(loggingInterceptor)
        }
        okHttpClientBuilder.addInterceptor(AuthInterceptor(API_KEY))
        okHttpClientBuilder.connectTimeout(5000, TimeUnit.MILLISECONDS)
        okHttpClientBuilder.readTimeout(5000, TimeUnit.MILLISECONDS)
        return okHttpClientBuilder.build()
    }
}