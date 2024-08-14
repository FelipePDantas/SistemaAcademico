package com.SistemaAcademico.ApiSistemaAcademico.exception

class QueryErrorException(override val message: String, val errorCode: String): Exception() {
}