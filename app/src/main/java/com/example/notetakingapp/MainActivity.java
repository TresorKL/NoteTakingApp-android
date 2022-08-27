package com.example.notetakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.notetakingapp.adapter.NoteAdapter;

public class MainActivity extends AppCompatActivity {
    RecyclerView noteRecycler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        noteRecycler=findViewById(R.id.noteRecycler);

        // data to populate the RecyclerView with
        String[] data = {"1", "2", "3", "4", "5", "6", "7", "8", "9", "10","1", "2", "3", "4", "5", "6", "7", "8", "9", "10"};

        int numberOfColumns =3;
      // noteRecycler.setLayoutManager(new LinearLayoutManager(this));

          noteRecycler.setLayoutManager(new GridLayoutManager(this, numberOfColumns,GridLayoutManager.VERTICAL, false));
        NoteAdapter adapter=new NoteAdapter(data,this);
       // adapter = new MyRecyclerViewAdapter(this, data);
      //  adapter.setClickListener(this);
        noteRecycler.setAdapter(adapter);


    }
}