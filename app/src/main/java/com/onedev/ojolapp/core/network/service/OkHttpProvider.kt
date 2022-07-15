package com.onedev.ojolapp.core.network.service

import android.content.Context
import com.onedev.ojolapp.BuildConfig
import com.onedev.ojolapp.utils.Constant.TOKEN
import com.onedev.ojolapp.utils.getStringPreference
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

class OkHttpProvider {
    companion object {
        fun provideClient(context: Context): OkHttpClient {
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
                        context.getStringPreference(TOKEN)
                    )
                val request = requestBuilder.build()
                chain.proceed(request)
            }
            return client.build()
        }
    }
}