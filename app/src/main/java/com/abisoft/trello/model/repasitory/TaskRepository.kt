package com.abisoft.trello.model.repasitory

import com.abisoft.trello.model.TokenManager
import com.abisoft.trello.model.api.TaskService
import com.abisoft.trello.model.data.Task

class TaskRepository(private val taskService: TaskService, private val tokenManager: TokenManager) {

    suspend fun getAllTasks(): List<Task>? {
        val token = tokenManager.getToken() // Tokenni olish
        return if (token != null) {
            val response = taskService.getAllTasks("Bearer $token")
            if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
        } else {
            null
        }
    }
}
