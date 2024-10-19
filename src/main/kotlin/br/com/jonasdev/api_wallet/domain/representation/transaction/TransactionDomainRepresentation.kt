package br.com.jonasdev.api_wallet.domain.representation.transaction

import java.math.BigDecimal
import java.time.LocalDate

interface TransactionDomainRepresentation {

    val id: Long?

    var purchaseDate: LocalDate

    var description: String

    var sector: String

    var paymentMethod: String

    var amount: BigDecimal

    var status: Boolean

}
