package com.SistemaAcademico.ApiSistemaAcademico.controller

import com.SistemaAcademico.ApiSistemaAcademico.extension.toCourseModel
import com.SistemaAcademico.ApiSistemaAcademico.model.Course
import com.SistemaAcademico.ApiSistemaAcademico.model.Institution
import com.SistemaAcademico.ApiSistemaAcademico.model.dtos.CourseRequest
import com.SistemaAcademico.ApiSistemaAcademico.service.CourseService
import jakarta.validation.Valid
import jakarta.websocket.server.PathParam
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.Optional
import java.util.UUID

@RestController
@RequestMapping("/course")
class CourseController(
    val courseService: CourseService
) {
    // Pensei em antes de fazer o Post fazer um compareTo com o Id lá da outra api
    @PostMapping("/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid courseRequest: CourseRequest,@PathVariable id: UUID){
        var map = hashMapOf("id" to ""+id )

        courseService.create(courseRequest.toCourseModel(id))
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAll(): List<Course>{
      return courseService.getAll()
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getById(@PathVariable id: UUID): Course{
      return courseService.getById(id)
    }

    fun put(){}

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(@PathVariable id: UUID){
        courseService.deleteById(id)
    }

    @GetMapping("/institutions/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getInstituions(@PathVariable id: UUID): Institution? {
        return courseService.getInstitution(id)
    }
}