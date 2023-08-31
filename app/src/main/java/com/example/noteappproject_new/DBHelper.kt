package com.example.noteappproject_new

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DBHelper(
    context: Context?,
) : SQLiteOpenHelper(context, "NoteApp", null, 1) {

    var Table_Name = "Note"

    override fun onCreate(db: SQLiteDatabase?) {
         var Sql = "CREATE TABLE $Table_Name(Id INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,COURSE TEXT)"
        db?.execSQL(Sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }
    fun AddData(DB:DataModel) {
        var Data = writableDatabase

        var Values = ContentValues().apply {
            DB.apply {
            put("Name",Name)
            put("Course",Course)
            }
        }

        Data.insert(Table_Name,null,Values)

        }

    fun fetch():ArrayList<DataModel>  {
        var DataList = ArrayList<DataModel>()
        var DB =readableDatabase

        var sql ="SELECT * FROM $Table_Name"

        var Cursor=DB.rawQuery(sql,null)
        Cursor.moveToFirst()

        for(i in 0..Cursor.count-1) {
            var id = Cursor.getInt(0)
            var Name = Cursor.getString(1)
            var Course = Cursor.getString(2)

            var Model = DataModel(id, Name, Course)

            DataList.add(Model)
            Cursor.moveToNext()
        }
            return DataList
    }
    fun UpdateNote(Model: DataModel) {
        var DB = writableDatabase
        var Value = ContentValues().apply {
        Model.apply {
            put("Name",Name)
            put("Course",Course)
        }

        }


        DB.update(Table_Name,Value,"id=${Model.id}",null)

    }
    fun DelectNote(id:Int) {
        var DB = writableDatabase

        DB.delete(Table_Name,"id=$id",null)
    }


}
