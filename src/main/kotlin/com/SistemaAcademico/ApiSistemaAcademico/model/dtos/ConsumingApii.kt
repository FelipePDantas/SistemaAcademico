package com.SistemaAcademico.ApiSistemaAcademico.model.dtos

import com.SistemaAcademico.ApiSistemaAcademico.exception.TimeoutException
import org.slf4j.LoggerFactory
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Component
import org.springframework.web.client.HttpClientErrorException
import org.springframework.web.client.ResourceAccessException
import org.springframework.web.client.RestTemplate
import java.util.UUID

import javax.naming.ServiceUnavailableException

@Component
class ConsumingApii(
    private val restTemplate: RestTemplate
) {
    private val log = LoggerFactory.getLogger(javaClass)
    fun getIdCheck(id: UUID): Boolean? {
        return try {
            log.info("m=getIdCheck, stage=init, i=get_id_check, msg= consultando o ID na API Institui\u00E7\u00E3o., instituicaoID ${id}")
            val url = "http://localhost:8081/institutions/check/${id}"
            val responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                object : ParameterizedTypeReference<Boolean>() {})
            log.info("m=getIdCheck, stage=finished, i=get_id_check, msg= consulta realizada com sucesso., instituicaoID ${id}\"")
            return responseEntity.body!!

        } catch (ex: Exception) {
            log.info("m=getIdCheck, stage= error, i=get_id_check, msg= ocorreu um erro ao consumir a API de Institui\u00E7\u00E3o")
            when (ex) {
                is ResourceAccessException ->
                    throw TimeoutException("API FORA DO AR ", "T004")
                is HttpClientErrorException -> {
                    when (ex.statusCode) {
                        HttpStatus.BAD_REQUEST, HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.NOT_FOUND -> throw ServiceUnavailableException(
                            "${ex.message}"
                        )

                        else -> throw Exception("Erro ao consultar Institui\u00E7\u00E3o ${ex.message}")
                    }
                }

                else -> throw ex

            }
        }
    }
}