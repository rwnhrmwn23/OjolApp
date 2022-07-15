package com.onedev.ojolapp.ui.register.customer

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onedev.ojolapp.core.domain.model.Register
import com.onedev.ojolapp.core.network.response.RegisterRequest
import com.onedev.ojolapp.core.repository.OjolRepository
import com.onedev.ojolapp.event.StateEventSubscriber
import com.onedev.ojolapp.utils.convertEventToSubscriber
import kotlinx.coroutines.launch
import org.koin.core.annotation.Scope

@Scope(RegisterCustomerFragment::class)
class RegisterCustomerViewModel(
    private val ojolRepository: OjolRepository
) : ViewModel(){

    private val registerCustomerManager = ojolRepository.registerStateEventManager
    private val registerCustomerScope = registerCustomerManager.createScope(viewModelScope)

    fun subscribeRegisterCustomer(subscriber: StateEventSubscriber<Register>) {
        convertEventToSubscriber(registerCustomerManager, subscriber)
    }

    fun registerCustomer(registerRequest: RegisterRequest) = registerCustomerScope.launch {
        ojolRepository.registerCustomer(registerRequest)
    }
}