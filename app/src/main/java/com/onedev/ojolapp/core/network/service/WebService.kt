package com.onedev.ojolapp.core.network.service

import com.onedev.ojolapp.BuildConfig.BASE_URL
import com.onedev.ojolapp.core.network.response.LoginRequest
import com.onedev.ojolapp.core.network.response.LoginResponse
import com.onedev.ojolapp.core.network.response.RegisterRequest
import com.onedev.ojolapp.core.network.response.RegisterResponse
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

    @POST(REGISTER_CUSTOMER)
    suspend fun registerCustomer(
        @Body request: RegisterRequest
    ): Response<RegisterResponse>

    @POST(LOGIN_DRIVER)
    suspend fun loginDriver(
        @Body request: LoginRequest
    ): Response<LoginResponse>

    @POST(REGISTER_DRIVER)
    suspend fun registerDriver(
        @Body request: RegisterRequest
    ): Response<RegisterResponse>

    companion object {
        const val LOGIN_CUSTOMER = "v1/customer/login"
        const val REGISTER_CUSTOMER = "v1/customer/register"
        const val LOGIN_DRIVER = "v1/driver/login"
        const val REGISTER_DRIVER = "v1/driver/register"

        fun build(): WebService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WebService::class.java)
        }
    }
}