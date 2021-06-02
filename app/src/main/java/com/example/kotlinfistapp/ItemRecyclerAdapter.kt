@file:Suppress("DEPRECATION")

package com.example.kotlinfistapp

import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import com.google.gson.Gson
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ItemRecyclerAdapter(private val actionListener: ActionListener, _items : List<ItemToDo>) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    var items : List<ItemToDo> = _items
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.item,p0,false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder)
        {
            is ItemViewHolder ->{
                holder.bind(items.get(position))
            }
        }
    }

    inner class ItemViewHolder constructor(itemView : View):RecyclerView.ViewHolder(itemView){
        private val titletextView : TextView = itemView.findViewById<TextView>(R.id.descriptionOfItem)
        private val cbitem : CheckBox = itemView.findViewById(R.id.itemcb)
        init {
            cbitem.setOnClickListener{
                val itemPosition = adapterPosition
                if (itemPosition != RecyclerView.NO_POSITION) {
                    val clickedItem = items[itemPosition]
                    actionListener.onItemClicked(itemPosition)
                }
            }
        }
        fun bind(itemToDo: ItemToDo){
            titletextView.text = itemToDo.getDescription()
            cbitem.isChecked = itemToDo.faitText != "0"
        }
    }
    interface ActionListener {
        fun onItemClicked(position: Int)
    }
}