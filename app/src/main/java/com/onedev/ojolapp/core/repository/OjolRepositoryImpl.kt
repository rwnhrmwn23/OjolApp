package com.onedev.ojolapp.core.repository

import com.onedev.ojolapp.core.domain.model.Customer
import com.onedev.ojolapp.core.domain.model.Driver
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
) : OjolRepository {

    private val _loginCustomerStateEventManager = default<Login>()
    override val loginCustomerStateEventManager: StateEventManager<Login>
        get() = _loginCustomerStateEventManager

    private val _registerCustomerStateEventManager = default<Register>()
    override val registerCustomerStateEventManager: StateEventManager<Register>
        get() = _registerCustomerStateEventManager

    private val _loginDriverStateEventManager = default<Login>()
    override val loginDriverStateEventManager: StateEventManager<Login>
        get() = _loginDriverStateEventManager

    private val _registerDriverStateEventManager = default<Register>()
    override val registerDriverStateEventManager: StateEventManager<Register>
        get() = _registerDriverStateEventManager

    private val _customerAllStateEventManager = default<List<Customer>>()
    override val customerAllStateEventManager: StateEventManager<List<Customer>>
        get() = _customerAllStateEventManager

    private val _customerStateEventManager = default<Customer>()
    override val customerStateEventManager: StateEventManager<Customer>
        get() = _customerStateEventManager

    private val _driverAllStateEventManager = default<List<Driver>>()
    override val driverAllStateEventManager: StateEventManager<List<Driver>>
        get() = _driverAllStateEventManager

    private val _driverStateEventManager = default<Driver>()
    override val driverStateEventManager: StateEventManager<Driver>
        get() = _driverStateEventManager

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
            .collect(_loginDriverStateEventManager)
    }

    override suspend fun registerDriver(registerRequest: RegisterRequest) {
        networkSource.registerDriver(registerRequest)
            .collect(_registerDriverStateEventManager)
    }

    override suspend fun customerAll() {
        networkSource.customerAll()
            .collect(_customerAllStateEventManager)
    }

    override suspend fun customer() {
        networkSource.customer()
            .collect(_customerStateEventManager)
    }

    override suspend fun driverAll() {
        networkSource.driverAll()
            .collect(_driverAllStateEventManager)
    }

    override suspend fun driver() {
        networkSource.driver()
            .collect(_driverStateEventManager)
    }
}