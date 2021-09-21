package com.example.teachproject

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class MyAdapter : RecyclerView.Adapter<MyAdapter.MyViewHolder>() {
    private val values : MutableList<String> = mutableListOf("")
    
    class MyViewHolder(row: View) : RecyclerView.ViewHolder(row) {
        val textView: TextView = row.findViewById(R.id.number)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val layout = LayoutInflater.from(parent.context).inflate(
            R.layout.item_view,
            parent, false
        )
        return MyViewHolder(layout)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.textView.text = values[position]
    }

    override fun getItemCount(): Int = values.size

    fun setItems(s: String) {
        values.add(s)
        notifyDataSetChanged()
    }

    fun clearItems() {
        values.clear()
        notifyDataSetChanged()
    }
}