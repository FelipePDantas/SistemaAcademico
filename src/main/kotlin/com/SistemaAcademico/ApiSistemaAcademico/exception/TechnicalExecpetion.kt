package com.SistemaAcademico.ApiSistemaAcademico.exception

class TechnicalExecpetion(override val message: String, val errorCode: String): Exception() {
}