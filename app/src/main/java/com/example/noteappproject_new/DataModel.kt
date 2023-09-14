package com.example.noteappproject_new

class DataModel {

    var id:Int =0
    lateinit var Title :String
    lateinit var Note :String


    constructor(id: Int, Title: String, Note: String) {
        this.id = id
        this.Title = Title
        this.Note = Note
    }
}