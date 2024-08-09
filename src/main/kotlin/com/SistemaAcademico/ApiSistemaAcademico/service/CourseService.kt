package com.SistemaAcademico.ApiSistemaAcademico.service

import com.SistemaAcademico.ApiSistemaAcademico.exception.IdDoesNotExistException
import com.SistemaAcademico.ApiSistemaAcademico.exception.NotFoundException
import com.SistemaAcademico.ApiSistemaAcademico.exception.TimeoutException
import com.SistemaAcademico.ApiSistemaAcademico.model.Course
import com.SistemaAcademico.ApiSistemaAcademico.model.Institution
import com.SistemaAcademico.ApiSistemaAcademico.repository.CourseRepository
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.client.HttpServerErrorException
import org.springframework.web.client.ResourceAccessException
import org.springframework.web.client.RestTemplate
import java.util.UUID
import javax.naming.ServiceUnavailableException

@Service
class CourseService(
    private val courseRepository: CourseRepository,
    private val restTemplate: RestTemplate,
) {

    fun getAllInstitution(): List<Institution> {
        val url = "http://localhost:8081/institutions"
        val responseEntity = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            object : ParameterizedTypeReference<List<Institution>>() {}
        )
        return responseEntity.body ?: emptyList()
    }

    fun create(course: Course) {

        val verifyId = getIdCheck(course.institutionId)

        if (verifyId == true) {

            courseRepository.save(course)

        } else {

            throw IdDoesNotExistException("não existe esse ID -${course.institutionId}", "ID001")
        }
    }

    fun getAll(): List<Course> {
        var courseList = courseRepository.findAll()
        return courseList
    }

    fun getById(id: UUID): Course {
        return courseRepository.findById(id).orElseThrow { NotFoundException("Não existe esse ${id}", "C001") }
    }

    fun update(id: UUID, course: Course) {

        val idExist =
            courseRepository.findById(id).orElseThrow { NotFoundException("Esse id ${id} não foi encontrado", "C003") }
        idExist.monthlyCost = course.monthlyCost
        courseRepository.save(idExist)

    }

    fun deleteById(id: UUID) {
        courseRepository.deleteById(id)
    }

    fun getIdCheck(id: UUID): Boolean? {
        return try {
            val url = "http://localhost:8081/institutions/check/${id}"
            val responseEntity = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                object : ParameterizedTypeReference<Boolean>() {})
            return responseEntity.body!!
        } catch (ex: Exception) {
            ex.printStackTrace()
            when (ex) {
                is ResourceAccessException ->
                    throw TimeoutException("API FORA DO AR ", "T004")


                is HttpServerErrorException -> {
                    when (ex.statusCode) {
                        HttpStatus.BAD_REQUEST, HttpStatus.INTERNAL_SERVER_ERROR,HttpStatus.NOT_FOUND -> throw ServiceUnavailableException(
                            "${ex.message}"
                        )

                        else -> throw Exception("Erro ao consultar Instituição ${ex.message}")
                    }
                }

                else -> throw ex

            }
        }
    }
}
