package com.hfad.finalyearproject;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.net.ConnectException;

import static android.provider.Telephony.TextBasedSmsColumns.PERSON;

/**
 * Created by lukeboyde on 01/03/2018.
 */

public class DatabaseTable extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Recommendation";
    private static final int DATABASE_VERSION = 1;
    public static final String RECOMMENDATIONS_TABLE_NAME = "Recommendations";
    public static final String RECOMMENDATIONS_COLUMN_ID = "_id";
    public static final String RECOMMENDATIONS_COLUMN_ACTIVITIES = "activities";
    public static final String RECOMMENDATIONS_COLUMN_EVENTS = "events";

    public DatabaseTable(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + RECOMMENDATIONS_TABLE_NAME + "(" +
                RECOMMENDATIONS_COLUMN_ID + " TEXT. " +
                RECOMMENDATIONS_COLUMN_ACTIVITIES + " TEXT. " +
                RECOMMENDATIONS_COLUMN_EVENTS + " TEXT)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + RECOMMENDATIONS_TABLE_NAME);
        onCreate(db);
    }

    public boolean insertRecommendation(String activities, String events) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(RECOMMENDATIONS_COLUMN_ACTIVITIES, activities);
        contentValues.put(RECOMMENDATIONS_COLUMN_EVENTS, events);
        db.insert(RECOMMENDATIONS_TABLE_NAME, null, contentValues);
        return true;
    }

    public boolean updateRecommendation(Integer id, String activities, String events) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(RECOMMENDATIONS_COLUMN_ACTIVITIES, activities);
        contentValues.put(RECOMMENDATIONS_COLUMN_EVENTS, events);
        db.update(RECOMMENDATIONS_TABLE_NAME, contentValues, RECOMMENDATIONS_COLUMN_ID + " = ? ", new String[]{Integer.toHexString(id)});
        return true;
    }

    public Cursor getRecommendation(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("SELECT * FROM " + RECOMMENDATIONS_TABLE_NAME + " WHERE " +
                RECOMMENDATIONS_COLUMN_ID + "=?", new String[]{Integer.toString(id)});
        return res;
    }

    public Cursor getAllRecommendations() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery( "SELECT * FROM " + RECOMMENDATIONS_TABLE_NAME, null);
        return res;
    }

}

