package com.example.testdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = arrayOf(Note::class), version = 1)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): Dao
    companion object{
        @Volatile
        private var INSTANCE : NoteDatabase? = null
        fun getDatabase(context: Context): NoteDatabase{
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NoteDatabase::class.java,
                    "note_db"
                ).build()
                INSTANCE = instance
                // return instance
                instance
            }
        }
    }
}