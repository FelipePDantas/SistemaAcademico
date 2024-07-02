package com.SistemaAcademico.ApiSistemaAcademico.exception

class NotFoundException(override val message: String,val errorCode: String): Exception() {
}