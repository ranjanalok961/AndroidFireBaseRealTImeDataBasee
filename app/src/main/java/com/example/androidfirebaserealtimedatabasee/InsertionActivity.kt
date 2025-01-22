package com.example.androidfirebaserealtimedatabasee

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.FirebaseDatabase

class InsertionActivity : AppCompatActivity() {
    private lateinit var name : EditText
    private lateinit var age : EditText
    private lateinit var salary : EditText
    private lateinit var insert : Button
    private lateinit var db : FirebaseDatabase

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_insertion)

        name = findViewById(R.id.name)
        age = findViewById(R.id.age)
        salary = findViewById(R.id.salary)
        insert = findViewById(R.id.insert)

        db = FirebaseDatabase.getInstance()

        insert.setOnClickListener {
            val dbRef = db.getReference("emp")
            val id = dbRef.push().key.toString()
            val data = Emp(age.text.toString().toIntOrNull(),id,name.text.toString() ,salary.text.toString().toDoubleOrNull() ?: 0.0)
            dbRef.child(id).setValue(data)
                .addOnSuccessListener {
                    Log.d("Firebase", "Data written successfully!")
                    name.text.clear()
                    age.text.clear()
                    salary.text.clear()
                    Toast.makeText(this,"Data written successfully!",Toast.LENGTH_LONG).show()
                    startActivity(Intent(this,MainActivity::class.java))
                }
                .addOnFailureListener {
                    Log.e("Firebase", "Failed to write data", it)
                }

        }
    }
}