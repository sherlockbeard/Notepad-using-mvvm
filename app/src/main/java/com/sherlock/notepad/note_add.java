package com.sherlock.notepad;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

public class note_add extends AppCompatActivity {
    public static final String Extra_Title= "com.sherlock.notepad.Extra_Title";
    public static final String Extra_Desc= "com.sherlock.notepad.Extra_desc";
    public static final String Extra_Pririty= "com.sherlock.notepad.Extra_proprity";

    private EditText editTextTitle;
    private EditText editTextdesc;
    private NumberPicker picker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_add);
        editTextTitle = findViewById(R.id.editText_title);
        editTextdesc = findViewById(R.id.editText_descr);
        picker.setMaxValue(10);
        picker.setMinValue(1);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);
        setTitle("add Note");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.menu_add_note,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.save_note:
                savenote();
                return true;
                default:
                    return super.onOptionsItemSelected(item);
        }
    }

    private void savenote() {
    String Title = editTextTitle.getText().toString();
    String desc = editTextdesc.getText().toString();
    int proprity = picker.getValue();

    if(Title.trim().isEmpty()|| desc.trim().isEmpty()){
        Toast.makeText(getApplicationContext(),"Enter data properly",Toast.LENGTH_LONG);
        return;
    }
        Intent intent = new Intent();
        intent.putExtra(Extra_Title,Title);
        intent.putExtra(Extra_Desc,desc);
        intent.putExtra(Extra_Pririty,proprity);
        setResult(RESULT_OK,intent);
        finish();

    }
}
