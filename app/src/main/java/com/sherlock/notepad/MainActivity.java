package com.sherlock.notepad;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private NoteViewModle noteViewModle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final NoteAdapter adapter = new NoteAdapter();
        recyclerView.setAdapter(adapter);

        noteViewModle = ViewModelProviders.of(this).get(NoteViewModle.class);
        noteViewModle.getAllNotes().observe(this, new Observer<List<notes>>() {
            @Override
            public void onChanged(List<notes> notes) {
                //take care of recycling
                adapter.setNotes(notes);
                Toast.makeText(getApplicationContext(),"onChanged",Toast.LENGTH_SHORT);
            }
        });
    }
}
