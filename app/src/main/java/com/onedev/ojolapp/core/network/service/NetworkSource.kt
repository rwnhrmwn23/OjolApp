package com.onedev.ojolapp.core.network.service

import com.onedev.ojolapp.core.domain.model.Login
import com.onedev.ojolapp.core.network.response.LoginRequest
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

}