package br.com.jonasdev.api_example.domain.model.customer

import br.com.jonasdev.api_example.domain.model.address.Address
import br.com.jonasdev.api_example.domain.model.address.AddressType
import kotlin.test.Test
import kotlin.test.assertEquals

class CustomerDomainTest {

    @Test
    fun `Should have multiple addresses per type`() {
        val customerDomain = CustomerDomain(
            id = 1,
            name = "Jonas",
            companyName = "JonasDev",
            customerType = CustomerType.PHYSICAL,
            documentRegistration = "123456789",
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

        assertEquals(1, customerDomain.id)

    }

}