package com.example.androidfirebaserealtimedatabasee

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.insert).setOnClickListener {
            startActivity(Intent(this,InsertionActivity::class.java))
        }
        findViewById<Button>(R.id.fetch).setOnClickListener {
            startActivity(Intent(this,ListOfEmploy::class.java))
        }
    }
}