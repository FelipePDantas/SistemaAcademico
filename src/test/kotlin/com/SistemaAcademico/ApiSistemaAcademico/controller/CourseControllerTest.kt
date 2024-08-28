package com.SistemaAcademico.ApiSistemaAcademico.controller

import com.SistemaAcademico.ApiSistemaAcademico.enums.CourseTypeEnum
import com.SistemaAcademico.ApiSistemaAcademico.model.Course
import com.SistemaAcademico.ApiSistemaAcademico.model.dtos.ConsumingApii
import com.SistemaAcademico.ApiSistemaAcademico.model.dtos.CourseRequest
import com.SistemaAcademico.ApiSistemaAcademico.repository.CourseRepository
import com.fasterxml.jackson.databind.ObjectMapper
import io.mockk.every
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import java.math.BigDecimal
import java.util.*

@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration
@ActiveProfiles("test")
class CourseControllerTest{

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var courseRepository: CourseRepository
    @Autowired
    private lateinit var objectMapper: ObjectMapper
    @BeforeEach
    fun setup() = courseRepository.deleteAll()

    @Test
    fun `should return all customers when get all`(){
        val course1 = courseRepository.save(buildCourse())
        val course2 = courseRepository.save(buildCourse())

        mockMvc.perform(get("/course"))
            .andExpect(status().isOk)
//            .andExpect(jsonPath("$[0].id").value(course1.id))
            .andExpect(jsonPath("$[0].name").value(course1.name))
//            .andExpect(jsonPath("$[0].istitutionId").value(course1.institutionId))
            .andExpect(jsonPath("$[0].type").value(course1.type.name))
    }

    @Test
    fun `should create course`() {
        val request = CourseRequest("fake course", BigDecimal(5555), CourseTypeEnum.GRADUATION, UUID.fromString("207e0559-74f1-45ed-8f46-5fea8cfddcaf"))
        mockMvc.perform(
            post("/course")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(request)))
            .andExpect(status().isCreated)

        val courses = courseRepository.findAll().toList()
        assertEquals(1,courses.size)
        assertEquals(request.name, courses[0].name)
    }
    fun buildCourse(
        id: UUID = UUID.randomUUID(),
        name: String = "course name",
        monthlyCost: BigDecimal = BigDecimal("3333323"),
        institutionId: UUID = UUID.randomUUID()
    ) = Course(
        id= id,
        name = name,
        monthlyCost = monthlyCost,
        institutionId = institutionId,
        type = CourseTypeEnum.GRADUATION
    )
}