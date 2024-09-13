package br.com.jonasdev.api_wallet.application.web.transaction

import br.com.jonasdev.api_wallet.domain.transaction.TransactionDomain
import java.math.BigDecimal
import java.time.LocalDate

class TransactionInputDto(
    val purchaseDate: LocalDate,
    val description: String,
    val sector: String,
    val paymentMethod: String,
    val amount: BigDecimal,
    val status: Boolean
) {
    fun toDomain() : TransactionDomain {
        return TransactionDomain(
            purchaseDate = purchaseDate,
            description = description,
            sector = sector,
            paymentMethod = paymentMethod,
            amount = amount,
            status = status
        )
    }
}
