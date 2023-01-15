package com.example.myapplication.base

import com.example.myapplication.models.Incident

interface PostClickHandler {
    fun clickedPostItem(item: Incident)
}