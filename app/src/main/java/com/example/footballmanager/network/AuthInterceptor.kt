package com.example.footballmanager.network

import com.example.footballmanager.BuildConfig
import com.example.footballmanager.R
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor : Interceptor {
    private val hostName: String = "x-rapidapi-host"
    private val hostValue: String = BuildConfig.RAPIDAPI_HOST
    private val keyName: String = "x-rapidapi-key"
    private val keyValue: String = BuildConfig.RAPIDAPI_KEY

    override fun intercept(chain: Interceptor.Chain): Response {
        val currentRequest = chain.request().newBuilder()
            .addHeader(hostName, hostValue)
            .addHeader(keyName, keyValue)
            .build()
        return chain.proceed(currentRequest)
    }
}
