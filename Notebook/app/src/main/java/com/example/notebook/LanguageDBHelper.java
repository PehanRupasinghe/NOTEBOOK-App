package com.example.notebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class LanguageDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "ListOfLanguages.db";
    private static final String LANGUAGE_TABLE = "LANGUAGES";

    private static final String LANGUAGE_ID = "ID";
    private static final String LANGUAGE_NAME = "NAME";
    private static final String LANGUAGE_CODE = "CODE";
    private static final String SUBSCRIBE = "CHECKED";

    public LanguageDBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + LANGUAGE_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT,NAME TEXT,CODE TEXT,CHECKED INTEGER) ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onCreate(db);
    }



    public boolean addLanguage(String language, String code, int checked) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(LANGUAGE_NAME, language);
        contentValues.put(LANGUAGE_CODE, code);
        contentValues.put(SUBSCRIBE, checked);
        long result = db.insert(LANGUAGE_TABLE, null, contentValues);
        return result != -1;
    }


    public Cursor printLanguages() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + LANGUAGE_TABLE, null);
        return cursor;
    }


    public Cursor LanguagesChecked() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + LANGUAGE_TABLE + " where " + SUBSCRIBE + " = " + " 1 ", null);
        return cursor;
    }


    public Cursor getLanguageCode(String nameOfLanguage) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + LANGUAGE_TABLE + " WHERE " + LANGUAGE_NAME + " = '" + nameOfLanguage + "'", null);
        return cursor;
    }


    public void updateCheckedLanguages(int number , String nameOfLanguage ) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + LANGUAGE_TABLE + " SET " + SUBSCRIBE +
                " = '" + number + "' WHERE " + LANGUAGE_NAME + " = '" + nameOfLanguage + "'";

        db.execSQL(query);
    }

}

