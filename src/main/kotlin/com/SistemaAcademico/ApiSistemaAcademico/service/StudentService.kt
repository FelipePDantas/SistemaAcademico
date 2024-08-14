package com.SistemaAcademico.ApiSistemaAcademico.service

import com.SistemaAcademico.ApiSistemaAcademico.model.Student
import com.SistemaAcademico.ApiSistemaAcademico.model.dtos.ConsumingApii
import com.SistemaAcademico.ApiSistemaAcademico.model.dtos.StudentRequest
import com.SistemaAcademico.ApiSistemaAcademico.repository.StudentRepository
import lombok.extern.log4j.Log4j2
import org.hibernate.query.sqm.tree.SqmNode.log
import org.springframework.stereotype.Service
import java.util.Optional
import java.util.UUID
import kotlin.math.log2

@Service
@Log4j2
class StudentService(
    val studentRepository: StudentRepository
) {

    fun create(student: Student) {
        studentRepository.save(student)
    }

    fun getAll(): List<Student> {
        var students = studentRepository.findAll()
        return students
    }

    fun getById(id: UUID): Optional<Student> {
        var studentExist = studentRepository.findById(id)
        return studentExist
    }

    fun deleteById(id: UUID) {
        studentRepository.deleteById(id)
    }
}