package com.abisoft.trello.model.api

import com.abisoft.trello.model.AuthResponse
import com.abisoft.trello.model.LoginRequest
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService  {
    @POST("api/v2/auth/login")
    suspend fun login(
        @Body loginRequest: LoginRequest
    ): Response<AuthResponse>
}