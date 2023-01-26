package com.example.android.notesapp_room_mvvm.adapter

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.android.notesapp_room_mvvm.R
import com.example.android.notesapp_room_mvvm.models.NoteModel

class NoteAdapter(val context : Context,
                  val onClickNoteInterface: OnClickNoteInterface,
                  val onClickDeleteNoteBtnInterface: OnClickDeleteNoteBtnInterface
                  ) : RecyclerView.Adapter<NoteAdapter.NoteViewHolder>() {


    private val allNotes = ArrayList<NoteModel>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteViewHolder {
        return NoteViewHolder(LayoutInflater.from(context).inflate(R.layout.note_recycler_design,parent,false))
    }

    override fun onBindViewHolder(holder: NoteViewHolder, position: Int) {
        holder.noteTitleTxt.text = allNotes[position].noteTitle
        holder.noteBodyTxt.text = allNotes[position].noteDescription
        holder.noteTimeTxt.text = "Last Updated :  ${allNotes[position].timeStamp}"    //********

        holder.noteDeleteBtn.setOnClickListener {
            AlertDialog.Builder(context)
                .setTitle("Delete Note")
                .setMessage("Are you sure to delete the note")
                .setIcon(R.drawable.ic_baseline_delete_24)
                .setPositiveButton("Yes") {dialog, which->
                    onClickDeleteNoteBtnInterface.onClickDeleteBtn(allNotes[position])
                }
                .setNegativeButton("no") {dialog, which->

                }
                .setCancelable(false)
                .show()

        }

        holder.itemView.setOnClickListener {
            onClickNoteInterface.onClickNote(allNotes[position])
        }
    }

    override fun getItemCount(): Int {
        return allNotes.size
    }

    fun updateNoteList(newList : List<NoteModel>) {
        allNotes.clear()
        allNotes.addAll(newList)
        notifyDataSetChanged()
    }


    inner class NoteViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        val noteTitleTxt = itemView.findViewById<TextView>(R.id.noteTitleTxt)
        val noteBodyTxt = itemView.findViewById<TextView>(R.id.noteBodyTxt)
        val noteTimeTxt = itemView.findViewById<TextView>(R.id.noteDateTxt)
        val noteDeleteBtn = itemView.findViewById<ImageView>(R.id.deleteIcon)
    }

}

interface OnClickDeleteNoteBtnInterface {
    fun onClickDeleteBtn(note : NoteModel)
}

interface OnClickNoteInterface {
    fun onClickNote(note : NoteModel)
}