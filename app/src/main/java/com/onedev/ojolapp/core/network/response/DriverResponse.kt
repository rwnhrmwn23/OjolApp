package com.onedev.ojolapp.core.network.response

data class DriverResponse(
    val `data`: Data?,
    val message: String?,
    val status: Boolean?
) {
    data class Data(
        val id: String?,
        val name: String?,
        val password: Any?,
        val role: String?,
        val username: String?
    )
}