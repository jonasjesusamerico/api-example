package br.com.jonasdev.api_wallet.domain.configuration.pageable

import kotlin.math.ceil

class InternCorePageableImpl<T>(
    private val content: List<T>,
    private val pageNumber: Int,
    private val pageSize: Int,
    private val totalElements: Long,
    private val sort: String
) :
    InternPageable<T> {
    override fun content(): List<T> {
        return content
    }

    override fun pageNumber(): Int {
        return pageNumber
    }

    override fun pageSize(): Int {
        return pageSize
    }

    override fun totalElements(): Long {
        return totalElements
    }


    override fun totalPages(): Int {
        return ceil(totalElements.toDouble() / pageSize).toInt()
    }

    override fun hasNext(): Boolean {
        return pageNumber < totalPages() - 1
    }

    override fun hasPrevious(): Boolean {
        return pageNumber > 0
    }

    override fun sort(): String {
        return sort
    }
}
