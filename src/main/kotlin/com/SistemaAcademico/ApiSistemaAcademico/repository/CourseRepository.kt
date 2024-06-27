package com.SistemaAcademico.ApiSistemaAcademico.repository

import com.SistemaAcademico.ApiSistemaAcademico.model.Course
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface CourseRepository : JpaRepository<Course, UUID> {
}