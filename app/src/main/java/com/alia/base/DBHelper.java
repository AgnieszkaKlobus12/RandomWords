package com.alia.base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DBHelper extends SQLiteOpenHelper {
    public final static String DATABASE_NAME = "WordsDatabase";
    public static final String WORDS_TABLE_NAME = "words";
    public static final String WORDS_COLUMN_WORD = "word";

    private static DBHelper sInstance;

    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public static synchronized DBHelper getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new DBHelper(context.getApplicationContext());
        }
        return sInstance;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(
                "create table words " +
                        "(id integer primary key, word text)"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS words");
        onCreate(sqLiteDatabase);
    }

    public boolean insertWord(String word) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        Cursor cursor = db.rawQuery("select * from words where word=?", new String[]{word});
        if (cursor.getCount() == 0) {
            contentValues.put("word", word);
            db.insert("words", null, contentValues);
            return true;
        }
        return false;
    }

    public String getWord(int id) {
        String word = null;
        SQLiteDatabase db = getReadableDatabase();
        Cursor res = db.rawQuery("select word from words where id=" + id, null);
        if (res != null && res.moveToFirst()) {
            word = res.getString(res.getColumnIndex(WORDS_COLUMN_WORD));
            res.close();
        }
        return word;
    }

    public int numberOfRows() {
        SQLiteDatabase db = getReadableDatabase();
        return (int) DatabaseUtils.queryNumEntries(db, WORDS_TABLE_NAME);
    }

    public Integer deleteWord(String word) {
        SQLiteDatabase db = getWritableDatabase();
        return db.delete("words",
                "word = ? ",
                new String[]{word});
    }

    public ArrayList<String> getAllWords() {
        ArrayList<String> array_list = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from words", null);
        res.moveToFirst();

        while (!res.isAfterLast()) {
            array_list.add(res.getString(res.getColumnIndex(WORDS_COLUMN_WORD)));
            res.moveToNext();
        }
        res.close();
        return array_list;
    }
}