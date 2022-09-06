package com.example.notetakingapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.notetakingapp.backgroundtasks.SaveNoteTask;
import com.example.notetakingapp.note.Note;
import com.example.notetakingapp.processor.Processor;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class NoteActivity extends AppCompatActivity {

    EditText title, noteBody;
    ImageButton saveNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        //------------------------------------------------------------
        // customise status bar
        //------------------------------------------------------------
        Window window = NoteActivity.this.getWindow();

        // clear FLAG_TRANSLUCENT_STATUS flag:
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        // add FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS flag to the window
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);

        // finally change the color
        window.setStatusBarColor(ContextCompat.getColor(NoteActivity.this, R.color.black));


        title = findViewById(R.id.title);
        noteBody = findViewById(R.id.noteBody);


        saveNote = findViewById(R.id.saveNote);

        saveNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String strTitle = title.getText().toString();
                String strNoteBody = noteBody.getText().toString();

                Note note = new Note();

                note.setTitle(strTitle);
                note.setNoteBody(strNoteBody);

                Processor processor=new Processor();
               processor.sendRequestToSaveNote(note, NoteActivity.this);
              // new SaveNoteTask(NoteActivity.this,note).execute();
              //  Toast.makeText(NoteActivity.this, "I am clicked", Toast.LENGTH_SHORT).show();

            }
        });


    }



}