package com.example.myapplication.conn

import com.example.myapplication.models.Token

interface ConnResponse<T> {
    fun success(t:T)

    fun fail ()

    fun error(err : String)
}