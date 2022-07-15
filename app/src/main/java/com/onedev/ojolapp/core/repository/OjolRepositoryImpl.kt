package com.onedev.ojolapp.core.repository

import com.onedev.ojolapp.core.domain.model.Login
import com.onedev.ojolapp.core.domain.model.Register
import com.onedev.ojolapp.core.network.response.LoginRequest
import com.onedev.ojolapp.core.network.response.RegisterRequest
import com.onedev.ojolapp.core.network.service.NetworkSource
import com.onedev.ojolapp.event.StateEventManager
import com.onedev.ojolapp.utils.default
import org.koin.core.annotation.Single

@Single
class OjolRepositoryImpl(
    private val networkSource: NetworkSource
): OjolRepository {

    private val _loginCustomerStateEventManager = default<Login>()
    override val loginCustomerStateEventManager: StateEventManager<Login>
        get() = _loginCustomerStateEventManager

    private val _registerCustomerStateEventManager = default<Register>()
    override val registerCustomerStateEventManager: StateEventManager<Register>
        get() = _registerCustomerStateEventManager

    override suspend fun loginCustomer(loginRequest: LoginRequest) {
        networkSource.loginCustomer(loginRequest)
            .collect(_loginCustomerStateEventManager)
    }

    override suspend fun registerCustomer(registerRequest: RegisterRequest) {
        networkSource.registerCustomer(registerRequest)
            .collect(_registerCustomerStateEventManager)
    }

    override suspend fun loginDriver(loginRequest: LoginRequest) {
        networkSource.loginDriver(loginRequest)
            .collect(_loginCustomerStateEventManager)
    }

    override suspend fun registerDriver(registerRequest: RegisterRequest) {
        networkSource.registerDriver(registerRequest)
            .collect(_registerCustomerStateEventManager)
    }


}