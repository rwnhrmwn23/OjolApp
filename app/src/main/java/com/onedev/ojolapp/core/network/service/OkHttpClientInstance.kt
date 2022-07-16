package com.onedev.ojolapp.core.network.service

import android.content.Context
import com.onedev.ojolapp.BuildConfig
import com.onedev.ojolapp.MainApplication
import com.onedev.ojolapp.utils.Constant.TOKEN
import com.onedev.ojolapp.utils.getInstance
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.annotation.Single

object OkHttpClientInstance {
    @Single
    fun okhttpClientWithToken(): OkHttpClient {
        val client = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            client.addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
        }
        client.addInterceptor { chain ->
            val original = chain.request()
            val requestBuilder = original.newBuilder()
                .method(original.method, original.body)
                .addHeader(
                    "Authorization",
                        getInstance(MainApplication.appContext as Context).getString(TOKEN)
                )
            val request = requestBuilder.build()
            chain.proceed(request)
        }
        return client.build()
    }
}