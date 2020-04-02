package com.example.networknotes.ui.main

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.networknotes.db.NoteContent
import com.example.networknotes.db.NoteHeader
import com.example.networknotes.R
import com.example.networknotes.ui.main.RcyclerView.Callback
import com.example.networknotes.ui.main.RcyclerView.MainAdapter
import com.example.networknotes.ui.note.NoteFragment

class MainFragment : Fragment(), MainContract.View, Callback{

    private lateinit var presenter: MainContract.Presenter
    private lateinit var recyclerView: RecyclerView

    companion object {
        fun newInstance() = MainFragment()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {

        val root: View = inflater.inflate(R.layout.main_fragment, container, false)
        recyclerView = root.findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(activity)
        presenter = MainPresenter(context!!,this)

        val button: Button = root.findViewById(R.id.add_btn)
        button.setOnClickListener { presenter.addNote() }

        return root
    }

    override fun displayAllNotesHeaders(array: List<NoteHeader>) {
        recyclerView.adapter = MainAdapter(array, this)
    }

    override fun displayNoteFragment(note: NoteContent?) {
        val transaction = parentFragmentManager.beginTransaction()
        transaction.replace(R.id.container, NoteFragment(note))
            .addToBackStack(null)
            .commit()
    }

    override fun onItemClicked(item: NoteHeader) {
        presenter.getNoteContent(item)
    }

    override fun displayMsg(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()
    }
}
