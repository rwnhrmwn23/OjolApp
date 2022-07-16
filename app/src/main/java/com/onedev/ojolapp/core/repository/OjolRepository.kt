package com.onedev.ojolapp.core.repository

import com.onedev.ojolapp.core.domain.model.Customer
import com.onedev.ojolapp.core.domain.model.Driver
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
    val customerAllStateEventManager: StateEventManager<List<Customer>>
    val customerStateEventManager: StateEventManager<Customer>
    val driverAllStateEventManager: StateEventManager<List<Driver>>
    val driverStateEventManager: StateEventManager<Driver>

    suspend fun loginCustomer(loginRequest: LoginRequest)
    suspend fun registerCustomer(registerRequest: RegisterRequest)
    suspend fun loginDriver(loginRequest: LoginRequest)
    suspend fun registerDriver(registerRequest: RegisterRequest)
    suspend fun customerAll()
    suspend fun customer()
    suspend fun driverAll()
    suspend fun driver()

}