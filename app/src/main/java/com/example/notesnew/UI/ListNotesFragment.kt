package com.example.notesnew.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.example.notesnew.ViewModel.MainViewModel
import com.example.notesnew.Model.Note
import com.example.notesnew.NotesAdapter
import com.example.notesnew.R

class ListNotesFragment : Fragment() {

    private lateinit var recyclerViewNotes: RecyclerView
    private lateinit var view1: View
    private lateinit var notesAdapter: NotesAdapter
    private lateinit var inflater: LayoutInflater
    private lateinit var tvCountNotes: TextView
    private lateinit var mainActivity: MainActivity
    private lateinit var mainViewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        this.inflater = inflater
        view1 = inflater.inflate(R.layout.fragment_list_notes, container, false)
        initViews()
        mainActivity = activity as MainActivity
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        val navController =
            activity?.let { Navigation.findNavController(it, R.id.nav_host_fragment) }

        notesAdapter = NotesAdapter()
        recyclerViewNotes.adapter = notesAdapter
        recyclerViewNotes.addItemDecoration(object : RecyclerView.ItemDecoration() {

        })
        notesAdapter.setNotes(arrayListOf(Note("", "", "")))

        mainViewModel.getNotes().observe(viewLifecycleOwner, object : Observer<List<Note>> {
            override fun onChanged(notes: List<Note>?) {
                if (notes != null) {
                    notesAdapter.setNotes(notes)
                    tvCountNotes.text = "/" + notes.size
                }
            }
        })

        notesAdapter.setOnNoteClickListener(object : NotesAdapter.OnNoteClickListener {
            override fun onNoteClick(note: Note) {
                if (navController != null) {
                    navController.navigate(
                        R.id.action_listNotesFragment_to_editNoteFragment,
                        bundleOf(
                            "id" to note.id,
                            "name" to note.name,
                            "text" to note.text,
                            "date" to note.date
                        )
                    )
                }
                mainActivity.refreshUpNavigation(2)
            }
        })
        recyclerViewNotes.adapter = notesAdapter
        val itemTouchHelper = ItemTouchHelper(
            object : ItemTouchHelper.SimpleCallback(
                0,
                ItemTouchHelper.RIGHT or ItemTouchHelper.LEFT
            ) {
                override fun onMove(
                    recyclerView: RecyclerView,
                    viewHolder: RecyclerView.ViewHolder,
                    target: RecyclerView.ViewHolder
                ): Boolean {
                    return false
                }

                override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                    val position = viewHolder.adapterPosition
                    val note = notesAdapter.getNotes().get(position)
                    mainViewModel.remove(note)
                }
            })
        itemTouchHelper.attachToRecyclerView(recyclerViewNotes)

        return view1
    }

    private fun initViews() {
        recyclerViewNotes = view1.findViewById(R.id.recyclerViewNotes)
        tvCountNotes = view1.findViewById(R.id.tvCountNotes)
    }
}