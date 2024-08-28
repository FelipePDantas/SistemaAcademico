package com.SistemaAcademico.ApiSistemaAcademico.service

import com.SistemaAcademico.ApiSistemaAcademico.exception.IdDoesNotExistException
import com.SistemaAcademico.ApiSistemaAcademico.exception.NotFoundException
import com.SistemaAcademico.ApiSistemaAcademico.exception.TechnicalExecpetion
import com.SistemaAcademico.ApiSistemaAcademico.model.Course
import com.SistemaAcademico.ApiSistemaAcademico.model.dtos.ConsumingApii
import com.SistemaAcademico.ApiSistemaAcademico.repository.CourseRepository
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import java.util.UUID


@Service
class CourseService(
    private val courseRepository: CourseRepository,
    private val consumingApii: ConsumingApii
) {
    private val log = LoggerFactory.getLogger(javaClass)

    fun create(course: Course) {
//        try {
            log.info("m=create, stage=init, i=create, msg= Iniciando o met\u00F3do create da entidade curso.")
            val verifyId = consumingApii.getIdCheck(course.institutionId)
            if (verifyId == true) {
                courseRepository.save(course)
                log.info("m=create, stage=finished, i=create, msg= chamada do met\u00F3do create com sucesso.")
            } else {
                log.warn("m=create, stage=error, i=create, msg= chamada do met\\u00F3do create com Id inv\u00E1lido. ${course.institutionId}\"")
                throw IdDoesNotExistException("não existe esse ID -${course.institutionId}", "ID001")
            }
//        } catch (e: Exception) {
//            throw TechnicalExecpetion("Erro na chamada do met\u00F3do", "C001")
//        }
    }

    fun getAll(): List<Course> {
        try {
            log.info("m=getAll, stage=init, i=get_all, msg= Iniciando o metódo getAll da entidade curso")
            val courseList = courseRepository.findAll()
            log.info("m=getAll, stage=finished, i=get_all, msg= chamada do met\u00F3do getAll da entidade curso")
            return courseList
        } catch (e: Exception) {
            log.warn("m=getAll, stage=error, i=get_all, msg= erro ao chamar o met\u00F3do getAll")
            throw TechnicalExecpetion("Erro na chamada do met\u00F3do", "G001")
        }
    }

    fun getById(id: UUID): Course {
            return courseRepository.findById(id).orElseThrow { NotFoundException("N\u00E3o existe esse ${id}", "C001") }
    }

    fun update(id: UUID, course: Course) {
        try {
            val idExist =
                courseRepository.findById(id)
                    .orElseThrow { NotFoundException("Esse id ${id} não foi encontrado", "C003") }
            idExist.monthlyCost = course.monthlyCost
            courseRepository.save(idExist)
        } catch (e: Exception) {
            throw TechnicalExecpetion("Erro na chamada do met\u00F3do", "U001")
        }
    }

    fun deleteById(id: UUID){
        try {
            courseRepository.deleteById(id)
        } catch (e: Exception) {
            throw TechnicalExecpetion("Erro na chamada do met\u00F3do", "D001")
        }
    }
}
