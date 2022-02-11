package com.mercadolivro.service

import com.mercadolivro.model.CustomerModel
import org.springframework.stereotype.Service

@Service
class CustomerService {

    val customers = mutableListOf<CustomerModel>()

    fun getAll(name: String?): List<CustomerModel> {
        name?.let {
            return this.customers.filter { it.name.contains(name, true) }
        }
        return this.customers
    }

    fun create(customer: CustomerModel) {
        val id = if (this.customers.isEmpty()) {
            1
        } else {
            this.customers.last().id!!.toInt().plus(1)
        }

        customer.id = id

        this.customers.add(customer)
    }

    fun getCustomer(id: Int): CustomerModel {
        return this.customers.first { it.id == id }
    }

    fun update(customer: CustomerModel) {
        this.customers.first { it.id == customer.id }.let {
            it.name = customer.name
            it.email = customer.email
        }
    }

    fun delete(id: Int) {
        this.customers.removeIf { it.id == id }
    }
}
