package com.sherlock.notepad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private NoteViewModle noteViewModle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        noteViewModle = ViewModelProviders.of(this).get(NoteViewModle.class);
        noteViewModle.getAllNotes().observe(this, new Observer<List<notes>>() {
            @Override
            public void onChanged(List<notes> notes) {
                //take care of recycling
                Toast.makeText(getApplicationContext(),"onChanged",Toast.LENGTH_SHORT);
            }
        });
    }
}
