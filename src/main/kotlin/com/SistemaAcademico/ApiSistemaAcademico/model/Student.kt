package com.SistemaAcademico.ApiSistemaAcademico.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.JoinColumn
import jakarta.persistence.ManyToOne
import java.util.UUID

@Entity
class Student(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val id: UUID,

    var nome: String,

    var email: String,

    @ManyToOne
    @JoinColumn(name = "course_id")
    var course: Course? = null



)