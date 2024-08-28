package com.SistemaAcademico.ApiSistemaAcademico.service

import com.SistemaAcademico.ApiSistemaAcademico.enums.CourseTypeEnum
import com.SistemaAcademico.ApiSistemaAcademico.exception.IdDoesNotExistException
import com.SistemaAcademico.ApiSistemaAcademico.exception.NotFoundException
import com.SistemaAcademico.ApiSistemaAcademico.model.Course
import com.SistemaAcademico.ApiSistemaAcademico.model.dtos.ConsumingApii
import com.SistemaAcademico.ApiSistemaAcademico.repository.CourseRepository
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.Mockito.mock
import org.springframework.web.client.RestTemplate
import java.math.BigDecimal
import java.util.*
import kotlin.test.Test

@ExtendWith(MockKExtension::class)
class CourseServiceTest{
    @MockK
    private lateinit var courseRepository: CourseRepository
    @MockK
    private lateinit var consumingApii: ConsumingApii
    @InjectMockKs
    private lateinit var courseService: CourseService

    @Test
    fun `should return all courses`(){

        val fakeCourses = listOf(buildCourse(),buildCourse())
        every { courseRepository.findAll() } returns fakeCourses
        val courses = courseService.getAll()
        assertEquals(fakeCourses,courses)
        verify(exactly = 1) { courseRepository.findAll() }
    }

    @Test
    fun `should return course by id`(){
        val id = UUID.randomUUID()
        val fakeCourse = buildCourse(id = id)
        every { courseRepository.findById(id) } returns Optional.of(fakeCourse)

        val course = courseService.getById(id)

        assertEquals(fakeCourse, course)
        verify (exactly = 1){ courseRepository.findById(id) }
    }
    @Test
    fun `should throw error when course not found`(){
        val id = UUID.randomUUID()

        every { courseRepository.findById(id) } returns Optional.empty()

        val error = assertThrows<NotFoundException> { courseService.getById(id) }

        assertEquals("C001",error.errorCode)

        verify (exactly = 1){ courseRepository.findById(id) }

    }
    @Test
    fun `should throw error when courseId not found`() {
        val fakeCourse = buildCourse()
        every { consumingApii.getIdCheck(fakeCourse.id) } returns false
        every { courseRepository.save(any())} returns  fakeCourse

        assertThrows(IdDoesNotExistException::class.java) {
            courseRepository.save(fakeCourse)
        }

        verify(exactly = 0) { courseRepository.save(any()) }
        verify(exactly = 1){consumingApii.getIdCheck(fakeCourse.institutionId)}
    }
    fun buildCourse(
        id: UUID = UUID.randomUUID(),
        name: String = "course name",
        monthlyCost:BigDecimal = BigDecimal("3333323"),
        institutionId: UUID = UUID.randomUUID()
    ) = Course(
        id= id,
        name = name,
        monthlyCost = monthlyCost,
        institutionId = institutionId,
        type = CourseTypeEnum.GRADUATION
    )
}