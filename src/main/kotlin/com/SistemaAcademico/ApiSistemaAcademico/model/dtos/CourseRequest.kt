package com.SistemaAcademico.ApiSistemaAcademico.model.dtos

import com.SistemaAcademico.ApiSistemaAcademico.enums.CourseTypeEnum
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal
import java.util.*

class CourseRequest(

    @field:NotEmpty(message = "Name not empty")
    val name: String,

    @field:NotNull(message = "Price not null")
    val monthlyCost: BigDecimal,

    val type: CourseTypeEnum,

    val institutionId: UUID
)