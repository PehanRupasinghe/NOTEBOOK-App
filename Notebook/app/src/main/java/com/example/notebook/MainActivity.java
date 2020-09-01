package com.example.notebook;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    Button btnAdd, btnDisplay, btnEdit, btnSubscription, btnTranslate, btndis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAdd = (Button) findViewById(R.id.buttonAdd);
        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityAdd();
            }
        });

        btnDisplay = (Button) findViewById(R.id.buttonDisplay);
        btnDisplay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityDisplay();
            }
        });

        btnEdit = (Button) findViewById(R.id.buttonEdit);
        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityEdit();
            }
        });

        btnSubscription = (Button) findViewById(R.id.buttonLanguage);
        btnSubscription.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityLanguage();
            }
        });

        btnTranslate = (Button) findViewById(R.id.buttonTranslate);
        btnTranslate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityTranslate();
            }
        });


    }
    public void ActivityAdd(){
        Intent intent =new Intent(this,InsertPhasesActivity.class ); //go to add phrase
        startActivity(intent);
    }

    public void ActivityDisplay(){
        Intent intent =new Intent(this,DisplayPhasesActivity.class ); //go to add phrase
        startActivity(intent);
    }

    public void ActivityEdit(){
        Intent intent =new Intent(this,UpdatePhasesActivity.class ); //go to add phrase
        startActivity(intent);
    }

    public void ActivityLanguage(){
        Intent intent =new Intent(this,SubscriptionActivity.class ); //go to add Language Subscription
        startActivity(intent);
    }

    public void ActivityTranslate(){
        Intent intent =new Intent(this,TranslatePhasesActivity.class ); //go to add Translate
        startActivity(intent);
    }


}