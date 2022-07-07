package com.onedev.ojolapp.utils

import com.onedev.ojolapp.core.domain.model.Login
import com.onedev.ojolapp.core.network.response.LoginResponse

object Mapper {
    fun mapLoginResponse(loginResponse: LoginResponse?): Login {
        return Login(loginResponse?.data?.token)
    }
}