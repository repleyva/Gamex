package com.repleyva.data.datasource.remote

import com.repleyva.data.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response

class AuthInterceptor: Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originalRequest = chain.request()
        val url = originalRequest.url.newBuilder().addQueryParameter("key", BuildConfig.API_KEY).build()
        return chain.proceed(originalRequest.newBuilder().url(url).build())
    }
}