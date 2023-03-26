package com.example.notesnew.UI

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.example.notesnew.R

class MainActivity : AppCompatActivity() {

    private lateinit var btnBack : ImageView
    private lateinit var tvTime : ImageView
    private lateinit var btnAddNote : ImageView
    private lateinit var navHostFragment: NavHostFragment
    private lateinit var navController : NavController

    private val ACTION_ADD = 1
    private val ACTION_EDIT = 2
    private val ACTION_BACK = 3

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        navHostFragment =
            supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        navController = navHostFragment.navController

        btnAddNote.setOnClickListener{
            navController.navigate(R.id.action_listNotesFragment_to_editNoteFragment)
            refreshUpNavigation(ACTION_ADD)
        }

        btnBack.setOnClickListener {
            navController.navigate(R.id.action_editNoteFragment_to_listNotesFragment)
            refreshUpNavigation(ACTION_BACK)
        }
    }

    private fun initViews() {
        btnBack = findViewById(R.id.goToListNote)
        tvTime = findViewById(R.id.icon)
        btnAddNote = findViewById(R.id.goToAddNote)
    }

    fun refreshUpNavigation(action : Int){
        when(action){
            ACTION_ADD -> {
                btnBack.visibility = View.VISIBLE
                tvTime.visibility = View.GONE
                btnAddNote.visibility = View.GONE
            }
            ACTION_EDIT -> {
                btnBack.visibility = View.VISIBLE
                tvTime.visibility = View.GONE
                btnAddNote.visibility = View.GONE
            }
            ACTION_BACK -> {
                btnBack.visibility = View.GONE
                tvTime.visibility = View.VISIBLE
                btnAddNote.visibility = View.VISIBLE
            }
        }
    }
}