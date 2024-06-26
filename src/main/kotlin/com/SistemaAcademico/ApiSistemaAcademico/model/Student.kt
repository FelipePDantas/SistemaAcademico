package com.SistemaAcademico.ApiSistemaAcademico.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.util.UUID

@Entity
data class Student(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID,

    var nome: String,

    var Email: String


)