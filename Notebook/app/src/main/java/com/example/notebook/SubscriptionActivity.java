package com.example.notebook;

import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class SubscriptionActivity extends AppCompatActivity {

    LanguageDBHelper db;
    private ListView LangauageList;
    private ArrayAdapter adapter;

    //Array with Language name
    private String[] langName = new String[]{"Afrikaans", "Albanian", "Arabic", "Armenian", "Azerbaijani", "Bashkir", "Basque", "Belarusian", "Bengali", "Bulgarian", "Burmese", "Catalan", "Central Khmer", "Chinese (Simplified)", "Chinese (Traditional)",
            "Chuvash", "Croatian", "Czech", "Danish", "Dutch", "English", "Esperanto", "Estonian", "Finnish", "French", "Georgian", "German", "Greek", "Gujarati", "Haitian", "Hebrew", "Hindi", "Hungarian", "Icelandic",
            "Irish", "Italian", "Japanese", "Kazakh", "Kirghiz", "Korean", "Kurdish", "Lao", "Latvian", "Lithuanian", "Malay", "Malayalam", "Maltese", "Marathi", "Mongolian", "Nepali", "Norwegian Bokmal", "Norwegian Nynorsk",
            "Punjabi", "Punjabi (Shahmukhi script, Pakistan)", "Persian", "Polish", "Portuguese", "Pushto", "Romanian", "Russian", "Serbian", "Sinhala", "Slovakian", "Slovenian", "Somali", "Spanish", "Swedish",
            "Tagalog", "Tamil", "Telugu", "Thai", "Turkish", "Ukrainian", "Urdu", "Vietnamese"};

    //Array with Language code
    private String[] langCode = new String[]{"af", "sq", "ar", "hy", "az", "ba", "eu", "be", "bn", "bg", "my", "ca", "km", "zh", "zh-TW", "cv", "hr", "cs", "da", "nl", "en", "eo", "et", "fi", "fr", "ka", "de", "el", "gu", "ht", "he", "hi", "hu", "is", "ga", "it"
            , "ja", "kk", "ky", "ko", "ku", "lo", "lv", "lt", "ms", "ml", "mt", "mr", "mn", "ne", "nb", "nn", "pa", "pa-PK", "fa", "pl", "pt", "ps", "ro", "ru", "sr", "si", "sk", "sl", "so", "es", "sv", "tl", "ta", "te", "th", "tr"
            , "uk", "ur", "vi"};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subscription);

        db = new LanguageDBHelper(this);
        LangauageList = findViewById(R.id.langLists); //the List view is assign it to the listViewLanguages

        Cursor c = db.printLanguages();
        if (c.getCount() == 0) {        //check is there language in the Database
            Toast.makeText(SubscriptionActivity.this, "No Languages In Database", Toast.LENGTH_SHORT).show();
            AddLanguages();
            displayView(); //Display language in list view

        } else {
            Toast.makeText(SubscriptionActivity.this, "Languages Are Present In Database", Toast.LENGTH_SHORT).show();
            SelectedLanguages();  //get all the language with cheaked
        }


    }

    //method which add language to the Databse
    private void AddLanguages() {
        for (int i = 0; i < langName.length; i++) { //For loop run till all the assign values are taken from the array
            String name = langName[i]; //get the name
            String code = langCode[i];  //get the code
            boolean added = db.addLanguage(name, code, 0);
            if (added) {
                displayView();
                Toast.makeText(SubscriptionActivity.this, "Adding Languages", Toast.LENGTH_SHORT).show();

            } else {
                Toast.makeText(SubscriptionActivity.this, "ERROR, Language Not Added", Toast.LENGTH_SHORT).show();
            }
        }
    }
    // display the languages in the listView
    public void displayView() {
        ArrayList<String> LanguageList = new ArrayList<>();

        Cursor c = db.printLanguages();
        while (c.moveToNext()) {
            LanguageList.add(c.getString(1)); //Adding all the Data to array list
        }

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, LanguageList); //the array adapter layout is set for multiple choice
        LangauageList.setAdapter(adapter);
    }

    //method to get all the cheaked language
    public void SelectedLanguages() {
        displayView();
        ArrayList<String> checkedLanguageList = new ArrayList<>();

        Cursor c = db.LanguagesChecked(); //get all the checked language names
        while (c.moveToNext()) {
            checkedLanguageList.add(c.getString(1)); //and add it to list
        }

        for (int position = 0; position < adapter.getCount(); position++) { //for loop go in order of the list view (possition)
            String languageName = (String) LangauageList.getItemAtPosition(position);
            boolean isChecked = checkedLanguageList.contains(languageName); //check whether language is check from array list
            if (isChecked) {                //if the languageName  is the in the list cheak button is put true
                LangauageList.setItemChecked(position, true);
            }
        }
    }

    //Update the Whole list view with check button is click or not
    public void OnClickUpdateSelectedLanguage(View view) {
        SparseBooleanArray checked = LangauageList.getCheckedItemPositions();
        //Show Toast soon as update selected
        Toast.makeText(SubscriptionActivity.this, "UPDATING..", Toast.LENGTH_SHORT).show();
        Toast.makeText(SubscriptionActivity.this, "UPDATING....", Toast.LENGTH_SHORT).show();

        for (int position = 0; position < adapter.getCount(); position++) {
            if (checked.get(position)) { // check item is clicked
                String LanguageName = (String) LangauageList.getItemAtPosition(position);
                db.updateCheckedLanguages(1, LanguageName); //if click is true set checked to 1 in dataBase

            } else {
                String LanguageName = (String) LangauageList.getItemAtPosition(position);
                db.updateCheckedLanguages(0, LanguageName); //else 0
            }
        } //for loop take time
    }

}