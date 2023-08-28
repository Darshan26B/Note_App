package com.example.noteappproject_new

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.View.OnLongClickListener
import android.view.ViewGroup
import android.widget.PopupMenu
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder

class noteAdapter(update:(DataModel)->Unit,delete: (Int) -> Unit): RecyclerView.Adapter<noteAdapter.NoteVH>() {

    var List =ArrayList<DataModel>()
    var Update = update
    var delete = delete

    class NoteVH(itemView: View) : ViewHolder(itemView) {
        var id = itemView.findViewById<TextView>(R.id.edtid)
        var Name = itemView.findViewById<TextView>(R.id.edtName)
        var Course = itemView.findViewById<TextView>(R.id.edtCourse)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteVH {
        var View = LayoutInflater.from(parent.context).inflate(R.layout.notercv,parent,false)
        return NoteVH(View)
    }

    override fun getItemCount(): Int {
        return List.size
    }

    override fun onBindViewHolder(holder: NoteVH, position: Int) {
        holder.id.text = List.get(position).id.toString()
        holder.Name.text = List.get(position).Name
        holder.Course.text = List.get(position).Course
    }

//    override fun onBindViewHolder(holder: NoteVH,
//                                  @SuppressLint("RecyclerView") position: Int, payloads: MutableList<Any>) {
//        holder.itemView.setOnLongClickListener(object :OnLongClickListener {
//             override fun onLongClick(v: View?): Boolean {
//            var PopupMenu =PopupMenu(this,holder.itemView)
//                PopupMenu.menuInflater.inflate(R.menu.popomenu,PopupMenu.menu)
//
//           PopupMenu.setOnMenuItemClickListener(object :PopupMenu.OnMenuItemClickListener {
//               override fun onMenuItemClick(item: MenuItem?): Boolean {
//
//                   if (item?.itemId ==R.id.Edit) {
//                       Update.invoke(List.get(position))
//                   }
//                   if (item?.itemId ==R.id.Delete) {
//
//                       delete.invoke(List.get(position).id)
//                   }
//                   return true
//               }
//
//           })
//                PopupMenu.show()
//                return false
//            }
//        })
//    }

    fun setNote(list: ArrayList<DataModel>) {
      this.List= list

    }
    fun update(fetch: ArrayList<DataModel>) {
        List = fetch
        notifyDataSetChanged()
    }


}