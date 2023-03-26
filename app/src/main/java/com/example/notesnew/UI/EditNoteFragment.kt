package com.example.notesnew.UI

import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.lifecycle.ViewModelProvider
import com.example.notesnew.ViewModel.MainViewModel
import com.example.notesnew.Model.Note
import com.example.notesnew.R
import java.time.LocalDate


class EditNoteFragment : Fragment() {

    private lateinit var mainViewModel : MainViewModel
    private lateinit var view1 : View
    private lateinit var editName : EditText
    private lateinit var editText : EditText
    private lateinit var tvDate : TextView
    private lateinit var noteState : Note

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onPause() {
        super.onPause()
        saveNoteState()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        view1 = inflater.inflate(R.layout.fragment_edit_note, container, false)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        getNoteState()
        initViews()

        return view1
    }

    private fun initViews(){
        editName = view1.findViewById(R.id.editName)
        editText = view1.findViewById(R.id.editText)
        tvDate = view1.findViewById(R.id.tvDate)
        editName.setText(noteState.name)
        editText.setText(noteState.text)
        tvDate.setText(noteState.date)
    }

    private fun getNoteState(){
        noteState = Note("", "", "")
        if (arguments?.get("id") != null) {
            noteState.id = requireArguments().get("id") as Int
            noteState.name = requireArguments().get("name") as String
            noteState.text = requireArguments().get("text") as String
            noteState.date = requireArguments().get("date") as String
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun saveNoteState(){
        noteState.name = editName.text.toString()
        noteState.text = editText.text.toString()
        noteState.date = LocalDate.now().toString()
        mainViewModel.saveNote(noteState)
    }
}