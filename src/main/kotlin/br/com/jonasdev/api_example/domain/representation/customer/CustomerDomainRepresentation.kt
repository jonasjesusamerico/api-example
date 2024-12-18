package br.com.jonasdev.api_example.domain.representation.customer

import br.com.jonasdev.api_example.domain.model.customer.CustomerType
import br.com.jonasdev.api_example.domain.model.address.Address

interface CustomerDomainRepresentation {

    val id: Long?
    val name: String
    val companyName: String?
    val customerType: CustomerType
    val documentRegistration: String
    val addresses: Set<Address>





}
