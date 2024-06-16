package com.example.gdh

import android.content.Context
import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [lessonItem::class], version = 1)
abstract class LessonDb : RoomDatabase(){
    abstract fun getDao(): Dao
    companion object{
        fun getDb(context: Context): LessonDb{
            return Room.databaseBuilder(context.applicationContext, LessonDb::class.java, "lessons.db").build()
        }
    }

}
