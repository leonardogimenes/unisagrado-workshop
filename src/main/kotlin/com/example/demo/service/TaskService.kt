package com.example.demo.service

import com.example.demo.model.Task
import com.example.demo.repository.TaskRepository
import com.example.demo.service.dto.CreateTaskDTO
import com.example.demo.service.dto.UpdateTaskDTO
import org.springframework.data.domain.PageRequest
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class TaskService(
    private val taskRepository: TaskRepository
) {

    fun create(dto: CreateTaskDTO): Task {
        val task = Task(title = dto.title, description = dto.description)
        taskRepository.save(task)
        return task
    }

    fun readAll(page: PageRequest) = taskRepository.findAll(page)

    fun readById(id: UUID): Task? = taskRepository.findTaskById(id)

    fun deleteTask(id: UUID) {
        taskRepository.deleteById(id)
    }

    fun updateTask(dto: UpdateTaskDTO): Task? =
        taskRepository.findTaskById(dto.id)?.run {
            taskRepository.save(
                this.copy(
                    title = dto.title ?: this.title,
                    description = dto.description ?: this.description,
                    completed = dto.completed ?: this.completed
                )
            )
        }
}

