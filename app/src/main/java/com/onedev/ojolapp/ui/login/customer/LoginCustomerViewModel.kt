package com.onedev.ojolapp.ui.login.customer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onedev.ojolapp.core.domain.model.Login
import com.onedev.ojolapp.core.network.response.LoginRequest
import com.onedev.ojolapp.core.repository.OjolRepository
import com.onedev.ojolapp.event.StateEventSubscriber
import com.onedev.ojolapp.utils.convertEventToSubscriber
import kotlinx.coroutines.launch
import org.koin.core.annotation.Scope

@Scope(LoginCustomerFragment::class)
class LoginCustomerViewModel(
    private val ojolRepository: OjolRepository
) : ViewModel(){

    private val loginCustomerManager = ojolRepository.loginCustomerStateEventManager
    private val loginCustomerScope = loginCustomerManager.createScope(viewModelScope)

    fun subscribeLoginCustomer(subscriber: StateEventSubscriber<Login>) {
        convertEventToSubscriber(loginCustomerManager, subscriber)
    }

    fun loginCustomer(loginRequest: LoginRequest) = loginCustomerScope.launch {
        ojolRepository.loginCustomer(loginRequest)
    }
}