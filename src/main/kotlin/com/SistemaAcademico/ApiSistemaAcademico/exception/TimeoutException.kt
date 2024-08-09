package com.SistemaAcademico.ApiSistemaAcademico.exception

class TimeoutException(override val message: String, val errorCode: String): Exception(){
}