package com.example.notetakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.example.notetakingapp.adapter.NoteAdapter;
import com.example.notetakingapp.backgroundtasks.FetchNoteTask;
import com.example.notetakingapp.note.Note;
import com.example.notetakingapp.processor.Processor;

import org.json.JSONArray;
import org.json.JSONException;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;

public class MainActivity extends AppCompatActivity {
    RecyclerView noteRecycler;
    ImageView addBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        //------------------------------------------------------------
        // customise status bar
        //------------------------------------------------------------
        Window window = MainActivity.this.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(MainActivity.this, R.color.black));

        List<Note> noteList=new ArrayList<>();

        Note note=new Note();
        note.setId(Long.parseLong("25"));
        note.setNoteBody("This is a dummy note just to try the system...");
        note.setTitle("Super note");

        //noteList.add(note);
        noteRecycler=findViewById(R.id.noteRecycler);
       new FetchNoteTask( noteRecycler,this).execute();



       addBtn =findViewById(R.id.addBtn);

       addBtn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               Intent noteActivity = new Intent(MainActivity.this, NoteActivity.class);
              // Intent myIntent = new Intent(CurrentActivity.this, NextActivity.class);
             //  myIntent.putExtra("key", value); //Optional parameters
               startActivity(noteActivity);
           }
       });

//
//        int numberOfColumns =3;
//
//         NoteAdapter adapter=new NoteAdapter(noteList,this);
//        noteRecycler.setLayoutManager(new GridLayoutManager(this, numberOfColumns,GridLayoutManager.VERTICAL, false));
//
//        noteRecycler.setAdapter(adapter);




    }
}