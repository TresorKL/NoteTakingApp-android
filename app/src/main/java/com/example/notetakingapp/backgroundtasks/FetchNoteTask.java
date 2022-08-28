package com.example.notetakingapp.backgroundtasks;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

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
             //String result = bf.readLine();
            JSONObject jsonObject = new JSONObject(bf.readLine());
            //JSONArray jsonArray =jsonObject.getJSONArray("notes");
            System.out.println(jsonObject);







        }catch (Exception ex){
            Log.d("EXCEPTION: ",ex.getMessage());
            System.out.println(ex);
        }

        return null;
    }
}
