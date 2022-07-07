package com.onedev.ojolapp.core.network.service

import com.onedev.ojolapp.BuildConfig.BASE_URL
import com.onedev.ojolapp.core.network.response.LoginRequest
import com.onedev.ojolapp.core.network.response.LoginResponse
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.POST

interface WebService {

    @POST(LOGIN_CUSTOMER)
    suspend fun loginCustomer(
        @Body request: LoginRequest
    ): Response<LoginResponse>

    companion object {
        const val LOGIN_CUSTOMER = "v1/customer/login"

        fun build(): WebService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WebService::class.java)
        }
    }
}