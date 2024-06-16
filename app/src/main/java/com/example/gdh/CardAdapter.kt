package com.example.gdh

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.gdh.databinding.CardCellLessonBinding

class CardAdapter(private val lesson: List<Lesson>, private val clickListener: LessonClickListener) : RecyclerView.Adapter<CardViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val from = LayoutInflater.from(parent.context)
        val binding = CardCellLessonBinding.inflate(from, parent, false)
        return CardViewHolder(binding, clickListener)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        holder.bindLesson(lesson[position])
    }

    override fun getItemCount(): Int = lesson.size

}