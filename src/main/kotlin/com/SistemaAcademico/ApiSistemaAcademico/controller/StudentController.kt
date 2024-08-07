package com.SistemaAcademico.ApiSistemaAcademico.controller

import com.SistemaAcademico.ApiSistemaAcademico.extension.toStudentModel
import com.SistemaAcademico.ApiSistemaAcademico.model.Student
import com.SistemaAcademico.ApiSistemaAcademico.model.dtos.StudentRequest
import com.SistemaAcademico.ApiSistemaAcademico.service.CourseService
import com.SistemaAcademico.ApiSistemaAcademico.service.StudentService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.Optional
import java.util.UUID

@RestController
@RequestMapping("/student")
class StudentController(
    val studentService: StudentService,
    val courseService: CourseService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody @Valid student: StudentRequest) {
        val course = courseService.getById(student.courseId)
        studentService.create(student.toStudentModel(course))
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAll(): List<Student> {
        return studentService.getAll()
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    fun getById(@PathVariable id: UUID): Student? {
        var studentExist = studentService.getById(id).orElseThrow()
        return studentExist
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteById(@PathVariable id: UUID) {
        studentService.deleteById(id)
    }
}