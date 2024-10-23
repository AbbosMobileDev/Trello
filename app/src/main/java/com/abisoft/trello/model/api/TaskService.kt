package com.abisoft.trello.model.api

import com.abisoft.trello.model.AuthResponse
import com.abisoft.trello.model.LoginRequest
import com.abisoft.trello.model.data.Task
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

interface TaskService {
    @POST("api/v2/auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<AuthResponse>

        @GET("api/v1/task/get_all_tasks")
        suspend fun getAllTasks(
            @Header("Authorization") token: String // Bearer token
        ): Response<List<Task>>
}