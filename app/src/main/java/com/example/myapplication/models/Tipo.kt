package com.example.myapplication.models

data class Tipo(
    val id: Int,
    val name:String,
    val description:String

) : java.io.Serializable

data class TipoAPI(
    val entities: List<Tipo>
)

data class TipoDeleteAPI(
    val status: String
)
