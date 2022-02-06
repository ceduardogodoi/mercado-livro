package com.mercadolivro.service

import com.mercadolivro.controller.request.PostCustomerRequest
import com.mercadolivro.controller.request.PutCustomerRequest
import com.mercadolivro.model.CustomerModel
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.ResponseStatus

@Service
class CustomerService {

    val customers = mutableListOf<CustomerModel>()

    fun getAll(name: String?): List<CustomerModel> {
        name?.let {
            return this.customers.filter { it.name.contains(name, true) }
        }
        return this.customers
    }

    fun create(customer: PostCustomerRequest) {
        val id = if (this.customers.isEmpty()) {
            1
        } else {
            this.customers.last().id.toInt() + 1
        }.toString()

        this.customers.add(CustomerModel(id, customer.name, customer.email))
    }

    fun getCustomer(id: String): CustomerModel {
        return this.customers.first { it.id == id }
    }

    fun update(id: String, customer: PutCustomerRequest) {
        this.customers.first { it.id == id }.let {
            it.name = customer.name
            it.email = customer.email
        }
    }

    fun delete(id: String) {
        this.customers.removeIf { it.id == id }
    }
}
