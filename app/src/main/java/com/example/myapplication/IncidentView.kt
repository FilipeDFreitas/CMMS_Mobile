package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView

class IncidentView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_incident_view)

        val incident = intent.getSerializableExtra("inc") as Incident
        val txt = findViewById<TextView>(R.id.textTitle)
        txt.text = incident.id
    }
}