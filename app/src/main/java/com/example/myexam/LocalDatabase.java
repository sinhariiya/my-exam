package com.example.myexam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class LocalDatabase extends SQLiteOpenHelper {

    public static final String database_name = "local.db";
    public static final String table_name = "user_info";

    public static final String col_1 = "name";
    public static final String col_2 = "username";
    public static final String col_3 = "password";

    public static final String exam_table = "Exam_Info";
    public static final String examcol_1 = "id";
    public static final String examcol_2 = "user_name";
    public static final String examcol_3 = "examname";
    public static final String examcol_4 = "totalmarks";
    public static final String examcol_5 = "marksobtained";
    public static final String examcol_6 = "grade";
    public static final String examcol_7 = "date";

    public LocalDatabase(@Nullable Context context) {
        super(context, database_name, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" create table " + table_name + "(name TEXT,username TEXT PRIMARY KEY,password TEXT)");
        db.execSQL(" create table " + exam_table + "(id INTEGER PRIMARY KEY AUTOINCREMENT,user_name TEXT,examname TEXT,totalmarks INTEGER,marksobtained INTEGER,grade TEXT,date TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + table_name);
        db.execSQL("DROP TABLE IF EXISTS " + exam_table);
        onCreate(db);
    }

    public boolean insertData(String name, String username, String password) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(col_1, name);
        cv.put(col_2, username);
        cv.put(col_3, password);
        long result = db.insert(table_name, null, cv);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public String getPassword(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String password = "Not found";
        String querry = "SELECT username,password from " + table_name;
        Cursor cursor = db.rawQuery(querry, null);
        if (cursor.moveToFirst()) {
            do {
                if (cursor.getString(0).equals(username)) {
                    password = cursor.getString(1);
                    break;
                }
            } while ((cursor.moveToNext()));
        }
        return password;
    }

    public String getName(String userName) {
        SQLiteDatabase db = this.getReadableDatabase();
        String name = "not match";
        String querry1 = "SELECT name from " + table_name + " WHERE " + col_2 + " =? ";
        Cursor cursor = db.rawQuery(querry1, new String[]{userName});
        cursor.moveToFirst();
        name = cursor.getString(cursor.getColumnIndex("name"));

        return name;

    }

    public boolean insertexamData(String user_name, String exam_name, String exam_totalmarks,String exam_marksobtained,String exam_grade,String exam_date) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(examcol_2, user_name);
        contentValues.put(examcol_3, exam_name);
        contentValues.put(examcol_4,exam_totalmarks);
        contentValues.put(examcol_5,exam_marksobtained);
        contentValues.put(examcol_6,exam_grade);
        contentValues.put(examcol_7,exam_date);
        long result = db.insert(exam_table, null, contentValues);
        if (result == -1) {
            return false;
        } else {
            return true;
        }
    }

    public Cursor getexamName(String username) {
        SQLiteDatabase db = this.getReadableDatabase();
        String querry2 = "SELECT examname,totalmarks,marksobtained,grade,date from " + exam_table + " WHERE " +examcol_2 + " =? ";
        Cursor cu = db.rawQuery(querry2, new String[]{username});
        return cu;

    }


}





