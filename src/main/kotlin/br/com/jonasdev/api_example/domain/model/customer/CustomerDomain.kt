package br.com.jonasdev.api_example.domain.model.customer

import br.com.jonasdev.api_example.domain.model.address.Address
import br.com.jonasdev.api_example.domain.representation.customer.CustomerDomainRepresentation

data class CustomerDomain(
    override val id: Long? = null,
    override val name: String,
    override val companyName: String? = null,
    override val customerType: CustomerType,
    override val documentRegistration: String,
    override val addresses: Set<Address>,
) : CustomerDomainRepresentation
