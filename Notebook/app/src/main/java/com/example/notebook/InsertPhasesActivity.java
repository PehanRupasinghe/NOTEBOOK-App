package com.example.notebook;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class InsertPhasesActivity extends AppCompatActivity {
    PharseDBHelper database;
    EditText txtNote;
    Button insertButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_phases);

        database = new PharseDBHelper(this);
        txtNote = findViewById(R.id.inputPhrase);
        insertButton = findViewById(R.id.insertPhrase);

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String note = txtNote.getText().toString();
                if (txtNote.length() != 0){
                    EnterPhrase(note);
                    txtNote.setText("");


                } else {
                    Toast.makeText(InsertPhasesActivity.this, "Please Enter The Phrase To Add", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    public void EnterPhrase(String newText){
        boolean insertNote = database.insertNote(newText); //put the phase to the database
        if (insertNote == true){
            Toast.makeText(InsertPhasesActivity.this, "The Phrace Is Added", Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(InsertPhasesActivity.this, "ERROR, Phase Did Not Enter To The Database", Toast.LENGTH_SHORT).show();
        }
    }
}