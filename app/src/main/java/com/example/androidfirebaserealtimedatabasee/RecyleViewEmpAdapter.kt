package com.example.androidfirebaserealtimedatabasee

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class RecyleViewEmpAdapter(private var list : MutableList<Emp> , private var onclick : (Emp) -> Unit ) : RecyclerView.Adapter<RecyleViewEmpAdapter.ViewHolder>(){
    class ViewHolder (view : View): RecyclerView.ViewHolder(view) {
        val name = view.findViewById<TextView>(R.id.item)!!
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.empitem,parent,false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.name.text = list[position].emp_name
        holder.itemView.setOnClickListener {
            onclick(list[position])
        }
    }

}