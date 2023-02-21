package com.example.myapplication.models


data class Incident(

    val id: String,
    val title: String,
    val description: String,
    val startDate: String,
    val endDate: String,
    val status: String,
    val incidentTypeId: Int,
    val incidentTypeName: String,
    val locationId: Int,
    val locationName: String,
    val itemId: Int,
    val itemName: String,
    val userId: Int,
    val userName: String,
    val adminId: Int,
    val adminName: String,
    val totalInteractions: Int

    ) : java.io.Serializable



