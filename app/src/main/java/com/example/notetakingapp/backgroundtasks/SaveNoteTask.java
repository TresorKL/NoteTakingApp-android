package com.example.notetakingapp.backgroundtasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.example.notetakingapp.NoteActivity;
import com.example.notetakingapp.note.Note;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SaveNoteTask extends AsyncTask<String, String, String> {

    Note note;
    Context context;
    public SaveNoteTask(Context context, Note note){
        this.context=context;
        this.note=note;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

       ;
    }

    @Override
    protected String doInBackground(String... strings) {


        try {
            //Toast.makeText(context, "start saving", Toast.LENGTH_SHORT).show();
            int userId = 2;

            String addNoteAPI = "http://192.168.0.60:8080/note/" + userId;
            URL url = new URL(addNoteAPI);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");

            connection.setRequestProperty("Content-Type", "application/json");

            connection.setRequestProperty("Accept", "application/json");

            JSONObject noteJson = toJSON(note);

            try (OutputStream os = connection.getOutputStream()) {
                byte[] input = noteJson.toString().getBytes("utf-8");
                os.write(input, 0, input.length);
            }

            connection.connect();
           // Toast.makeText(context, "Note saved", Toast.LENGTH_SHORT).show();

        } catch (Exception ex) {
            System.out.println("NOTE PROBLEM:!!!!!! " + ex);
        }




        return null;
    }

    @Override
    protected void onProgressUpdate(String... values) {
        super.onProgressUpdate(values);
        Toast.makeText(context, "Processing..", Toast.LENGTH_LONG).show();

    }

    public JSONObject toJSON(Note note) throws JSONException {

        JSONObject jo = new JSONObject();

        jo.put("title", note.getTitle());
        jo.put("noteBody", note.getNoteBody());

        return jo;
    }
}
