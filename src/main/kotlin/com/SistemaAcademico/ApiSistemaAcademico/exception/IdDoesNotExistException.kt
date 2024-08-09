package com.SistemaAcademico.ApiSistemaAcademico.exception

class IdDoesNotExistException(override val message: String, val errorCode: String): Exception(){
}