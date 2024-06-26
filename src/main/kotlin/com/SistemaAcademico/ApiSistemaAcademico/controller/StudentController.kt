package com.SistemaAcademico.ApiSistemaAcademico.controller

import com.SistemaAcademico.ApiSistemaAcademico.model.Student
import com.SistemaAcademico.ApiSistemaAcademico.service.StudentService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/student")
class StudentController(
    val studentService: StudentService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody student: Student){
        studentService.create(student)
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAll(): List<Student>{
       return studentService.getAll()
    }

    @GetMapping("/{id}")
    fun getById(@PathVariable id: UUID): Student? {
        var studentExist = studentService.getById(id).orElseThrow()
        return  studentExist
    }
}