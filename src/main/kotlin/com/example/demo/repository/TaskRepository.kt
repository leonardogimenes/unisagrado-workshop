package com.example.demo.repository

import com.example.demo.model.Task
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface TaskRepository : MongoRepository<Task, UUID> {

    fun findTaskById(id: UUID): Task?
}