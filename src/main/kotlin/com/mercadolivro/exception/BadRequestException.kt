package com.mercadolivro.exception

import org.springframework.http.HttpStatus

class BadRequestException(
    override val message: String,
    val errorCode: String
) : Exception()