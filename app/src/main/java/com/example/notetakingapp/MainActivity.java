package com.example.notetakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.example.notetakingapp.adapter.NoteAdapter;

public class MainActivity extends AppCompatActivity {
    RecyclerView noteRecycler;
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