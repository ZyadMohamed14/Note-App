package com.example.noteapp.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.noteapp.ui.theme.BabyBlue
import com.example.noteapp.ui.theme.LightGreen
import com.example.noteapp.ui.theme.RedOrange
import com.example.noteapp.ui.theme.RedPink
import com.example.noteapp.ui.theme.Violet

@Entity
data class Note(
    @PrimaryKey(autoGenerate = true) val id: Int?=null,
    val title: String,
    val content: String,
    val timeStamp: Long,
    val color: Int
){
    companion object{
        val noteColors  = listOf(RedOrange, RedPink , BabyBlue, LightGreen, Violet)
    }

}
class InvalidNoteException(message: String): Exception(message)
