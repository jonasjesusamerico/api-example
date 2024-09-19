package br.com.jonasdev.api_wallet.library.pageable

import br.com.jonasdev.api_wallet.domain.configuration.pageable.InternPageable
import com.fasterxml.jackson.annotation.JsonProperty
import kotlin.math.ceil

class InternPageableImpl<T> : InternPageable<T> {
    private var content: List<T>? = null
    private val pageNumber: Int
    private val pageSize: Int
    private var totalElements: Long = 0
    private val sort: String

    constructor(pageNumber: Int, pageSize: Int, sort: String) {
        this.pageNumber = pageNumber
        this.pageSize = pageSize
        this.sort = sort
    }

    constructor(content: List<T>?, pageNumber: Int, pageSize: Int, totalElements: Long, sort: String) {
        this.content = content
        this.pageNumber = pageNumber
        this.pageSize = pageSize
        this.totalElements = totalElements
        this.sort = sort
    }


    @JsonProperty("content")
    override fun content(): List<T>? {
        return content
    }

    @JsonProperty("pageNumber")
    override fun pageNumber(): Int {
        return pageNumber
    }

    @JsonProperty("pageSize")
    override fun pageSize(): Int {
        return pageSize
    }

    @JsonProperty("totalElements")
    override fun totalElements(): Long {
        return totalElements
    }

    @JsonProperty("totalPages")
    override fun totalPages(): Int {
        return ceil(totalElements.toDouble() / pageSize).toInt()
    }

    @JsonProperty("hasNext")
    override fun hasNext(): Boolean {
        return pageNumber < totalPages() - 1
    }

    @JsonProperty("hasPrevious")
    override fun hasPrevious(): Boolean {
        return pageNumber > 0
    }

    @JsonProperty("sort")
    override fun sort(): String {
        return sort
    }
}
