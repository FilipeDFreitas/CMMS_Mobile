package com.example.myapplication.models

data class Token(
    val userId :Int,
    val token :String,
    val tokenExpirationDate :String
)
