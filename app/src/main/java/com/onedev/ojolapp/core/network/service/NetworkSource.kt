package com.onedev.ojolapp.core.network.service

import com.onedev.ojolapp.core.domain.model.Login
import com.onedev.ojolapp.core.domain.model.Register
import com.onedev.ojolapp.core.network.response.LoginRequest
import com.onedev.ojolapp.core.network.response.RegisterRequest
import com.onedev.ojolapp.utils.FlowState
import com.onedev.ojolapp.utils.Mapper
import com.onedev.ojolapp.utils.asFlowStateEvent
import org.koin.core.annotation.Single

@Single
class NetworkSource(private val webServiceProvider: WebServiceProvider) {

    suspend fun loginCustomer(request: LoginRequest): FlowState<Login> {
        return webServiceProvider.get().loginCustomer(request).asFlowStateEvent {
            Mapper.mapLoginResponse(it)
        }
    }

    suspend fun registerCustomer(request: RegisterRequest): FlowState<Register> {
        return webServiceProvider.get().registerCustomer(request).asFlowStateEvent {
            Mapper.mapRegisterResponse(it)
        }
    }

    suspend fun loginDriver(request: LoginRequest): FlowState<Login> {
        return webServiceProvider.get().loginDriver(request).asFlowStateEvent {
            Mapper.mapLoginResponse(it)
        }
    }

    suspend fun registerDriver(request: RegisterRequest): FlowState<Register> {
        return webServiceProvider.get().registerDriver(request).asFlowStateEvent {
            Mapper.mapRegisterResponse(it)
        }
    }

}