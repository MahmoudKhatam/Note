package com.example.application.note;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteViewModel extends AndroidViewModel {
    private NoteRepository repository;
    private LiveData<List<notes>> allNotes;
    public NoteViewModel(@NonNull Application application) {
        super(application);
        repository = new NoteRepository(application);
        allNotes = repository.getAllNotes();
    }
    public void insert (notes notes){
    repository.insert(notes);
    }
    public void update (notes notes){
        repository.update(notes);
    }
    public void delete (notes notes){
        repository.delete(notes);
    }
    public void deleteAllNotes(){
        repository.deleteAll();
    }

    public LiveData<List<notes>> getAllNotes() {
        return allNotes;
    }
}
