package com.sliide.myapplication.networking

import okhttp3.Interceptor
import okhttp3.Response

class UserInterceptor: Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        var request = chain.request()
        request = request.newBuilder()
            .addHeader("Authorization", "Bearer ${GoRestClient.API_KEY}")
            .build()
        return chain.proceed(request)
    }
}