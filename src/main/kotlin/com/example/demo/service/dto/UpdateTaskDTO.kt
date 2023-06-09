package com.example.demo.service.dto

import java.util.UUID

data class UpdateTaskDTO(
    val id: UUID,
    val title: String?,
    val description: String?,
    val completed: Boolean?
)