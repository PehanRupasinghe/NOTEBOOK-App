package com.example.notebook;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class TranslatePhasesActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    LanguageDBHelper db;
    PharseDBHelper database;
    Spinner selectedLanguage;
    ArrayAdapter<String> adapter;
    ListView notesListView;

    ArrayList<String> arrayNote;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_translate_phases);

        db = new LanguageDBHelper(this);
        database = new PharseDBHelper(this);

        arrayNote = new ArrayList<>();
        notesListView = findViewById(R.id.addPhraseList);
        selectedLanguage = findViewById(R.id.spinner);

        SelectedLanguages(); //Show the Dropdown Button
        DisplayView(); // Show the Phrase list
    }

    // method get Selected languages
    public void SelectedLanguages(){
        ArrayList<String> checkedList = new ArrayList<>();

        Cursor c = db.LanguagesChecked();
        while (c.moveToNext()){
            checkedList.add(c.getString(1)); //adding all the checked Languages to arraylist
        }

        selectedLanguage.setOnItemSelectedListener(this);

        adapter = new ArrayAdapter<>(this,android.R.layout.simple_spinner_dropdown_item,checkedList);
        selectedLanguage.setAdapter(adapter);

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    // the Phase in list
    private void DisplayView() {
        Cursor cursor = database.printNote(); //get Phase from list

        if (cursor.getCount() == 0){
            Toast.makeText(this, "List is Empty", Toast.LENGTH_SHORT).show();

        } else {
            while (cursor.moveToNext()){
                arrayNote.add(cursor.getString(1)); //Store in a Array List
            }

            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayNote); //display the radio button
            notesListView.setAdapter(adapter);
        }
    }
}