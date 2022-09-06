package com.example.notetakingapp.backgroundtasks;

import android.os.AsyncTask;
import android.util.Log;

import com.example.notetakingapp.note.Note;
import com.example.notetakingapp.processor.Processor;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.net.ssl.HttpsURLConnection;

public class FetchNoteTask extends AsyncTask<String,String,String> {

    public FetchNoteTask() {
    }

    @Override
    protected String doInBackground(String... strings) {

        try {

            String allNotesApi="http://192.168.0.60:8080/notes/1";
            URL url = new URL(allNotesApi);
            HttpURLConnection connection= (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();



            // receive result
            BufferedReader bf=new BufferedReader(new InputStreamReader(connection.getInputStream()));
             String result = bf.readLine();

           JSONArray jsonarray = new JSONArray(result);

            Processor processor = new Processor();

            List<Note>noteList = processor.retrieveNotesList(jsonarray);
            String noteString="";
            for(int i=0;i<noteList.size();i++){
                noteString=noteString+noteList.get(i).getTitle().concat(" --> ");
            }
            System.out.println("TITLES FOUND!!!!!!!!!!!!!: "+noteString);

            System.out.println(jsonarray);
        }catch (Exception ex){
           // Log.d("EXCEPTION: ",ex.getMessage());
          System.out.println("oops !!!!!!  "+ex);
        }

        return null;
    }


}
