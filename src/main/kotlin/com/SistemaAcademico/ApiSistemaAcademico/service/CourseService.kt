package com.SistemaAcademico.ApiSistemaAcademico.service

import com.SistemaAcademico.ApiSistemaAcademico.exception.NotFoundException
import com.SistemaAcademico.ApiSistemaAcademico.model.Course
import com.SistemaAcademico.ApiSistemaAcademico.model.Institution
import com.SistemaAcademico.ApiSistemaAcademico.repository.CourseRepository
import org.slf4j.LoggerFactory
import org.springframework.core.ParameterizedTypeReference
import org.springframework.http.HttpMethod
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.exchange
import org.springframework.web.client.getForObject
import org.springframework.web.server.ResponseStatusException
import java.util.UUID

@Service

class CourseService(
    val courseRepository: CourseRepository,
    val restTemplate: RestTemplate,
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
        val url = "http://localhost:8081/institutions/check/${course.institutionId}"
        val responseEntity = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            object : ParameterizedTypeReference<Boolean>() {}
        )
        if (responseEntity.body == true) {
            courseRepository.save(course)
        } else {
            throw ResponseStatusException(HttpStatus.NOT_FOUND)
        }
    }

    fun getAll(): List<Course> {
        var courseList = courseRepository.findAll()
        return courseList
    }

    fun getById(id: UUID): Course {
        return courseRepository.findById(id).orElseThrow { NotFoundException("Não existe esse ${id}", "C001") }
    }

    fun deleteById(id: UUID) {
        courseRepository.deleteById(id)
    }


}