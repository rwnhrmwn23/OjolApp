package com.onedev.ojolapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onedev.ojolapp.core.domain.model.Driver
import com.onedev.ojolapp.core.repository.OjolRepository
import com.onedev.ojolapp.event.StateEventSubscriber
import com.onedev.ojolapp.utils.convertEventToSubscriber
import kotlinx.coroutines.launch
import org.koin.core.annotation.Scope

@Scope(MainFragment::class)
class DriverViewModel(
    private val ojolRepository: OjolRepository
) : ViewModel(){

    private val driverAllManager = ojolRepository.driverAllStateEventManager
    private val driverAllScope = driverAllManager.createScope(viewModelScope)

    fun subscribeAllDriver(subscriber: StateEventSubscriber<List<Driver>>) {
        convertEventToSubscriber(driverAllManager, subscriber)
    }

    fun driverAll() = driverAllScope.launch {
        ojolRepository.driverAll()
    }

    private val driverManager = ojolRepository.driverStateEventManager
    private val driverScope = driverManager.createScope(viewModelScope)

    fun subscribeDriver(subscriber: StateEventSubscriber<Driver>) {
        convertEventToSubscriber(driverManager, subscriber)
    }

    fun driver() = driverScope.launch {
        ojolRepository.driver()
    }
}