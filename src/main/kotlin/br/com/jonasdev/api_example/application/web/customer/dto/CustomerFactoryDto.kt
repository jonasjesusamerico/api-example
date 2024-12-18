package br.com.jonasdev.api_example.application.web.customer.dto

import br.com.jonasdev.api_example.domain.model.customer.CustomerType
import br.com.jonasdev.api_example.domain.model.address.Address
import br.com.jonasdev.api_example.domain.model.address.AddressType
import br.com.jonasdev.api_example.domain.model.customer.CustomerDomain
import br.com.jonasdev.api_example.domain.representation.customer.CustomerDomainRepresentation

fun CustomerResponse.fromDomain(domain: CustomerDomainRepresentation) = CustomerResponse(
    id = domain.id,
    name = domain.name,
    companyName = domain.companyName,
    customerType = domain.customerType.name,
    documentRegistration = domain.documentRegistration
)

fun CustomerResponse.toDomain() = CustomerDomain(
    id = this.id,
    name = this.name,
    companyName = this.companyName,
    customerType = CustomerType.valueOf(this.customerType),
    documentRegistration = this.documentRegistration,
    addresses = setOf(
        Address(
            id = 1,
            street = "Rua 1",
            number = "123",
            neighborhood = "Bairro 1",
            city = "Cidade 1",
            state = "Estado 1",
            country = "País 1",
            zipCode = "12345678",
            type = AddressType.HOME
        )
    )
)

fun CustomerRequest.toDomain() = CustomerDomain(
    id = this.id,
    name = this.name,
    companyName = this.companyName,
    customerType = CustomerType.valueOf(this.customerType),
    documentRegistration = this.documentRegistration,
    addresses = setOf(
        Address(
            id = 1,
            street = "Rua 1",
            number = "123",
            neighborhood = "Bairro 1",
            city = "Cidade 1",
            state = "Estado 1",
            country = "País 1",
            zipCode = "12345678",
            type = AddressType.HOME
        )
    )
)
