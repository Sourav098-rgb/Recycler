package com.example.recycler

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface Notesinterface{
    @Insert
    fun insertTodo(todoEntity: NotesEntity)
    @Query("SELECT * FROM NotesEntity")
    fun getList():List<NotesEntity>

    @Update
    fun updateTodoEntity(todoEntity: NotesEntity)

    @Delete
    fun deleteTodoEntity(todoEntity:NotesEntity)
}
