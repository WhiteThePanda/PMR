package com.example.kotlinfistapp

import android.content.Context
import android.content.Intent
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView

class ListRecyclerAdapter(context: Context) : RecyclerView.Adapter<RecyclerView.ViewHolder>(){
    var items : List<ListeToDo> = ArrayList()
    var mContext = context
    lateinit var username : String
    fun initData(currentProfilListeToDo : ProfilListeToDo)
    {
        items = currentProfilListeToDo.GetMesListesToDo()
        username = currentProfilListeToDo.name
    }
    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.todolist_item,p0,false)
        return ListViewHolder(view)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(holder)
        {
            is ListViewHolder ->{
                holder.parentLayout.setOnClickListener{
                    val toItemAct: Intent
                    Log.d("PMRMoi",position.toString())
                    toItemAct = Intent(mContext, ShowListActivity::class.java)
                    toItemAct.putExtra("name",username)
                    toItemAct.putExtra("position",position)
                    mContext.startActivity(toItemAct)
                }
                holder.bind(items.get(position))
            }
        }
    }

    class ListViewHolder constructor(itemView : View):RecyclerView.ViewHolder(itemView){
        val titletextView : TextView = itemView.findViewById<TextView>(R.id.titleOfList)
        val parentLayout : ConstraintLayout = itemView.findViewById(R.id.parent_layout)
        fun bind(toDoList: ListeToDo){
            titletextView.text = toDoList.GetTitreListeToDo()
        }
    }
}