package com.teruten.todolist

import android.support.v7.widget.RecyclerView
import android.text.format.DateFormat
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_todo.view.*

class TodoAdapter(private val list: List<Todo>,
                  private val click: (Long) -> Unit) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder =
        ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_todo, parent, false))

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val item = list[position]
        holder.itemView.text1.text = item.title
        holder.itemView.text2.text = DateFormat.format("yyyy/MM/dd", item.date)

        holder.itemView.setOnClickListener { click(item.id) }
    }

    inner class ViewHolder(view: View): RecyclerView.ViewHolder(view)
}