package com.example.networknotes.ui.note

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.view.isGone
import androidx.fragment.app.Fragment
import com.example.networknotes.db.NoteContent
import com.example.networknotes.R

class NoteFragment(private val note: NoteContent?): Fragment(), NoteContract.View {

    private lateinit var presenter: NotePresenter
    private lateinit var title: EditText
    private lateinit var content: EditText

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        val root= inflater.inflate(R.layout.note_fragment, container, false)
        val addBtn: Button = root.findViewById(R.id.dialog_add_btn)
        val editBtn: Button = root.findViewById(R.id.dialog_edit_btn)
        val deleteBtn: Button = root.findViewById(R.id.dialog_delete_btn)

        val hideBtn = note == null
        addBtn.isGone = !hideBtn
        editBtn.isGone = hideBtn
        deleteBtn.isGone = hideBtn

        title = root.findViewById(R.id.titleEditText)
        content = root.findViewById(R.id.contentEditText)

        addBtn.setOnClickListener{presenter.addNewNote()}
        editBtn.setOnClickListener{presenter.editExistingNote()}
        deleteBtn.setOnClickListener{presenter.deleteNote()}

        presenter = NotePresenter(context!!, this)

        fillFields(note)

        return root
    }

    private fun fillFields(note: NoteContent?) {
        if (note == null) {
            return
        }
        title.setText(note.title)
        content.setText(note.content)
    }

    override fun getNoteTitle(): String = title.text.toString()

    override fun getNoteContent(): String = content.text.toString()

    override fun getNoteId(): Int = note!!.id

    override fun closeFragment() {
        parentFragmentManager.popBackStack()
    }

    override fun displayMsg(msg: String) {
        Toast.makeText(activity, msg, Toast.LENGTH_LONG).show()
    }
}