package br.com.jonasdev.api_example.domain.representation.customer

interface CustomerEntityRepresentation {

    var id: Long?
    var name: String
    var companyName: String?
    var customerType: String
    var documentRegistration: String

}
