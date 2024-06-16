package com.example.gdh

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.gdh.databinding.CardCellLessonBinding

class CardViewHolder(private val cardCellBinding : CardCellLessonBinding, private val clickListener: LessonClickListener) : RecyclerView.ViewHolder(cardCellBinding.root)
{
    fun bindLesson(lesson: Lesson) = with(cardCellBinding)
    {
        lessonTitle.text = lesson.title
        lessonTimeText.text = lesson.time
        folblePart.visibility = lesson.visibleVal
        optionIcon.setImageResource(lesson.iconRes)
        lessonDiscription.text = lesson.description

        lessonCard.setOnClickListener{
            clickListener.onClick(lesson)
        }
    }

}