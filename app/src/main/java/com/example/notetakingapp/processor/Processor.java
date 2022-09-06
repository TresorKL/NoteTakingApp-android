package com.example.notetakingapp.processor;

import android.content.Context;
import android.widget.Toast;

import com.example.notetakingapp.backgroundtasks.FetchNoteTask;
import com.example.notetakingapp.note.Note;
import com.example.notetakingapp.service.NoteClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Processor {

    public Processor(){

    }

    //-------------------------------------------------------------------------------
    // Convert JsonArray to List of notes
    //-------------------------------------------------------------------------------
    public List<Note> retrieveNotesList(JSONArray notesArray) throws JSONException {

        List<Note>noteList = new ArrayList<>();

     for(int i=0; i<notesArray.length();i++){


         Note note =new Note();
         JSONObject noteJsonObject= notesArray.getJSONObject(i);

         // extract field values from JsonObject
         Long id = Long.parseLong(noteJsonObject.get("id").toString());

        // LocalDate date = LocalDate.parse(noteJsonObject.get("dateOfEdition").toString());
         String title= noteJsonObject.getString("title").toString();
         String noteBody =noteJsonObject.getString("noteBody");
         String noteBackgroundColor=noteJsonObject.getString("noteBackgroundColor");

         // set values to note object
         note.setId(id);
       //  note.setDateOfEdition(date);
         note.setTitle(title);
         note.setNoteBody(noteBody);
         note.setNoteBackgroundColor(noteBackgroundColor);

         noteList.add(note);

     }

        return noteList;
    }

    //-------------------------------------------------------------------------------
    // Post/Save Note using Retrofit
    //-------------------------------------------------------------------------------
    public void sendRequestToSaveNote(Note note, Context context){

        int userId = 2;
        Retrofit.Builder builder= new Retrofit.Builder()
                .baseUrl("http://192.168.0.60:8080/")
                .addConverterFactory(GsonConverterFactory.create());

        Retrofit retrofit = builder.build();

        NoteClient client= retrofit.create(NoteClient.class);

        Call<Note> call =client.addNote(note);
       // Toast.makeText(context, "NOTE SAVED", Toast.LENGTH_SHORT).show();
        call.enqueue(new Callback<Note>() {
            @Override
            public void onResponse(Call<Note> call, Response<Note> response) {
              Toast.makeText(context, "TITLE :"+response.body().getTitle(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onFailure(Call<Note> call, Throwable t) {
              //  Toast.makeText(context, "Oops something went wrong!!", Toast.LENGTH_SHORT).show();
            }
        });




    }



}
