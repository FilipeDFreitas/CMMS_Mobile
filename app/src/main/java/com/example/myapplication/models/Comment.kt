package com.example.myapplication.models

data class Comment(
    val id: String,
    val id_owner: String,
    val titulo: String,
    val comentario: String,
    val data:String

) : java.io.Serializable
