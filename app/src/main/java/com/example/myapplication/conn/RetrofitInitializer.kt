package com.example.myapplication.conn

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetrofitInitializer {

        private val retrofit = Retrofit.Builder()
                                        //.baseUrl("http://192.168.68.114:3000/")           //local
                                        .baseUrl("http://168.138.252.48:3000/")             //web
                                        .addConverterFactory(GsonConverterFactory.create())
                                        .build()


    fun connService () = retrofit.create(ConnService::class.java)

}