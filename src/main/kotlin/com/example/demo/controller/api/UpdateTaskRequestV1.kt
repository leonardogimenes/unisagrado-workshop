package com.example.demo.controller.api

data class UpdateTaskRequestV1(
    val title: String?,
    val description: String?,
    val completed: Boolean?
)
