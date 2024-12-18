package br.com.jonasdev.api_example.domain.model.address

data class Address(
    val id: Long? = null,
    val street: String,
    val number: String,
    val complement: String? = null,
    val neighborhood: String,
    val city: String,
    val state: String,
    val country: String,
    val zipCode: String,
    val type: AddressType
)