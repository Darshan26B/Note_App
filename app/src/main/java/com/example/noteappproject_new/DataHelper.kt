package com.example.noteappproject_new

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import android.graphics.ColorSpace.Model
import android.renderscript.Sampler.Value

class DataHelper(
    context: Context?,
) : SQLiteOpenHelper(context, "Note.db", null, 1) {

    var Table_Name = "Note"
    override fun onCreate(db: SQLiteDatabase?) {
        var Sql =
            "CREATE TABLE $Table_Name(ID INTEGER PRIMARY KEY AUTOINCREMENT,TITLE TEXT,NOTE TEXT)"
        db?.execSQL(Sql)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {

    }

    fun AddNote(db: DataModel) {
        var AddData = writableDatabase

        var Value = ContentValues().apply {
            db.apply {
                put("Title", Title)
                put("Note", Note)
            }
        }
        AddData.insert(Table_Name, null, Value)
    }

    fun FetchData(): ArrayList<DataModel> {
        var DataList = ArrayList<DataModel>()
        var fetchData = readableDatabase

        var sql = "SELECT *FROM $Table_Name"

        var Cursor = fetchData.rawQuery(sql, null)
        Cursor.moveToFirst()


        for (i in 0..Cursor.count - 1) {
            var id = Cursor.getInt(0)
            var Title = Cursor.getString(1)
            var Note = Cursor.getString(2)

            var Model = DataModel(id, Title, Note)
            DataList.add(Model)
            Cursor.moveToNext()

        }
        return DataList
    }

    fun UpdateNote(db:DataModel) {
        var updateData = writableDatabase
        var Value = ContentValues().apply {
            db.apply {
                put("Title",Title)
                put("Note",Note)
            }
        }


        updateData.update(Table_Name,Value,"id=${db.id}",null)
    }
    fun DelectNote(id:Int) {
        var db = writableDatabase

        db.delete(Table_Name,"id=$id",null)
    }
}