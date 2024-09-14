package br.com.jonasdev.api_wallet.application.web.transaction.dto

import java.math.BigDecimal
import java.time.LocalDate

class TransactionResponseDto(
    val id : Long?,
    val purchaseDate: LocalDate,
    val description: String,
    val sector: String,
    val paymentMethod: String,
    val amount: BigDecimal,
    val status: Boolean
) {
    companion object
}