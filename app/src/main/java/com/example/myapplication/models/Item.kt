package com.example.myapplication.models

data class Item(
    val id: Int,
    val name:String,
    val description:String,
    val locationId: Int

) : java.io.Serializable

data class ItemList(
    val entities: List<Tipo>
)

data class ItemAPI(
    val entity: Item
)

data class ItemDeleteAPI(
    val status: String
)
