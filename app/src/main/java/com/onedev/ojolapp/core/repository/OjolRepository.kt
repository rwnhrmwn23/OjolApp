package com.onedev.ojolapp.core.repository

import com.onedev.ojolapp.core.domain.model.Login
import com.onedev.ojolapp.core.network.response.LoginRequest
import com.onedev.ojolapp.event.StateEventManager

interface OjolRepository {

    val loginCustomerStateEventManager: StateEventManager<Login>

    suspend fun loginCustomer(loginRequest: LoginRequest)

}