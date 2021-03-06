package com.onedev.ojolapp.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.onedev.ojolapp.core.domain.model.Customer
import com.onedev.ojolapp.core.repository.OjolRepository
import com.onedev.ojolapp.event.StateEventSubscriber
import com.onedev.ojolapp.utils.convertEventToSubscriber
import kotlinx.coroutines.launch
import org.koin.core.annotation.Scope

@Scope(MainFragment::class)
class CustomerViewModel(
    private val ojolRepository: OjolRepository
) : ViewModel(){

    private val customerAllManager = ojolRepository.customerAllStateEventManager
    private val customerAllScope = customerAllManager.createScope(viewModelScope)

    fun subscribeAllCustomer(subscriber: StateEventSubscriber<List<Customer>>) {
        convertEventToSubscriber(customerAllManager, subscriber)
    }

    fun customerAll() = customerAllScope.launch {
        ojolRepository.customerAll()
    }

    private val customerManager = ojolRepository.customerStateEventManager
    private val customerScope = customerManager.createScope(viewModelScope)

    fun subscribeCustomer(subscriber: StateEventSubscriber<Customer>) {
        convertEventToSubscriber(customerManager, subscriber)
    }

    fun customer() = customerScope.launch {
        ojolRepository.customer()
    }
}