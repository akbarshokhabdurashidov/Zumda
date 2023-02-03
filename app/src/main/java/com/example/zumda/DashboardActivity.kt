package com.example.zumda

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class DashboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        val welcomeText = "Welcome " + intent.getStringExtra("email") + "!"
        val tvWelcome = findViewById<TextView>(R.id.tvWelcome)
        tvWelcome.text = welcomeText
    }
}