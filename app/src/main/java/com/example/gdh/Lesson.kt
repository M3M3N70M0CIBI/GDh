package com.example.gdh

import android.view.View


var lessonList = mutableListOf<Lesson>()

class Lesson(

    var title: String,
    var time: String,
    val id: Int? = lessonList.size,
    var visibleVal: Int = View.GONE,
    var description: String,
    var iconRes: Int = R.drawable.book_ic// Будет изменено
)



