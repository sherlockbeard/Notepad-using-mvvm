package com.sherlock.notepad;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private NoteViewModle noteViewModle;
    public static final int Add_Note_Request=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.recycle_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        final NoteAdapter adapter = new NoteAdapter();
        recyclerView.setAdapter(adapter);

        FloatingActionButton floatingActionButton = findViewById(R.id.add_note);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,note_add.class);
                startActivityForResult(intent,Add_Note_Request);

            }
        });

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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==Add_Note_Request && resultCode==RESULT_OK){
            String title = data.getStringExtra(note_add.Extra_Title);
            String desc = data.getStringExtra(note_add.Extra_Desc);
            int priority = data.getIntExtra(note_add.Extra_Pririty,1);

            notes notes = new notes(title,desc,priority);
            noteViewModle.insert(notes);

            Toast.makeText(getApplicationContext(),"Note Saved",Toast.LENGTH_LONG);
        }else{
            Toast.makeText(getApplicationContext(),"Nothing Happed",Toast.LENGTH_LONG);
        }
    }
}
