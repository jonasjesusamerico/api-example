package br.com.jonasdev.api_example.application.web.customer.dto

import br.com.jonasdev.api_example.domain.model.address.Address

data class CustomerRequest(
    val id: Long? = null,
    val name: String,
    val companyName: String? = null,
    val customerType: String,
    val documentRegistration: String,
//    val addresses: Set<AddressRequest>
)
