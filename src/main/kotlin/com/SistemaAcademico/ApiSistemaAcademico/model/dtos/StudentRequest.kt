package com.SistemaAcademico.ApiSistemaAcademico.model.dtos

import com.fasterxml.jackson.annotation.JsonAlias
import java.util.UUID

class StudentRequest(

    val nome: String,

    val email: String?,

    @JsonAlias("course_id")
    val courseId: UUID
)