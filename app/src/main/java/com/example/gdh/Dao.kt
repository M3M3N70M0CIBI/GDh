package com.example.gdh

import android.content.ClipData.Item
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow


@Dao
interface Dao {
    @Insert (onConflict = OnConflictStrategy.REPLACE)
    fun  insertItem(lessonItem: lessonItem)
    @Query("SELECT * FROM lesson_item")
    fun getAllLessonItems(): Flow<List<lessonItem>>
    @Delete
    fun deleteItem(lessonItem: lessonItem)
    @Query("DELETE FROM lesson_item")
    fun nukeAll()
}