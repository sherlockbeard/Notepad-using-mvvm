package com.sherlock.notepad;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Update;

import java.util.List;

public class NoteRepo {
    private NoteDao noteDao;
    private LiveData<List<notes>> allNotes;

    public NoteRepo(Application application){
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public void insert(notes note){
        new InsertNoteAsync(noteDao).execute(note);

    }
    public void update(notes note){
        new UpdateNoteAsync(noteDao).execute(note);

    }
    public void delete(notes note){
        new DeleteNoteAsync(noteDao).execute(note);

    }
    public void deleteAllNotes(){
        new DeleteAllNoteAsync(noteDao).execute();
    }

    public LiveData<List<notes>> getAllNotes() {
        return allNotes;
    }

    private static class InsertNoteAsync extends AsyncTask<notes,Void,Void>{
        private NoteDao noteDao;

        private InsertNoteAsync(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(notes... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsync extends AsyncTask<notes,Void,Void>{
        private NoteDao noteDao;

        private UpdateNoteAsync(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(notes... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsync extends AsyncTask<notes,Void,Void>{
        private NoteDao noteDao;

        private DeleteNoteAsync(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(notes... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNoteAsync extends AsyncTask<Void,Void,Void>{
        private NoteDao noteDao;

        private DeleteAllNoteAsync(NoteDao noteDao){
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... Void) {
            noteDao.deleteAll();
            return null;
        }
    }
}
