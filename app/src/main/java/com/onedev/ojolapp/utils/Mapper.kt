package com.onedev.ojolapp.utils

import com.onedev.ojolapp.core.domain.model.Customer
import com.onedev.ojolapp.core.domain.model.Driver
import com.onedev.ojolapp.core.domain.model.Login
import com.onedev.ojolapp.core.domain.model.Register
import com.onedev.ojolapp.core.network.response.*

object Mapper {
    fun LoginResponse?.mapLoginResponse(): Login {
        return Login(this?.data?.token)
    }

    fun RegisterResponse.mapRegisterResponse(): Register {
        return Register(status)
    }

    fun CustomerAllResponse?.mapCustomerAllResponse(): List<Customer> {
        val mapper : (CustomerAllResponse.Data?) -> Customer = {
            Customer(
                id = it?.id,
                name = it?.name,
                username = it?.username,
                role = it?.role
            )
        }
        return this?.data?.map(mapper).orEmpty()
    }

    fun CustomerResponse.mapCustomer(): Customer {
        return Customer(data?.id, data?.name, data?.username, data?.role)
    }

    fun DriverAllResponse?.mapDriverAllResponse(): List<Driver> {
        val mapper : (DriverAllResponse.Data?) -> Driver = {
            Driver(
                id = it?.id,
                name = it?.name,
                username = it?.username,
                role = it?.role
            )
        }
        return this?.data?.map(mapper).orEmpty()
    }

    fun DriverResponse.mapDriver(): Driver {
        return Driver(data?.id, data?.name, data?.username, data?.role)
    }
}