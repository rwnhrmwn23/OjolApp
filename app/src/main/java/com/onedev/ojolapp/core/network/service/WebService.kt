package com.onedev.ojolapp.core.network.service

import com.onedev.ojolapp.BuildConfig.BASE_URL
import com.onedev.ojolapp.core.network.response.*
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
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
    suspend fun customerAll(): Response<CustomerAllResponse>

    @GET(CUSTOMER)
    suspend fun customer(): Response<CustomerResponse>

    @GET(DRIVER_ALL)
    suspend fun driverAll(): Response<DriverAllResponse>

    @GET(DRIVER)
    suspend fun driver(): Response<DriverResponse>

    companion object {
        const val LOGIN_CUSTOMER = "v1/customer/login"
        const val REGISTER_CUSTOMER = "v1/customer/register"
        const val LOGIN_DRIVER = "v1/driver/login"
        const val REGISTER_DRIVER = "v1/driver/register"
        const val CUSTOMER_ALL = "v1/customer/all"
        const val CUSTOMER = "v1/customer"
        const val DRIVER_ALL = "v1/driver/all"
        const val DRIVER = "v1/driver/"

        private var okhttpClientWithToken = OkHttpClientInstance.okhttpClientWithToken()

        fun build(): WebService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
                .create(WebService::class.java)
        }

        fun buildWithAuth(): WebService {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okhttpClientWithToken)
                .build()
                .create(WebService::class.java)
        }
    }
}