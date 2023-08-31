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
import com.example.noteappproject_new.databinding.NotercvBinding

class noteAdapter(update:(DataModel)->Unit,delete: (Int) -> Unit): RecyclerView.Adapter<noteAdapter.NoteVH>() {

    var List =ArrayList<DataModel>()
    var Update = update
    var delete = delete
   lateinit var Context:Context

    class NoteVH(itemView: NotercvBinding) : ViewHolder(itemView.root) {
    var binding = itemView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteVH {
        Context= parent.context
        var View =NotercvBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return NoteVH(View)
    }

    override fun getItemCount(): Int {
        return List.size
    }

    override fun onBindViewHolder(holder: NoteVH, @SuppressLint("RecyclerView") position: Int) {
        holder.binding.apply {
            List.get(position).apply{
                edtid.text =id.toString()
                edtName.text =Name
                edtCourse.text =Course

            }




            holder.itemView.setOnLongClickListener(object : OnLongClickListener {
                @SuppressLint("SuspiciousIndentation")
                override fun onLongClick(v: View?): Boolean {
                    var PopupMenu = PopupMenu(Context, holder.itemView)
                    PopupMenu.menuInflater.inflate(R.menu.popomenu, PopupMenu.menu)

                    PopupMenu.setOnMenuItemClickListener(object :
                        PopupMenu.OnMenuItemClickListener {
                        override fun onMenuItemClick(item: MenuItem?): Boolean {

                            if (item?.itemId == R.id.Edit) {
                                Update.invoke(List.get(position))
                            }
                            if (item?.itemId == R.id.Delete) {

                                delete.invoke(List.get(position).id)
                            }
                            return true
                        }

                    })
                    PopupMenu.show()
                    return false
                }
            })
        }

    }


    fun setNote(list: ArrayList<DataModel>) {
      this.List= list

    }
    fun update(fetch:ArrayList<DataModel>) {
        List = fetch
        notifyDataSetChanged()
    }


}