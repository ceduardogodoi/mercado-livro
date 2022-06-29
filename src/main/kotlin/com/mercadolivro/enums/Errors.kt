package com.mercadolivro.enums

enum class Errors(val code: String, val message: String) {
    ML001("ML-001", "Invalid request"),
    ML101("ML-101", "Book [%s] does not exist"),
    ML102("ML-102", "Cannot update book with status [%s]"),
    ML201("ML-201", "Customer [%s] does not exist"),
    ML301("ML-301", "Cannot purchase a book which is not active")
}