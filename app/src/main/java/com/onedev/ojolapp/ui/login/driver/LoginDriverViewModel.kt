package com.onedev.ojolapp.ui.login.driver

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onedev.ojolapp.core.domain.model.Login
import com.onedev.ojolapp.core.network.response.LoginRequest
import com.onedev.ojolapp.core.repository.OjolRepository
import com.onedev.ojolapp.event.StateEventSubscriber
import com.onedev.ojolapp.utils.convertEventToSubscriber
import kotlinx.coroutines.launch
import org.koin.core.annotation.Scope

@Scope(LoginDriverFragment::class)
class LoginDriverViewModel(
    private val ojolRepository: OjolRepository
) : ViewModel(){

    private val loginDriverManager = ojolRepository.loginDriverStateEventManager
    private val loginDriverScope = loginDriverManager.createScope(viewModelScope)

    fun subscribeLoginDriver(subscriber: StateEventSubscriber<Login>) {
        convertEventToSubscriber(loginDriverManager, subscriber)
    }

    fun loginDriver(loginRequest: LoginRequest) = loginDriverScope.launch {
        ojolRepository.loginDriver(loginRequest)
    }
}