package com.example.myapplication.models


data class Incident(

    val id: String,
    val titulo: String,
    val tipo: String,
    val local: String,
    val equipamento: String,
    val status: String,
    val data:String

    ) : java.io.Serializable



