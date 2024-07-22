package com.SistemaAcademico.ApiSistemaAcademico.service

import com.SistemaAcademico.ApiSistemaAcademico.exception.NotFoundException
import com.SistemaAcademico.ApiSistemaAcademico.model.Course
import com.SistemaAcademico.ApiSistemaAcademico.model.Institution
import com.SistemaAcademico.ApiSistemaAcademico.repository.CourseRepository
import org.springframework.stereotype.Service
import org.springframework.web.client.RestTemplate
import org.springframework.web.client.getForObject
import java.util.UUID

@Service

class CourseService(
    val courseRepository: CourseRepository,
    val restTemplate: RestTemplate
) {

    fun getInstitution(id: UUID): Institution? {
        val url = "http://localhost:8081/institutions/$id"
       return restTemplate.getForObject(url, Institution::class.java)
    }


    fun create(course: Course, id: UUID){
        val url = "http://localhost:8081/institutions/$id"
        var idInstitution = restTemplate.getForObject(url,Institution::class.java)
        course.institution = idInstitution!!.id
        courseRepository.save(course)
    }

    fun getAll(): List<Course>{
      var courseList =  courseRepository.findAll()
        return courseList
    }

    fun getById(id: UUID): Course{
        return courseRepository.findById(id).orElseThrow{ NotFoundException("Não existe esse ${id}","C001")}
    }

    fun deleteById(id: UUID){
        courseRepository.deleteById(id)
    }



}