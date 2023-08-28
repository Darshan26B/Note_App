package com.example.noteappproject_new

class DataModel {

    var id:Int = 0
    lateinit var Name:String
    lateinit var Course:String


    constructor(id: Int, Name: String, Course: String) {
        this.id = id
        this.Name = Name
        this.Course = Course
    }
}