package com.abisoft.trello.model

import com.abisoft.trello.model.data.User

data class AuthResponse(
    val token: String,
    val user: User
)
