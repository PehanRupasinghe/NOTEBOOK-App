package com.example.notebook;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class UpdatePhasesActivity extends AppCompatActivity {
    PharseDBHelper database;

    Button buttonSave, buttonEdit;
    EditText phraseText;
    ListView phraselist;

    ArrayList<String> arrayNoteList;
    ArrayAdapter adapter;

    String  NOTE, P_ID;
    Integer pos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_phases);

        database = new PharseDBHelper(this);

        arrayNoteList = new ArrayList<>();

        buttonSave = findViewById(R.id.btnSave);
        buttonEdit = findViewById(R.id.btnEdit);
        phraseText = findViewById(R.id.insertPhrase2);
        phraselist = findViewById(R.id.updatePharaseList);

        listDisplay();

        phraselist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String text = phraselist.getItemAtPosition(position).toString();
                pos = position;
                Toast.makeText(UpdatePhasesActivity.this,""+text,Toast.LENGTH_SHORT).show();
                Cursor idData = database.getNoteID(text);
                String IDitem = "";
                while (idData.moveToNext()){
                    IDitem = idData.getString(0);
                }

                Toast.makeText(UpdatePhasesActivity.this,"ID : "+IDitem,Toast.LENGTH_SHORT).show();
                P_ID = IDitem;


            }
        });

        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phraseText.setText(arrayNoteList.get(pos));
            }
        });

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NOTE = phraseText.getText().toString();
                boolean Updated = database.editNote(P_ID, NOTE);
                if(Updated == true){
                    Toast.makeText(UpdatePhasesActivity.this, "NOTE UPDATED", Toast.LENGTH_SHORT).show();
                    arrayNoteList.clear(); //
                    listDisplay();
                    phraseText.setText("");

                } else {
                    Toast.makeText(UpdatePhasesActivity.this, "NOTE IS NOT UPDATED", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void listDisplay(){
        Cursor c = database.printNote();

        if (c.getCount() == 0){
            Toast.makeText(this, "NO NOTE IN THE LIST", Toast.LENGTH_SHORT).show();

        } else {
            while (c.moveToNext()){
                arrayNoteList.add(c.getString(1));
            }

            adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_single_choice, arrayNoteList);
            phraselist.setAdapter(adapter);
        }

    }

}
