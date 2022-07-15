package com.onedev.ojolapp.core.repository

import com.onedev.ojolapp.core.domain.model.Login
import com.onedev.ojolapp.core.domain.model.Register
import com.onedev.ojolapp.core.network.response.LoginRequest
import com.onedev.ojolapp.core.network.response.RegisterRequest
import com.onedev.ojolapp.event.StateEventManager

interface OjolRepository {

    val loginCustomerStateEventManager: StateEventManager<Login>
    val registerCustomerStateEventManager: StateEventManager<Register>
    val loginDriverStateEventManager: StateEventManager<Login>
    val registerDriverStateEventManager: StateEventManager<Register>

    suspend fun loginCustomer(loginRequest: LoginRequest)
    suspend fun registerCustomer(registerRequest: RegisterRequest)
    suspend fun loginDriver(loginRequest: LoginRequest)
    suspend fun registerDriver(registerRequest: RegisterRequest)

}