package com.onedev.ojolapp.ui.register.driver

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onedev.ojolapp.core.domain.model.Register
import com.onedev.ojolapp.core.network.response.RegisterRequest
import com.onedev.ojolapp.core.repository.OjolRepository
import com.onedev.ojolapp.event.StateEventSubscriber
import com.onedev.ojolapp.utils.convertEventToSubscriber
import kotlinx.coroutines.launch
import org.koin.core.annotation.Scope

@Scope(RegisterDriverFragment::class)
class RegisterDriverViewModel(
    private val ojolRepository: OjolRepository
) : ViewModel(){

    private val registerDriverManager = ojolRepository.registerStateEventManager
    private val registerDriverScope = registerDriverManager.createScope(viewModelScope)

    fun subscribeRegisterCustomer(subscriber: StateEventSubscriber<Register>) {
        convertEventToSubscriber(registerDriverManager, subscriber)
    }

    fun registerDriver(registerRequest: RegisterRequest) = registerDriverScope.launch {
        ojolRepository.registerDriver(registerRequest)
    }
}