package com.example.myapplication.models

data class User(
    val id: Int,
    val name: String,
    val phone: String,
    val document: String,
    val address: String,
    val email: String,
    val role: Int
    )

data class UserAPI(
    val entity: User
)
