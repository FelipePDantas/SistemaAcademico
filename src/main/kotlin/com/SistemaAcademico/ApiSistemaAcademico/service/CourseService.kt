package com.SistemaAcademico.ApiSistemaAcademico.service

import com.SistemaAcademico.ApiSistemaAcademico.exception.NotFoundException
import com.SistemaAcademico.ApiSistemaAcademico.model.Course
import com.SistemaAcademico.ApiSistemaAcademico.model.dtos.CourseRequest
import com.SistemaAcademico.ApiSistemaAcademico.repository.CourseRepository
import lombok.extern.log4j.Log4j2
import org.hibernate.query.sqm.tree.SqmNode.log
import org.springframework.stereotype.Service
import java.util.Optional
import java.util.UUID

@Service
@Log4j2
class CourseService(
    val courseRepository: CourseRepository
) {

    fun create(course: Course){

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