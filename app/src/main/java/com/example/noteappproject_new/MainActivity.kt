package com.example.noteappproject_new

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.noteappproject_new.databinding.ActivityMainBinding
import com.example.noteappproject_new.databinding.UpdateLayoutBinding

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var List = ArrayList<DataModel>()
    lateinit var DB: DBHelper
    lateinit var adapter: noteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


            DB = DBHelper(this)


        binding.btnDone.setOnClickListener {
            var Name = binding.edtN.text.toString()
            var Course = binding.edtC.text.toString()
            var  model =DataModel(1, Name, Course)

            DB.AddData(model)
            binding.edtN.setText("")
            binding.edtC.setText("")
        }
            List = DB.fetch()
        adapter = noteAdapter({
            UpdateHome(it)
        },{
            deleteHome(it)
        })
        adapter.setNote(List)

        binding.rcvExm.layoutManager = LinearLayoutManager(this)
        binding.rcvExm.adapter = adapter


    }

    private fun deleteHome(it: Int) {
        var dialog = AlertDialog.Builder(this)
            .setTitle("Delete Transaction")
            .setMessage("Are you want sure?")
            .setPositiveButton("yes", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {
                     DB.DelectNote(it)
                    adapter.update(DB.fetch())
                }

            }).setNegativeButton("No", object : DialogInterface.OnClickListener {
                override fun onClick(dialog: DialogInterface?, which: Int) {

                }
            }).create()
        dialog.show()
    }
    private fun UpdateHome(List: DataModel) {
        var dialog = Dialog(this)
        var bind = UpdateLayoutBinding.inflate(layoutInflater)
        dialog.setContentView(bind.root)

        bind.edtName.setText(List.Name.toString())
        bind.edtCourse.setText(List.Course.toString())

        bind.btnSave.setOnClickListener {
            var Name = bind.edtName.text.toString()
            var Course = bind.edtCourse.text.toString()
            var Model = DataModel(List.id, Name, Course)
            DB.UpdateNote(Model)
            adapter.update(DB.fetch())
            dialog.dismiss()
        }
        dialog.show()
    }
}