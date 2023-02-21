package com.example.myapplication.conn

import com.example.myapplication.models.*
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Header

import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ConnService {

    @POST("auth")
    fun login(@Body l :Login) : Call<Token>
    //USERS

    @GET("user")
    fun getUserList(@Header("Authorization")token : String) : Call<TipoAPI>

    @GET("user/{user_id}")
    fun getUser(@Header("Authorization")token : String,@Path(value = "user_id") id : Int) : Call<UserAPI>

    @PUT("user")
    fun putUser(@Header("Authorization")token : String, @Body u: User) : Call<UserAPI>

    @POST("user")
    fun postUser(@Header("Authorization")token : String, @Body u: User) : Call<UserAPI>

    @DELETE("user/{id}")
    fun deleteUser(@Header("Authorization")token : String,@Path(value = "id") id : Int) : Call<TipoDeleteAPI>

    //TIPOS

    @GET("incident-type")
    fun getTipo(@Header("Authorization")token : String) : Call<TipoAPI>

    @PUT("incident-type")
    fun putTipo(@Header("Authorization")token : String, @Body t: Tipo) : Call<TipoAPI>

    @DELETE("incident-type/{id}")
    fun deleteTipo(@Header("Authorization")token : String,@Path(value = "id") id : Int) : Call<TipoDeleteAPI>

    @POST("incident-type")
    fun postTipo(@Header("Authorization")token : String,@Body t: Tipo) : Call<TipoAPI>

    //EQUIPAMENTOS

    @GET("item")
    fun getEquipList(@Header("Authorization")token : String) : Call<ItemList>

    @GET("item/{id}")
    fun getEquip(@Header("Authorization")token : String,@Path(value = "id") id : Int) : Call<ItemAPI>

    @PUT("item")
    fun putEquip(@Header("Authorization")token : String, @Body t: Item) : Call<ItemList>

    @DELETE("item/{id}")
    fun deleteEquip(@Header("Authorization")token : String,@Path(value = "id") id : Int) : Call<ItemDeleteAPI>

    @POST("item")
    fun postEquip(@Header("Authorization")token : String,@Body t: Item) : Call<ItemList>


    //LOCAIS

    @GET("location")
    fun getLocal(@Header("Authorization")token : String) : Call<LocalAPI>

    @PUT("location")
    fun putLocal(@Header("Authorization")token : String, @Body t: Local) : Call<LocalAPI>

    @DELETE("location/{id}")
    fun deleteLocal(@Header("Authorization")token : String,@Path(value = "id") id : Int) : Call<LocalDeleteAPI>

    @POST("location")
    fun postLocal(@Header("Authorization")token : String,@Body t: Local) : Call<LocalAPI>
}