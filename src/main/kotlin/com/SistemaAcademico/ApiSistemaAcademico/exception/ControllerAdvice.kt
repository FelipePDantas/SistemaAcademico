package com.SistemaAcademico.ApiSistemaAcademico.exception

import com.SistemaAcademico.ApiSistemaAcademico.model.dtos.ErrorResponse
import com.SistemaAcademico.ApiSistemaAcademico.model.dtos.FieldErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest
import javax.naming.ServiceUnavailableException

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
            HttpStatus.CONFLICT.value(),
            ex.message,
            ex.errorCode,
            null
        )
        return ResponseEntity(err, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(TimeoutException::class)
    fun timeoutException(ex: TimeoutException, request: WebRequest): ResponseEntity<ErrorResponse> {

        var err = ErrorResponse(
            HttpStatus.CONFLICT.value(),
            ex.message,
            ex.errorCode,
            null
        )
        return ResponseEntity(err, HttpStatus.CONFLICT)
    }

    @ExceptionHandler(TechnicalExecpetion::class)
    fun timeoutException(ex: TechnicalExecpetion, request: WebRequest): ResponseEntity<ErrorResponse> {

        var err = ErrorResponse(
            HttpStatus.CONFLICT.value(),
            ex.message,
            ex.errorCode,
            null
        )
        return ResponseEntity(err, HttpStatus.CONFLICT)
    }

    @ExceptionHandler(ServiceUnavailableException::class)
    fun serviceUnavailableException(
        ex: ServiceUnavailableException,
        request: WebRequest
    ): ResponseEntity<ErrorResponse> {

        var err = ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            "Erro na consulta",
            "EN:100",
            null
        )
        return ResponseEntity(err, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(IdDoesNotExistException::class)
    fun handleIdDoesNotExist(ex: IdDoesNotExistException, request: WebRequest): ResponseEntity<ErrorResponse> {

        var err = ErrorResponse(
            HttpStatus.NOT_FOUND.value(),
            ex.message,
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