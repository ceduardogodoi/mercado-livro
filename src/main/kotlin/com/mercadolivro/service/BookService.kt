package com.mercadolivro.service

import com.mercadolivro.enums.BookStatus
import com.mercadolivro.exception.NotFoundException
import com.mercadolivro.model.BookModel
import com.mercadolivro.model.CustomerModel
import com.mercadolivro.repository.BookRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service

@Service
class BookService(
    private val bookRepository: BookRepository
) {

    fun create(book: BookModel) {
        this.bookRepository.save(book)
    }

    fun findAll(pageable: Pageable): Page<BookModel> {
        return this.bookRepository.findAll(pageable)
    }

    fun findActives(pageable: Pageable): Page<BookModel> {
        return this.bookRepository.findByStatus(BookStatus.ATIVO, pageable)
    }

    fun findById(id: Int): BookModel {
        return this.bookRepository.findById(id).orElseThrow { NotFoundException("Book [$id] does not exist", "ML-0001") }
    }

    fun update(book: BookModel) {
        this.bookRepository.save(book)
    }

    fun delete(id: Int) {
        val book = this.findById(id)
        book.status = BookStatus.CANCELADO
        this.update(book)
    }

    fun deleteByCustomer(customer: CustomerModel) {
        val books = this.bookRepository.findByCustomer(customer)
        for (book in books) {
            book.status = BookStatus.DELETADO
        }
        this.bookRepository.saveAll(books)
    }

}
