package com.example.androidfirebaserealtimedatabasee

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.database.FirebaseDatabase
import kotlin.math.log

class empDetails : AppCompatActivity() {
    private lateinit var id : String
    private lateinit var firebaseDatabase: FirebaseDatabase
    private lateinit var name : TextView
    private lateinit var age : TextView
    private lateinit var salary : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_emp_details)

        id = intent.getStringExtra("id").toString()
        getData()
        name = findViewById(R.id.disName)
        age = findViewById(R.id.disAge)
        salary = findViewById(R.id.disSalary)


    }

    private fun getData(){
        firebaseDatabase = FirebaseDatabase.getInstance()
        firebaseDatabase.getReference("emp").child(id).get()
            .addOnSuccessListener { snapshot ->
                val emp = snapshot.getValue(Emp::class.java)
                if(emp != null ){
                    name.text = emp.emp_name
                    age.text = emp.emp_age.toString()
                    salary.text = emp.emp_salary.toString()

                }
            }
    }
}