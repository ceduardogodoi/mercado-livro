package com.mercadolivro.service

import com.mercadolivro.enums.BookStatus
import com.mercadolivro.enums.Errors
import com.mercadolivro.events.PurchaseEvent
import com.mercadolivro.exception.BadRequestException
import com.mercadolivro.model.PurchaseModel
import com.mercadolivro.repository.PurchaseRepository
import org.springframework.context.ApplicationEventPublisher
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import javax.transaction.Transactional

@Service
class PurchaseService(
    private val purchaseRepository: PurchaseRepository,
    private val bookService: BookService,
    private val applicationEventPublisher: ApplicationEventPublisher
) {

    @Transactional
    fun create(purchaseModel: PurchaseModel) {
        val booksIds = purchaseModel.books.mapNotNull { it.id }.toSet()
        val books = this.bookService.findAllByIds(booksIds)
        val hasOnlyActiveBooks = books.mapNotNull { it.status }.all { it == BookStatus.ATIVO }
        if (!hasOnlyActiveBooks) {
            throw BadRequestException(Errors.ML301.message, Errors.ML301.code)
        }

        this.purchaseRepository.save(purchaseModel)

        println("Disparando evento de compra")
        applicationEventPublisher.publishEvent(PurchaseEvent(this, purchaseModel))
        println("Finalização do processamento")
    }

    fun update(purchaseModel: PurchaseModel) {
        this.purchaseRepository.save(purchaseModel)
    }
}
