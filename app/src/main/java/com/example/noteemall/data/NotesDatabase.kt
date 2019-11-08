package com.example.noteemall.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [Note::class, Tag::class, NoteTagJoin::class],
    version = 1
)
abstract class NotesDatabase: RoomDatabase() {
    abstract fun noteDataDao(): NoteDataDao
    abstract fun noteTagDao(): NoteTagDao

    companion object {
        private var instance: NotesDatabase? = null

        private val LOCK = Any()

        operator fun invoke(context: Context)= instance
            ?: synchronized(LOCK){
            instance
                ?: buildDatabase(context).also { instance = it}
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(context,
            NotesDatabase::class.java, "notes.db")
            .build()
    }
}