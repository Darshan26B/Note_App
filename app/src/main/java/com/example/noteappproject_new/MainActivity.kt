package com.example.noteappproject_new

import android.annotation.SuppressLint
import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteappproject_new.databinding.ActivityMainBinding
import com.example.noteappproject_new.databinding.UpdateDailogBinding
import java.util.Date

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var DB: DataHelper
    lateinit var adapter: DataAdapter
    var NoteList = ArrayList<DataModel>()


    @SuppressLint("SuspiciousIndentation")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        DB = DataHelper(this)


//Button - Note Add

        binding.add.setOnClickListener {

            var intent = Intent(this, Note_Layout::class.java)
            startActivity(intent)
        }

        //Search-View search the note code

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {

                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                var temarr = ArrayList<DataModel>()

                for (arr in NoteList) {
                    if (arr.Title?.lowercase()?.contains(newText!!.lowercase()) == true ||
                        arr.Note?.lowercase()?.contains(newText!!.lowercase()) == true
                    ) {

                        temarr.add(arr)
                    }

                }
                adapter.setNote(temarr)
                adapter.notifyDataSetChanged()
                return true
            }

        })


        //Button in Create Note

        binding.BtnCreateNote.setOnClickListener {
            binding.add.performClick()
        }

        NoteList = DB.FetchData()

        //Empty Note
        ShowNotes()

        //code Update an Delete

        adapter = DataAdapter({
            UpdateData(it)
        }, {
            DeletData(it)
        })

        adapter.setNote(NoteList)
        binding.noteListrcv.layoutManager = LinearLayoutManager(this)
        binding.noteListrcv.adapter = adapter
    }




    //Empty Note
    private fun ShowNotes() {
        NoteList = DB.FetchData()
        if (NoteList.size > 0) {

            binding.emptyNote.visibility = View.INVISIBLE
            binding.noteListrcv.visibility = View.VISIBLE

        } else {
            binding.emptyNote.visibility = View.VISIBLE
            binding.noteListrcv.visibility = View.INVISIBLE
        }
    }


    private fun DeletData(id: Int) {
        var dialog = AlertDialog.Builder(this)
            .setTitle("Delecte Your Note")
            .setMessage("Are you want sure?")
            .setPositiveButton("Yes", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    DB.DelectNote(id)
                    //Empty Note
                    ShowNotes()
                    adapter.Liveupdate(DB.FetchData())
                }

            }).setNegativeButton("No", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {

                }
            }).create()
        dialog.show()
    }

    private fun UpdateData(Data: DataModel) {
        var dialog = Dialog(this)
        var bind = UpdateDailogBinding.inflate(layoutInflater)
        dialog.setContentView(bind.root)

        bind.edtTitle.setText(Data.Title)
        bind.edttText.setText(Data.Note)

        bind.btnSubmit.setOnClickListener {
            var Title = bind.edtTitle.text.toString()
            var Note = bind.edttText.text.toString()
            var Model = DataModel(Data.id, Title, Note)
            DB.UpdateNote(Model)
            adapter.Liveupdate(DB.FetchData())
            dialog.dismiss()
        }
        dialog.show()
    }


}