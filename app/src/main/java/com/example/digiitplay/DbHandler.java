package com.example.digiitplay;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DbHandler extends SQLiteOpenHelper {

    public static final String[] modes;

    static {
        modes = new String[]{"easy_comp", "moderate_comp", "hard_comp", "hardplus_comp", "allmodes_comp"};
    }

    public DbHandler(Context context) {
        super(context, "digiplay.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String attributes = "id INTEGER PRIMARY KEY,score INTEGER, accuracy REAL, date TEXT";
        String createEasyTable = "CREATE TABLE easy_comp(" + attributes + ")";
        String createMedTable = "CREATE TABLE moderate_comp(" + attributes + ")";
        String createHardTable = "CREATE TABLE hard_comp(" + attributes + ")";
        String createHardPlusTable = "CREATE TABLE hardplus_comp(" + attributes + ")";
        String createAllModeTable = "CREATE TABLE allmodes_comp(" + attributes + ")";
        sqLiteDatabase.execSQL(createEasyTable);
        sqLiteDatabase.execSQL(createMedTable);
        sqLiteDatabase.execSQL(createHardTable);
        sqLiteDatabase.execSQL(createHardPlusTable);
        sqLiteDatabase.execSQL(createAllModeTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String dropTable = "DROP TABLE if EXISTS " + "easy_comp";
        sqLiteDatabase.execSQL(dropTable);
    }

    public void insertData(int mode, int score, double accuracy, String date) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("score", score);
        contentValues.put("date", date);
        contentValues.put("accuracy", accuracy);

        long result = sqLiteDatabase.insert(modes[mode - 5], null, contentValues);
    }

    public ArrayList<Score> getTop5Scores(int mode) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ArrayList<Score> scoreArrayList = new ArrayList<>();
        String select = "SELECT * from " + modes[mode - 5] + " ORDER BY score DESC, accuracy DESC LIMIT 5 OFFSET 0;";
        Cursor cursor = sqLiteDatabase.rawQuery(select, null);

        if (cursor.moveToFirst()) {
            do {
                Score score = new Score();
                score.setId(Integer.parseInt(cursor.getString(0)));
                score.setScore(Integer.parseInt(cursor.getString(1)));
                score.setAccuracy(Double.parseDouble(cursor.getString(2)));
                score.setDate(cursor.getString(3));
                scoreArrayList.add(score);
            } while (cursor.moveToNext());
        }
        return scoreArrayList;
    }

    public ArrayList<Score> getLast5Scores(int mode) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ArrayList<Score> scoreArrayList = new ArrayList<>();
        String select = "SELECT * from " + modes[mode - 5] + " ORDER BY id DESC LIMIT 5 OFFSET 0;";
        Cursor cursor = sqLiteDatabase.rawQuery(select, null);

        if (cursor.moveToFirst()) {
            do {
                Score score = new Score();
                score.setId(Integer.parseInt(cursor.getString(0)));
                score.setScore(Integer.parseInt(cursor.getString(1)));
                score.setAccuracy(Double.parseDouble(cursor.getString(2)));
                score.setDate(cursor.getString(3));
                scoreArrayList.add(score);
            } while (cursor.moveToNext());
        }
        return scoreArrayList;
    }

    public String getRoundCount(int mode) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ArrayList<Score> scoreArrayList = new ArrayList<>();
        String select = "SELECT COUNT(id) from " + modes[mode - 5]+";";
        Cursor cursor = sqLiteDatabase.rawQuery(select, null);

        cursor.moveToFirst();
        String count = cursor.getString(0);
        return count;
    }

    public String getTotalScore(int mode) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ArrayList<Score> scoreArrayList = new ArrayList<>();
        String select = "SELECT SUM(score) from " + modes[mode - 5]+";";
        Cursor cursor = sqLiteDatabase.rawQuery(select, null);

        cursor.moveToFirst();
        String sum = cursor.getString(0);
        return sum;
    }

    public String getAverageAccuracy(int mode) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        ArrayList<Score> scoreArrayList = new ArrayList<>();
        String select = "SELECT AVG(accuracy) from " + modes[mode - 5]+";";
        Cursor cursor = sqLiteDatabase.rawQuery(select, null);

        cursor.moveToFirst();
        String accuracy = cursor.getString(0);
        return accuracy;
    }
}