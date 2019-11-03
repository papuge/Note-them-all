package com.example.noteemall

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation

class TagWithNotesPojo {
    @Embedded
    lateinit var tag: Tag

    @Relation(
        entityColumn = "note_id",
        parentColumn = "tag_id",
        associateBy = Junction(
            value = NoteTagJoin::class,
            parentColumn = "fk_tag_id",
            entityColumn = "fk_note_id"
        )
    )
    var notes: List<Note> = arrayListOf()
}