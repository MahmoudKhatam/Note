package com.example.application.note;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface noteDao {
    @Update
    void update (notes notes);
    @Insert
    void insert (notes notes);
    @Delete
    void delete (notes notes);
    @Query( " DELETE FROM note_table" )
    void deleteAllNotes();
    @Query( "Select* FROM note_table ORDER BY priority DESC " )
    LiveData<List<notes>> getAllNotes();

}
