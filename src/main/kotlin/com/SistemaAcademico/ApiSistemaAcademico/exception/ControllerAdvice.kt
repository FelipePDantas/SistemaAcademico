package com.SistemaAcademico.ApiSistemaAcademico.exception

import com.SistemaAcademico.ApiSistemaAcademico.model.dtos.ErrorResponse
import com.SistemaAcademico.ApiSistemaAcademico.model.dtos.FieldErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

@ControllerAdvice
class ControllerAdvice {

    @ExceptionHandler(NotFoundException::class)
    fun handleException(ex: NotFoundException, request: WebRequest): ResponseEntity<ErrorResponse> {

        var err = ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            ex.message,
            ex.errorCode,
            null
        )
        return ResponseEntity(err, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequestException(ex: BadRequestException, request: WebRequest): ResponseEntity<ErrorResponse> {

        var err = ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            "Invalid Request",
            ex.errorCode,
            null
        )
        return ResponseEntity(err, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handlerMethodArgumentNotValidException(
        ex: MethodArgumentNotValidException,
        request: WebRequest
    ): ResponseEntity<ErrorResponse> {

        var err = ErrorResponse(
            HttpStatus.UNPROCESSABLE_ENTITY.value(),
            "Invalid Request",
            "CR001",
            ex.bindingResult.fieldErrors.map { FieldErrorResponse(it.defaultMessage ?: "ivalid", it.field) }
        )
        return ResponseEntity(err, HttpStatus.UNPROCESSABLE_ENTITY)


    }
}