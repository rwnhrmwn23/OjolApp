package com.onedev.ojolapp.core.network.service

import org.koin.core.annotation.Single

@Single
class WebServiceProvider {
    fun get(): WebService {
        return WebService.build()
    }
}