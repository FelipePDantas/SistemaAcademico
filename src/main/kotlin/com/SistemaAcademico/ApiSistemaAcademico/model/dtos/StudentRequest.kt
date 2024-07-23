package com.SistemaAcademico.ApiSistemaAcademico.model.dtos

import com.fasterxml.jackson.annotation.JsonAlias
import java.util.*

data class StudentRequest(

    var nome: String,

    var email: String?,

    @JsonAlias("course_id")
    var courseId: UUID
)