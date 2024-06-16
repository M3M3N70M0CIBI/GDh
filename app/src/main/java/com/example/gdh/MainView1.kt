package com.example.gdh

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.core.view.size
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.lifecycle.asLiveData
import com.example.gdh.databinding.MainActivityBinding
import kotlin.concurrent.thread

class MainView1 : AppCompatActivity() {

    private lateinit var binding : MainActivityBinding
    private val drawerLayout : DrawerLayout by lazy {
        findViewById(R.id.drawer_layout)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)



//        testLessons()

        binding = MainActivityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = LessonDb.getDb(this)


        dbInit(db)

        binding.bottomNavigationView.menu.getItem(1).setChecked(true)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        val toggle = ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open_nav, R.string.nav_close)
        drawerLayout.addDrawerListener(toggle)
        toggle.syncState()


        if (savedInstanceState == null){
            replaceFragment(Calendar())
        }

        binding.bottomNavigationView.setOnItemSelectedListener {
            reEnableChecking()
            when(it.itemId){


                R.id.calendar -> replaceFragment(Calendar())
                R.id.messenger -> replaceFragment(Messenger())
                R.id.files -> replaceFragment(Files())

                else ->{

                }

            }
            true
        }


        binding.navView.setNavigationItemSelectedListener {
            unCheckFunction()
            when(it.itemId) {

                R.id.settings -> replaceFragment(Settings())
                R.id.info -> replaceFragment(About())
                R.id.notifications -> replaceFragment(Notifications())
                R.id.nav_logout -> Toast.makeText(this, "Logout WIP", Toast.LENGTH_SHORT).show()

            }
            drawerLayout.closeDrawer(GravityCompat.START)
            true
        }

    }

    private fun dbInit(db: LessonDb) {
        db.getDao().getAllLessonItems().asLiveData().observe(this){
            Toast.makeText(this, "Data found!", Toast.LENGTH_SHORT).show()
            var item: Lesson
            it.forEach{
                Log.i("myApp", it.toString())
                item = Lesson(it.title, it.time, it.id, View.GONE ,it.description ,R.drawable.book_ic)
                lessonList.add(item)
            }
        }
    }

    private fun addingTestItemsViaBd(dataBase: LessonDb) {
        var item = lessonItem(null, "Lesson 1", "8:30", "Описание урока отсутствует")
        dataBase.getDao().insertItem(item)
        item = lessonItem(null, "Lesson 2", "10:30", "Описание урока отсутствует")
        dataBase.getDao().insertItem(item)
        item = lessonItem(null, "Lesson 3", "12:30", "Описание урока отсутствует")
        dataBase.getDao().insertItem(item)
    }

    private fun replaceFragment(fragment: Fragment){

        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.replace(R.id.frame_layout, fragment)
        fragmentTransaction.commit()

    }
    override fun onBackPressed() {
        super.onBackPressed()
        if (drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START)
        } else {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun unCheckFunction(){
        var size : Int = binding.bottomNavigationView.menu.size
        binding.bottomNavigationView.menu.getItem(0).setChecked(true)
        binding.bottomNavigationView.menu.getItem(1).setChecked(true)
        binding.bottomNavigationView.menu.getItem(2).setChecked(true)
        for (i in 0 until size){
            binding.bottomNavigationView.menu.getItem(i).setCheckable(false)
            binding.bottomNavigationView.menu.getItem(i).setChecked(true)

        }
    }
    private fun reEnableChecking(){
        var size : Int = binding.bottomNavigationView.menu.size
        for (i in 0 until size){
            binding.bottomNavigationView.menu.getItem(i).setCheckable(true)
        }
    }

//    private fun testLessons() {
//        val lesson1 = Lesson(
//            title = "lesson1",
//            time = "8:30",
//            description = "Описание 1"
//
//        )
//        val lesson2 = Lesson(
//            title = "lesson2",
//            time = "10:00",
//            description = "Описание 2"
//
//        )
//        lessonList.add(lesson1)
//        lessonList.add(lesson2)
//
//    } //Функция-placeholder по заполнению массива данных

//    override fun onNavigationItemSelected(item: MenuItem): Boolean {
//        when(item.itemId) {
//
//
//            R.id.settings -> replaceFragment(Settings())
//            R.id.info -> replaceFragment(About())
//            R.id.nav_logout -> Toast.makeText(this, "Logout WIP", Toast.LENGTH_SHORT).show()
//
//        }
//        drawerLayout.closeDrawer(GravityCompat.START)
//        return true
//    }
}
