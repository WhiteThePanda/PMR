@file:Suppress("DEPRECATION")

package com.example.kotlinfistapp

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.support.constraint.ConstraintLayout
import android.support.v4.content.ContextCompat.startActivity
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import com.google.gson.Gson

class ItemRecyclerAdapter(context : Context, dataset : List<User>,username : String,position :Int) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    val mContext = context
    var items : List<ItemToDo> = ArrayList()
    var mdataset = dataset
    var musername = username
    var mlistPosition = position
    init {
        Log.d("PMRMoi","init")
        for( user : User in mdataset)
        {
            if(user.name == musername)
            {
                items = user.toDoLists[mlistPosition].lesItems
            }
        }
    }

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
                holder.cbitem.setOnClickListener{
                    var sp: SharedPreferences? = null
                    var editor: SharedPreferences.Editor? = null
                    sp = PreferenceManager.getDefaultSharedPreferences(mContext)
                    editor = sp?.edit()
                    items[position].fait = !items[position].fait
                    val gson = Gson()
                    val chaine_json = gson.toJson(mdataset)
                    Log.d("PMRMoi","inAdapter")
                    Log.d("PMRMoi",chaine_json)
                    editor?.putString("data",chaine_json)
                    editor?.commit()
                }
                holder.bind(items.get(position))
            }
        }
    }

    class ItemViewHolder constructor(itemView : View):RecyclerView.ViewHolder(itemView){
        val titletextView : TextView = itemView.findViewById<TextView>(R.id.descriptionOfItem)
        val cbitem : CheckBox = itemView.findViewById(R.id.itemcb)
        fun bind(itemToDo: ItemToDo){
            titletextView.text = itemToDo._description
            cbitem.isChecked = itemToDo.fait
            Log.d("PMRMoi",itemToDo.fait.toString())
        }
    }
}