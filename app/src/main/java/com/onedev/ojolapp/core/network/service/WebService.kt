package com.onedev.ojolapp.core.network.service

import com.onedev.ojolapp.BuildConfig.BASE_URL
import com.onedev.ojolapp.core.network.response.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Headers
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

    @GET(CUSTOMER_ALL)
    @Headers("Authorization:eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJjZTQ1Nzg1NC1jNGI3LTQ5OGUtODZlMS1lZDA5YTM2M2UxNTgiLCJhdXRoIjpbImphcHJhIl0sImV4cCI6MTY1Nzk4NjQxNn0.ExN9q7-qpc9wjMh7bbl1vMPEAgza70t0Ffw5u0TnjXw")
    suspend fun customerAll(): Response<CustomerResponse>

    companion object {
        const val LOGIN_CUSTOMER = "v1/customer/login"
        const val REGISTER_CUSTOMER = "v1/customer/register"
        const val LOGIN_DRIVER = "v1/driver/login"
        const val REGISTER_DRIVER = "v1/driver/register"
        const val CUSTOMER_ALL = "v1/customer/all"

        fun build(): WebService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WebService::class.java)
        }
    }
}