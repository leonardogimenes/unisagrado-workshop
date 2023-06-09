package com.example.demo.controller

import com.example.demo.controller.api.CreateTaskRequestV1
import com.example.demo.controller.api.UpdateTaskRequestV1
import com.example.demo.model.Task
import com.example.demo.service.TaskService
import com.example.demo.service.dto.CreateTaskDTO
import com.example.demo.service.dto.UpdateTaskDTO
import org.springframework.data.domain.PageRequest
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.server.ResponseStatusException
import java.util.UUID

@RestController
@RequestMapping("/tasks")
class TaskControllerV1(
    private val taskService: TaskService
) {

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@RequestBody request: CreateTaskRequestV1) =
        taskService.create(CreateTaskDTO(title = request.title, description = request.description))

    @GetMapping
    fun readAll(
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "2") size: Int
    ) = taskService.readAll(PageRequest.of(page, size))

    @GetMapping("/{id}")
    fun readTask(@PathVariable id: UUID): Task =
        taskService.readById(id) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteTask(@PathVariable id: UUID) {
        taskService.deleteTask(id)
    }

    @PatchMapping("/{id}")
    fun updateTask(@PathVariable id: UUID, @RequestBody request: UpdateTaskRequestV1): Task =
        taskService.updateTask(
            UpdateTaskDTO(
                id = id,
                title = request.title,
                description = request.description,
                completed = request.completed
            )
        ) ?: throw ResponseStatusException(HttpStatus.NOT_FOUND)
}
