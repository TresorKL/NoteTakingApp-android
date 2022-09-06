package com.example.notetakingapp.service;

import com.example.notetakingapp.note.Note;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface NoteClient {

    @POST("note/2")
    Call<Note> addNote(@Body Note note);
}
