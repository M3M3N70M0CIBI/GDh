package com.example.gdh

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "lesson_item")
data class lessonItem(

    @PrimaryKey(autoGenerate = true)
    var id: Int? = null,
    @ColumnInfo(name = "title")
    var title: String,
    @ColumnInfo(name = "time")
    var time: String,
    @ColumnInfo(name = "description")
    var description: String


)
