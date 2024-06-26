package com.SistemaAcademico.ApiSistemaAcademico.service

import com.SistemaAcademico.ApiSistemaAcademico.model.Student
import com.SistemaAcademico.ApiSistemaAcademico.repository.StudentRepository
import org.springframework.stereotype.Service
import java.util.Optional
import java.util.UUID

@Service
class StudentService(
    val studentRepository: StudentRepository
) {

    fun create(student: Student){

        studentRepository.save(student)

    }
    fun getAll(): List<Student>{
      var students = studentRepository.findAll()
        return students
    }

    fun getById(id: UUID): Optional<Student>{
        var studentExist = studentRepository.findById(id)
        return studentExist
    }

    fun deleteById(id: UUID){
        studentRepository.deleteById(id)
    }
}