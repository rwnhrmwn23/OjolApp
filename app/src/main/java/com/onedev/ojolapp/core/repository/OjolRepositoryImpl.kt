package com.onedev.ojolapp.core.repository

import com.onedev.ojolapp.core.domain.model.Login
import com.onedev.ojolapp.core.network.response.LoginRequest
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

    override suspend fun loginCustomer(loginRequest: LoginRequest) {
        networkSource.loginCustomer(loginRequest)
            .collect(_loginCustomerStateEventManager)
    }


}