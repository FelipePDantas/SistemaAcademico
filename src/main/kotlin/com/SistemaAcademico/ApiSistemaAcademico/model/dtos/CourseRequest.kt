package com.SistemaAcademico.ApiSistemaAcademico.model.dtos

import com.SistemaAcademico.ApiSistemaAcademico.enums.CourseTypeEnum
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal
import java.util.*

data class CourseRequest(

    @field:NotEmpty(message = "Name not empty")
    var name: String,

    @field:NotNull(message = "Price not null")
    var monthlyCost: BigDecimal,

    var type: CourseTypeEnum,

    var institution: UUID
)