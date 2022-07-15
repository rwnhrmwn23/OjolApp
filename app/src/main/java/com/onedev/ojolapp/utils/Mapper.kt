package com.onedev.ojolapp.utils

import com.onedev.ojolapp.core.domain.model.Login
import com.onedev.ojolapp.core.domain.model.Register
import com.onedev.ojolapp.core.network.response.LoginResponse
import com.onedev.ojolapp.core.network.response.RegisterResponse

object Mapper {
    fun mapLoginResponse(loginResponse: LoginResponse?): Login {
        return Login(loginResponse?.data?.token)
    }

    fun mapRegisterResponse(registerResponse: RegisterResponse): Register {
        return Register(registerResponse.status)
    }
}