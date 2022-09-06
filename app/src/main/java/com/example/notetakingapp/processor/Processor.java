package com.example.notetakingapp.processor;

import com.example.notetakingapp.note.Note;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Processor {

    public Processor(){

    }

    //-------------------------------------------------------------------------------
    //
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

         //note.setId(Long.parseLong(noteJsonObject.get("id").toString()));
     }

        return noteList;
    }
}
