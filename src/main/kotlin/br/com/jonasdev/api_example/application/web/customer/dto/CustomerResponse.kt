package br.com.jonasdev.api_example.application.web.customer.dto

import br.com.jonasdev.api_example.domain.representation.customer.CustomerDomainRepresentation

data class CustomerResponse(
    val id: Long? = null,
    val name: String,
    val companyName: String? = null,
    val customerType: String,
    val documentRegistration: String
) {
    companion object {
        fun fromDomain(domain: CustomerDomainRepresentation) = CustomerResponse(
            id = domain.id,
            name = domain.name,
            companyName = domain.companyName,
            customerType = domain.customerType.name,
            documentRegistration = domain.documentRegistration
        )
    }
}
