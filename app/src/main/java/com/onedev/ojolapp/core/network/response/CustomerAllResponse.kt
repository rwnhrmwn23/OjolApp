package com.onedev.ojolapp.core.network.response

data class CustomerAllResponse(
    val `data`: List<Data>?,
    val message: String?,
    val status: Boolean?
) {
    data class Data(
        val id: String?,
        val name: String?,
        val password: String?,
        val role: String?,
        val username: String?
    )
}