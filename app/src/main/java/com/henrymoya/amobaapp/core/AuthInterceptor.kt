package com.henrymoya.amobaapp.core

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor(private val apiKey: String) : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request()
        val newRequest: Request = request.newBuilder()
            .addHeader("key", apiKey)
            .build()
        return chain.proceed(newRequest)
    }
}