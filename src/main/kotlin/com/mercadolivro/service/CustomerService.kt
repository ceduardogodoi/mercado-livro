package com.mercadolivro.service

import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.CustomerRepository
import org.springframework.stereotype.Service

@Service
class CustomerService(
    val customerRepository: CustomerRepository
) {

    fun getAll(name: String?): List<CustomerModel> {
        name?.let {
            return this.customerRepository.findByNameContainingIgnoreCase(it)
        }
        return this.customerRepository.findAll().toList()
    }

    fun create(customer: CustomerModel) {
        this.customerRepository.save(customer)
    }

    fun getCustomer(id: Int): CustomerModel {
        return this.customerRepository.findById(id).orElseThrow()
    }

    fun update(customer: CustomerModel) {
        if (!this.customerRepository.existsById(customer.id!!)) {
            throw Exception()
        }
        this.customerRepository.save(customer)
    }

    fun delete(id: Int) {
        if (!this.customerRepository.existsById(id)) {
            throw Exception()
        }
        this.customerRepository.deleteById(id)
    }
}
