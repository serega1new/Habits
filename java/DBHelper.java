package com.example.habits_01;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "DBHabits1";

    public static final String TABLE_HABITS = "habits";

    public static final String KEY_ID_T_HABITS = "_id";
    public static final String KEY_NAME= "name";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_DURATION = "duration";
    public static final String KEY_START_DATE = "startdate";

    public static final String TABLE_HABIT_TIME = "habittime";
    public static final String KEY_ID_T_HABIT_TIME = "_id";
    public static final String KEY_ID_F_HABITS = "id_f_habits";
    public static final String KEY_DATE = "date";


    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_HABITS + " ( " +
                KEY_ID_T_HABITS  + " integer primary key, " +
                KEY_NAME + " text, " +
                KEY_DESCRIPTION + " text, " +
                KEY_DURATION + " integer, " +
                KEY_START_DATE + " integer " + ")");

        db.execSQL("create table " + TABLE_HABIT_TIME + " (" +
                KEY_ID_T_HABIT_TIME  + " integer primary key, " +
                KEY_ID_F_HABITS  + " integer, " +
                KEY_DATE + " integer " + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_HABITS );
        db.execSQL("drop table if exists " + TABLE_HABIT_TIME );

        onCreate(db);

    }
}
