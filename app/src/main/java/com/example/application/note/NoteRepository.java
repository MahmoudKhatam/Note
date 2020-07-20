package com.example.application.note;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteRepository {

    private noteDao noteDao;
    private LiveData<List<notes>> allNotes;

    public NoteRepository(Application application) {
        NoteDatabase database = NoteDatabase.getInstance(application);
        noteDao = database.noteDao();
        allNotes = noteDao.getAllNotes();
    }

    public void insert(notes notes) {
        new InsertNoteAsyncTask(noteDao).execute(notes);

    }

    public void update(notes notes) {
        new UpdateNoteAsyncTask(noteDao).execute(notes);
    }

    public void delete(notes notes) {
        new DeleteNoteAsyncTask(noteDao).execute(notes);
    }

    public void deleteAll() {
        new DeleteAllNoteAsyncTask(noteDao).execute();
    }

    public LiveData<List<notes>> getAllNotes() {
        return allNotes;
    }

    private static class InsertNoteAsyncTask extends AsyncTask<notes, Void, Void> {
        private static noteDao noteDao;

        private InsertNoteAsyncTask(noteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(notes... notes) {
            noteDao.insert(notes[0]);
            return null;
        }
    }

    private static class UpdateNoteAsyncTask extends AsyncTask<notes, Void, Void> {
        private static noteDao noteDao;

        private UpdateNoteAsyncTask(noteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(notes... notes) {
            noteDao.update(notes[0]);
            return null;
        }
    }

    private static class DeleteNoteAsyncTask extends AsyncTask<notes, Void, Void> {
        private static noteDao noteDao;

        private DeleteNoteAsyncTask(noteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(notes... notes) {
            noteDao.delete(notes[0]);
            return null;
        }
    }

    private static class DeleteAllNoteAsyncTask extends AsyncTask<Void, Void, Void> {
        private static noteDao noteDao;

        private DeleteAllNoteAsyncTask(noteDao noteDao) {
            this.noteDao = noteDao;
        }

        @Override
        protected Void doInBackground(Void... Voids) {
            noteDao.deleteAllNotes();
            return null;
        }
    }

}
