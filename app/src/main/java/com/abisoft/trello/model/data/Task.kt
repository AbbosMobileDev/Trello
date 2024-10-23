package com.abisoft.trello.model.data

data class Task(
    val id: Int,
    val title: String,
    val status: String, // "New", "In progress", "In review", "Done"
    val index: Int // Ustun ichidagi tartib raqami
)
