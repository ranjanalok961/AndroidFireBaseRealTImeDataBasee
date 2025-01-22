package com.example.androidfirebaserealtimedatabasee

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.google.firebase.database.FirebaseDatabase

class ListOfEmploy : AppCompatActivity() {
    private lateinit var list : MutableList<Emp>
    private lateinit var recyclerView: RecyclerView
    private lateinit var db : FirebaseDatabase

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_list_of_employ)
        setData()
        recyclerView = findViewById(R.id.recycleView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = RecyleViewEmpAdapter(list){ item ->
            val intent = Intent(this,empDetails::class.java)
            intent.putExtra("id",item.emp_id)
            startActivity(intent)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun setData(){
        db = FirebaseDatabase.getInstance()
        var dbRef = db.getReference("emp")
        list = mutableListOf<Emp>()
        dbRef.get().addOnSuccessListener {snapshot ->
            for (child in snapshot.children) {
                val emp = child.getValue(Emp::class.java)

                if (emp != null) {
                    list.add(emp)
                }
            }
            recyclerView.adapter?.notifyDataSetChanged()

        }
    }
}