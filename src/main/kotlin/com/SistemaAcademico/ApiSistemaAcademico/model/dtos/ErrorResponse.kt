package com.SistemaAcademico.ApiSistemaAcademico.model.dtos

class ErrorResponse(

    val httpCode: Int,
    val message: String,
    val internalCode: String,
    val errors: List<FieldErrorResponse>?
)