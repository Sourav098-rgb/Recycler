package com.example.recycler

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class NotesEntity(
    @PrimaryKey(autoGenerate = true)

var id :Int=0,
var title:String?=null,
var discription:String?=null,

    )