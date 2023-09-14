package com.example.noteappproject_new

import android.content.Context
import android.icu.text.CaseMap.Title
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.noteappproject_new.databinding.ActivityNoteLayoutBinding

class Note_Layout : AppCompatActivity() {
    lateinit var binding: ActivityNoteLayoutBinding
    lateinit var DB:DataHelper
    lateinit var adapter: DataAdapter
      override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNoteLayoutBinding.inflate(layoutInflater)
        setContentView(binding.root)

        DB = DataHelper(this)

          //Button Data Add

        binding.btnSubmit.setOnClickListener {
            var Title = binding.edtTitle.text.toString()
            var Note =binding.edttText.text.toString()


            //Empty Note
            if(!Note.equals("")) {
                var Model = DataModel(1,Title, Note)
                DB.AddNote(Model)

            }
            adapter.Liveupdate(DB.FetchData())

            binding.edtTitle.setText("")
            binding.edttText.setText("")
        }
    }


}