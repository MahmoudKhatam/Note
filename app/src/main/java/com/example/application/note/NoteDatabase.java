package com.example.application.note;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {notes.class} , version = 1 )
public abstract class NoteDatabase extends RoomDatabase {
    private static NoteDatabase instance;
    public abstract noteDao noteDao();

    public static synchronized NoteDatabase getInstance(Context context){
        if (instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteDatabase.class , "note_database").fallbackToDestructiveMigration()
                    .addCallback(roomCallback).build();

        }
        return instance;

    }

    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new populateDbAsyncTask(instance).execute();
        }

    };
    private static class populateDbAsyncTask extends AsyncTask<Void,Void,Void> {
        private noteDao noteDao;
        private populateDbAsyncTask(NoteDatabase db){
            noteDao=db.noteDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            noteDao.insert(new notes("Title 1" , "Description 1" , 1));
            noteDao.insert(new notes("Title 2" , "Description 2" , 2));
            noteDao.insert(new notes("Title 3" , "Description 3" , 3));
            return null;
        }
    }



}
