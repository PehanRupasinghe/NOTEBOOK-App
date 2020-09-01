package com.example.notebook;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DisplayPhasesActivity extends AppCompatActivity {
    PharseDBHelper database;

    ListView notesListView;

    ArrayList<String> arrayNote;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_phases);

        database = new PharseDBHelper(this);
        arrayNote = new ArrayList<>();
        notesListView = findViewById(R.id.addPhraseList);
        DisplayView();  //

        notesListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int pos, long id) {
                String text = notesListView.getItemAtPosition(pos).toString();
                Toast.makeText(DisplayPhasesActivity.this,""+text, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void DisplayView() {
        Cursor cursor = database.printNote();

        if (cursor.getCount() == 0){
            Toast.makeText(this, "List is Empty", Toast.LENGTH_SHORT).show();

        } else {
            while (cursor.moveToNext()){
                arrayNote.add(cursor.getString(1));
            }

            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayNote); //display the radio button
            notesListView.setAdapter(adapter);
        }
    }
}
