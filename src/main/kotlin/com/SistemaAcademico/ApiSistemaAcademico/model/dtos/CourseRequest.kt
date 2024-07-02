package com.SistemaAcademico.ApiSistemaAcademico.model.dtos

import com.SistemaAcademico.ApiSistemaAcademico.enums.CourseTypeEnum
import java.math.BigDecimal

data class CourseRequest (

    var name: String,

    var monthlyCost: BigDecimal,

    var type: CourseTypeEnum
)