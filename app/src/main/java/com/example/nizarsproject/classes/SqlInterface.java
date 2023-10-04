package com.example.nizarsproject.classes;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public interface SqlInterface {

    long Add(SQLiteDatabase db);
    int Delete(SQLiteDatabase db, int id);
    int Update(SQLiteDatabase db, int id);
    Cursor Select(SQLiteDatabase db);
}
