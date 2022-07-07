package com.onedev.ojolapp.core.network.response

data class LoginResponse(
    val `data`: Data?,
    val message: String?,
    val status: Boolean?
) {
    data class Data(
        val token: String?
    )
}