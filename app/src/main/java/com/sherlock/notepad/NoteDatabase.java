package com.sherlock.notepad;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = notes.class,version = 1)
public abstract class NoteDatabase extends RoomDatabase {
    public static NoteDatabase instance;

    public abstract NoteDao noteDao();

    public static synchronized NoteDatabase getInstance(Context context){
        if(instance==null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class,"note database").addCallback(roomCallback)
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }
    public static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };
    public static class PopulateDbAsyncTask extends AsyncTask<Void,Void,Void>{
        private NoteDao noteDao;

        public PopulateDbAsyncTask(NoteDatabase db){
            noteDao = db.noteDao();
        }


        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new notes("Title1","desc 1",1));
            noteDao.insert(new notes("Title2","desc 3",1));
            noteDao.insert(new notes("Title2","desc 4",1));
            return null;
        }
    }

}
