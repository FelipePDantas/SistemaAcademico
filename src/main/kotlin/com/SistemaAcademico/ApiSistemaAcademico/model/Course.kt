package com.SistemaAcademico.ApiSistemaAcademico.model

import com.SistemaAcademico.ApiSistemaAcademico.enums.CourseTypeEnum
import jakarta.persistence.*
import java.math.BigDecimal
import java.util.UUID

@Entity
 class Course(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID,

    val name: String,

    var monthlyCost: BigDecimal,

    @Enumerated(EnumType.STRING)
    val type: CourseTypeEnum,

    val institutionId: UUID


)