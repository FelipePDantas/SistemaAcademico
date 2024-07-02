package com.SistemaAcademico.ApiSistemaAcademico.extension

import com.SistemaAcademico.ApiSistemaAcademico.model.Course
import com.SistemaAcademico.ApiSistemaAcademico.model.Student
import com.SistemaAcademico.ApiSistemaAcademico.model.dtos.CourseRequest
import com.SistemaAcademico.ApiSistemaAcademico.model.dtos.StudentRequest
import java.util.*

fun StudentRequest.toStudentModel(course1: Course): Student{
    return Student(UUID.randomUUID(),nome = this.nome,email =this.email, course = course1)
}

fun CourseRequest.toCourseModel() : Course{
    return Course(UUID.randomUUID(),name= this.name,monthlyCost = this.monthlyCost,type = this.type)
}