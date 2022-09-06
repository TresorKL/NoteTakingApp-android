package com.example.notetakingapp.backgroundtasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.notetakingapp.R;
import com.example.notetakingapp.adapter.NoteAdapter;
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

public class FetchNoteTask extends AsyncTask<String, String, String> {


    RecyclerView noteRecycler;
    Context context;

    List<Note>noteList=new ArrayList<>();
    public FetchNoteTask( RecyclerView noteRecycler, Context context) {

        this.noteRecycler=noteRecycler;
        this.context=context;
    }

    @Override
    protected void onPostExecute(String s) {

        int numberOfColumns = 3;
        NoteAdapter noteAdapter = new NoteAdapter(noteList, context);
        noteRecycler.setLayoutManager(new GridLayoutManager(context, numberOfColumns, GridLayoutManager.VERTICAL, false));
        noteRecycler.setAdapter(noteAdapter);

        super.onPostExecute(s);
    }

    @Override
    protected String doInBackground(String... strings) {

        try {

            int userId=2;
            String allNotesApi = "http://192.168.0.60:8080/notes/"+userId;
            URL url = new URL(allNotesApi);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();


            // receive result
            BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String result = bf.readLine();
            JSONArray jsonarray = new JSONArray(result);
            Processor processor = new Processor();

            noteList = processor.retrieveNotesList(jsonarray);





            String noteString = "";
            for (int i = 0; i < noteList.size(); i++) {
                noteString = noteString + noteList.get(i).getTitle().concat(" --> ");
            }
            System.out.println("TITLES FOUND!!!!!!!!!!!!!: " + noteString);

            System.out.println(jsonarray);
        } catch (Exception ex) {
            // Log.d("EXCEPTION: ",ex.getMessage());
            System.out.println("oops !!!!!!  " + ex);
        }

        return null;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

        Toast.makeText(context, "LOADING NOTES", Toast.LENGTH_SHORT).show();
    }
}
