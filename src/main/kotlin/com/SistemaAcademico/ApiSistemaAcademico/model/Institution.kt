package com.SistemaAcademico.ApiSistemaAcademico.model

import java.util.UUID

data class Institution(

    val id: UUID,
    val name: String,
    val numberContact: Int

)