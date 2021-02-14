package com.sliide.myapplication.networking

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

internal class GoRestClient {
    companion object {
        const val BASE_URL = "https://gorest.co.in/public-api/users/"
        const val API_KEY = "3510506179c2396ec402d422f22ece22853ca804b320623b5e010efdca327a27"


        fun retrofitInstance(): Retrofit {
            val retrofitClientBuilder = Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .baseUrl(BASE_URL)

            val interceptor = OkHttpClient.Builder()
                .addInterceptor(UserInterceptor())
            val okHttpClient = interceptor.build()
            retrofitClientBuilder.client(okHttpClient)
            return retrofitClientBuilder.build()

        }
    }
}