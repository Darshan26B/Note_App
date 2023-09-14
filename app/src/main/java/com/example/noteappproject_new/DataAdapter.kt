package com.example.noteappproject_new

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.View.INVISIBLE
import android.view.View.OnLongClickListener
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.PopupMenu
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.example.noteappproject_new.databinding.NotercvBinding

class DataAdapter(update: (DataModel) -> Unit, delete: (Int) -> Unit) : RecyclerView.Adapter<DataAdapter.NoteViewHolder>() {

    lateinit var Context: Context
    var List = ArrayList<DataModel>()
     var Update = update
    var Delete = delete

    class NoteViewHolder(itemView: NotercvBinding) : ViewHolder(itemView.root) {
        var binding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        Context = parent.context
        var View = NotercvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NoteViewHolder(View)
    }

    override fun getItemCount(): Int {
        return List.size
    }

    @SuppressLint("ResourceType")
    override fun onBindViewHolder(holder: NoteViewHolder, @SuppressLint("RecyclerView") position: Int) {
    holder.binding.apply {
        List.get(position).apply {
            txtTitle.text = Title
            txtText.text = Note
        }
        //Popupmenu in Edit And Delete
        holder.binding.ClickItem.setOnClickListener {
            var PopupMenu = PopupMenu(Context,holder.itemView)
            PopupMenu.menuInflater.inflate(R.menu.popomenu,PopupMenu.menu)

            PopupMenu.setOnMenuItemClickListener(object :PopupMenu.OnMenuItemClickListener {
                override fun onMenuItemClick(item: MenuItem?): Boolean {
                    if (item?.itemId == R.id.Edit) {
                        Update.invoke(List.get(position))
                    }
                    if (item?.itemId == R.id.Delete) {
                        Delete.invoke(List.get(position).id)
                    }
                     return true
                }

            })
            PopupMenu.show()
        }
//        holder.itemView.setOnLongClickListener(object :OnLongClickListener {
//            override fun onLongClick(v: View?): Boolean {
//                var popup = PopupMenu(Context,v)
//                var  inflater :MenuInflater = popup.menuInflater
//                inflater.inflate(R.menu.popomenu,popup.menu)
//                popup.show()
//
//                popup.setOnMenuItemClickListener(object : PopupMenu.OnMenuItemClickListener {
//                    override fun onMenuItemClick(item: MenuItem?): Boolean {
//                        if (item?.itemId == R.id.Edit) {
//                            Update.invoke(List.get(position))
//                        }
//                        if (item?.itemId == R.id.Delete) {
//                            Delete.invoke(List.get(position).id)
//                        }
//                        return true
//                    }
//
//                })
//                popup.show()
//                return true
//            }
//
//        })

    }

    }

    // Live Update
    fun Liveupdate(fetchData: java.util.ArrayList<DataModel>) {
        List = fetchData
        notifyDataSetChanged()
    }

    //Note Set

    fun setNote(noteList: java.util.ArrayList<DataModel>) {
        this.List = noteList
    }
    interface NotesClickListener {
        fun onItemClicked(note: DataModel)
    }
}