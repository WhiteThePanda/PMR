package com.example.kotlinfistapp.ui.adapter

import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.example.kotlinfistapp.data.model.ListeToDo
import com.example.kotlinfistapp.R

class ListRecyclerAdapter(private val actionListener: ActionListener) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    var items : List<ListeToDo> = ArrayList()
    fun initData(mesListes : List<ListeToDo>)
    {
        items = mesListes
    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.todolist_item,p0,false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        Log.d("PMRMoi",items.size.toString())
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder)
        {
            is ListViewHolder ->{
                holder.bind(items.get(position))
            }
        }
    }

    inner class ListViewHolder constructor(itemView : View):RecyclerView.ViewHolder(itemView){
        val titletextView : TextView = itemView.findViewById<TextView>(R.id.titleOfList)
        init {
            itemView.setOnClickListener{
                val itemPosition = adapterPosition
                if (itemPosition != RecyclerView.NO_POSITION) {
                    val clickedItem = items[itemPosition]
                    actionListener.onItemClicked(itemPosition)
                }
            }
        }
        fun bind(toDoList: ListeToDo){
            titletextView.text = toDoList.GetTitreListeToDo()
        }
    }
    interface ActionListener {
        fun onItemClicked(position: Int)
    }
}