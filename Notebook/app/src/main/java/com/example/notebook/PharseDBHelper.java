package com.example.notebook;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class PharseDBHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Notes.db";
    private static final String TABLE_NAME = "NOTES_Table";
    private static final String ID = "ID";
    private static final String NOTE = "NOTE";

    public PharseDBHelper(Context context){
        super(context, DATABASE_NAME, null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +" (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + NOTE + " TEXT);";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertNote(String text){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(NOTE, text);

        long result = db.insert(TABLE_NAME, null, contentValues);

        return result != -1;
    }

    public Cursor printNote(){
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM "+ TABLE_NAME + " ORDER BY " + NOTE + " ASC ";
        Cursor cursor = db.rawQuery(query,null);

        return cursor;
    }

    public Cursor getNoteID(String phrase){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = " SELECT " + ID + " FROM " + TABLE_NAME + " WHERE " + NOTE + " = '" + phrase + "'";
        Cursor cursor = db.rawQuery(query, null);
        return cursor;
    }

    public boolean editNote(String id, String note){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(ID, id);
        contentValues.put(NOTE, note);
        db.update(TABLE_NAME, contentValues, "ID = ?",new String[]{ id } );
        return true;

    }
}
