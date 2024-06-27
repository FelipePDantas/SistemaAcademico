package com.SistemaAcademico.ApiSistemaAcademico.service

import com.SistemaAcademico.ApiSistemaAcademico.model.Course
import com.SistemaAcademico.ApiSistemaAcademico.repository.CourseRepository
import org.springframework.stereotype.Service
import java.util.Optional
import java.util.UUID

@Service
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

    fun getById(id: UUID): Optional<Course>{
        return courseRepository.findById(id)
    }

    fun deleteById(id: UUID){
        courseRepository.deleteById(id)
    }


}