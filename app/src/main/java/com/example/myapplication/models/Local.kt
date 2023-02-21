package com.example.myapplication.models

data class Local(
    val id: Int,
    val name:String,
    val description:String

) : java.io.Serializable

data class LocalAPI(
    val entities: List<Tipo>
)

data class LocalDeleteAPI(
    val status: String
)