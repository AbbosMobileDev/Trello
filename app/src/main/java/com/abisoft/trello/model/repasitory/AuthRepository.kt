package com.abisoft.trello.model.repasitory

import com.abisoft.trello.model.AuthResponse
import com.abisoft.trello.model.LoginRequest
import com.abisoft.trello.model.RetrofitInstance
import com.abisoft.trello.model.TokenManager
import retrofit2.Response

class AuthRepository(private val tokenManager: TokenManager) {

    suspend fun login(loginRequest: LoginRequest): Response<AuthResponse> {
        val response = RetrofitInstance.taskService.login(loginRequest)
        if (response.isSuccessful) {
            response.body()?.token?.let { token ->
                tokenManager.saveToken(token) // Tokenni saqlash
            }
        }
        return response
    }

    // Tokenni olish
    fun getToken(): String? {
        return tokenManager.getToken()
    }

    // Tokenni tozalash
    fun clearToken() {
        tokenManager.clearToken()
    }
}
