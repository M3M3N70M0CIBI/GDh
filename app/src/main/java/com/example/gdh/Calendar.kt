package com.example.gdh

import android.os.Bundle
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.SurfaceControl.Transaction
import android.view.View
import android.view.ViewGroup
import android.widget.CalendarView
import android.widget.Toast
import androidx.lifecycle.asLiveData
import androidx.lifecycle.findViewTreeViewModelStoreOwner
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gdh.databinding.CardCellLessonBinding
import com.example.gdh.databinding.FragmentCalendarBinding
import kotlin.concurrent.thread
import kotlin.math.ceil

class Calendar : Fragment(), LessonClickListener {

    private var _binding: FragmentCalendarBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentCalendarBinding.inflate(inflater, container, false)

        val activity = this


//        Thread{
//            addingTestItemsViaBd(db)
//        }.start()


        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 1)
            adapter = CardAdapter(lessonList, activity)
        }


        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()

        val activity = this

        for (i in 0 until lessonList.size){
            lessonList[i].visibleVal = View.GONE
            lessonList[i].iconRes = R.drawable.book_ic
        }

        binding.recyclerView.apply {
            layoutManager = GridLayoutManager(context, 1)
            adapter = CardAdapter(lessonList, activity)
        }

    }


    override fun onClick(lesson: Lesson){
        if (lesson.visibleVal == View.GONE){
            lesson.visibleVal = View.VISIBLE
            lesson.iconRes = R.drawable.book_open_ic
        } else{
            lesson.visibleVal = View.GONE
            lesson.iconRes = R.drawable.book_ic
        }
        binding.recyclerView.apply {
            adapter = CardAdapter(lessonList, this@Calendar)
        }

    }



}