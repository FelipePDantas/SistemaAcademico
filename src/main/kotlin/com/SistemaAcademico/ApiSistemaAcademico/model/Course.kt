package com.SistemaAcademico.ApiSistemaAcademico.model

import com.SistemaAcademico.ApiSistemaAcademico.enums.CourseTypeEnum
import jakarta.persistence.*
import java.math.BigDecimal
import java.util.UUID

@Entity
data class Course(
// Primeira dúvida é como eu vou fazer o join se eu não tenho a entidade no meu banco, pensei em colocar só o id para fazer isso mas ai já surgiu a dúvida de como vou fazer a verificação quando for criar o objeto no post do course.
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID,

    val name: String,

    val monthlyCost: BigDecimal,

    @Enumerated(EnumType.STRING)
    var type: CourseTypeEnum,

    var institution: UUID






)