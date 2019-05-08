package com.sherlock.notepad;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteDao {

    @Insert
    void insert(notes note);

    @Update
    void update(notes note);

    @Delete
    void delete(notes note);

    @Query("DELETE FROM notes_table")
    void deleteAll();

    @Query("SELECT * FROM notes_table ORDER BY priority DESC")
    LiveData<List<notes>> getAllNotes();

}
