package com.sherlock.notepad;

import android.app.Application;
import android.app.ListActivity;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModle extends AndroidViewModel {
    private NoteRepo noteRepo;
    private LiveData<List<notes>> AllNotes;


    public NoteViewModle(@NonNull Application application) {
        super(application);
        noteRepo = new NoteRepo(application);
        AllNotes = noteRepo.getAllNotes();
    }

    public void insert(notes note){
        noteRepo.insert(note);
    }
    public void delete(notes note){
        noteRepo.delete(note);
    }
    public void update(notes note){
        noteRepo.update(note);
    }
    public void deleteAllNotes(notes note){
        noteRepo.deleteAllNotes();
    }

    public LiveData<List<notes>> getAllNotes() {
        return AllNotes;
    }
}
