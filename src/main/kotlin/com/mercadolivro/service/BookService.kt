package com.mercadolivro.service

import com.mercadolivro.enums.BookStatus
import com.mercadolivro.model.BookModel
import com.mercadolivro.repository.BookRepository
import org.springframework.stereotype.Service

@Service
class BookService(
    private val bookRepository: BookRepository
) {

    fun create(book: BookModel) {
        this.bookRepository.save(book)
    }

    fun findAll(): List<BookModel> {
        return this.bookRepository.findAll().toList()
    }

    fun findActives(): List<BookModel> {
        return this.bookRepository.findByStatus(BookStatus.ATIVO)
    }

    fun findById(id: Int): BookModel {
        return this.bookRepository.findById(id).orElseThrow()
    }

    fun update(book: BookModel) {
        this.bookRepository.save(book)
    }

    fun delete(id: Int) {
        val book = this.findById(id)
        book.status = BookStatus.CANCELADO
        this.update(book)
    }

}
