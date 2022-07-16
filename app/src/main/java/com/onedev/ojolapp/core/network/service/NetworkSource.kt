package com.onedev.ojolapp.core.network.service

import com.onedev.ojolapp.core.domain.model.Customer
import com.onedev.ojolapp.core.domain.model.Driver
import com.onedev.ojolapp.core.domain.model.Login
import com.onedev.ojolapp.core.domain.model.Register
import com.onedev.ojolapp.core.network.response.LoginRequest
import com.onedev.ojolapp.core.network.response.RegisterRequest
import com.onedev.ojolapp.utils.FlowState
import com.onedev.ojolapp.utils.Mapper.mapCustomer
import com.onedev.ojolapp.utils.Mapper.mapCustomerAllResponse
import com.onedev.ojolapp.utils.Mapper.mapDriver
import com.onedev.ojolapp.utils.Mapper.mapDriverAllResponse
import com.onedev.ojolapp.utils.Mapper.mapLoginResponse
import com.onedev.ojolapp.utils.Mapper.mapRegisterResponse
import com.onedev.ojolapp.utils.asFlowStateEvent
import org.koin.core.annotation.Single

@Single
class NetworkSource(
    private val webServiceProvider: WebServiceProvider,
) {

    suspend fun loginCustomer(request: LoginRequest): FlowState<Login> {
        return webServiceProvider.get().loginCustomer(request).asFlowStateEvent {
            it.mapLoginResponse()
        }
    }

    suspend fun registerCustomer(request: RegisterRequest): FlowState<Register> {
        return webServiceProvider.get().registerCustomer(request).asFlowStateEvent {
            it.mapRegisterResponse()
        }
    }

    suspend fun loginDriver(request: LoginRequest): FlowState<Login> {
        return webServiceProvider.get().loginDriver(request).asFlowStateEvent {
            it.mapLoginResponse()
        }
    }

    suspend fun registerDriver(request: RegisterRequest): FlowState<Register> {
        return webServiceProvider.get().registerDriver(request).asFlowStateEvent {
            it.mapRegisterResponse()
        }
    }

    suspend fun customerAll(): FlowState<List<Customer>> {
        return webServiceProvider.getWithToken().customerAll().asFlowStateEvent {
            it.mapCustomerAllResponse()
        }
    }

    suspend fun customer(): FlowState<Customer> {
        return webServiceProvider.getWithToken().customer().asFlowStateEvent {
            it.mapCustomer()
        }
    }

    suspend fun driverAll(): FlowState<List<Driver>> {
        return webServiceProvider.getWithToken().driverAll().asFlowStateEvent {
            it.mapDriverAllResponse()
        }
    }

    suspend fun driver(): FlowState<Driver> {
        return webServiceProvider.getWithToken().driver().asFlowStateEvent {
            it.mapDriver()
        }
    }

}