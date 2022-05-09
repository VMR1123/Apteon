package com.example.digiitplay;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.digiitplay.DigitPlay.Score;

import java.util.ArrayList;

public class DbHandler extends SQLiteOpenHelper {

    public static final String[] modes1, modes2, modes3;

    String attributes1 = "id INTEGER,score INTEGER, accuracy REAL, date TEXT";
    String attributes2 = "id INTEGER PRIMARY KEY, score TEXT, accuracy TEXT, date TEXT";

    String createEasyTable = "CREATE TABLE easy_comp(" + attributes1 + ")";
    String createMedTable = "CREATE TABLE moderate_comp(" + attributes1 + ")";
    String createHardTable = "CREATE TABLE hard_comp(" + attributes1 + ")";
    String createHardPlusTable = "CREATE TABLE hardplus_comp(" + attributes1 + ")";
    String createAllModeTable = "CREATE TABLE allmodes_comp(" + attributes1 + ")";

    String createEasyEncryptedTable = "CREATE TABLE easy_encrypted_comp(" + attributes2 + ")";
    String createMedEncryptedTable = "CREATE TABLE med_encrypted_comp(" + attributes2 + ")";
    String createHardEncryptedTable = "CREATE TABLE hard_encrypted_comp(" + attributes2 + ")";
    String createHardPlusEncryptedTable = "CREATE TABLE hardplus_encrypted_comp(" + attributes2 + ")";
    String createAllEncryptedTable = "CREATE TABLE all_encrypted_comp(" + attributes2 + ")";

    public String dropTable1 = "DROP TABLE if EXISTS easy_comp";
    public String dropTable2 = "DROP TABLE if EXISTS moderate_comp";
    public String dropTable3 = "DROP TABLE if EXISTS hard_comp";
    public String dropTable4 = "DROP TABLE if EXISTS hardplus_comp";
    public String dropTable5 = "DROP TABLE if EXISTS allmodes_comp";

    static {
        modes1 = new String[]{"easy_comp", "moderate_comp", "hard_comp", "hardplus_comp", "allmodes_comp"};
    }

    static {
        modes2 = new String[]{"easy_encrypted_comp", "med_encrypted_comp", "hard_encrypted_comp", "hardplus_encrypted_comp", "all_encrypted_comp"};
    }

    static {
        modes3 = new String[]{"createEasyTable", "createMedTable", "createHardTable", "createHardPlusTable", "createAllModeTable"};
    }

    public DbHandler(Context context) {
        super(context, "digiplay.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
//        sqLiteDatabase.execSQL(createEasyTable);
//        sqLiteDatabase.execSQL(createMedTable);
//        sqLiteDatabase.execSQL(createHardTable);
//        sqLiteDatabase.execSQL(createHardPlusTable);
//        sqLiteDatabase.execSQL(createAllModeTable);

        sqLiteDatabase.execSQL(createEasyEncryptedTable);
        sqLiteDatabase.execSQL(createMedEncryptedTable);
        sqLiteDatabase.execSQL(createHardEncryptedTable);
        sqLiteDatabase.execSQL(createHardPlusEncryptedTable);
        sqLiteDatabase.execSQL(createAllEncryptedTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String dropTable = "DROP TABLE if EXISTS " + "easy_comp";
        sqLiteDatabase.execSQL(dropTable);
    }

//    public void insertData(int mode, int score, double accuracy, String date) {
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//        ContentValues contentValues = new ContentValues();
//        contentValues.put("score", score);
//        contentValues.put("date", date);
//        contentValues.put("accuracy", accuracy);
//
//        sqLiteDatabase.insert(modes1[mode - 5], null, contentValues);
//    }

    public void insertDataEncrypted(int mode, String score, String accuracy, String date) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("score", score);
        contentValues.put("date", date);
        contentValues.put("accuracy", accuracy);

        sqLiteDatabase.insert(modes2[mode - 5], null, contentValues);
    }

//    public ArrayList<String> getDecryptedData() {
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//
//        ArrayList<Score> scoreArrayList = new ArrayList<>();
//        String select = "SELECT * from easy_encrypted_comp;";
//        Cursor cursor = sqLiteDatabase.rawQuery(select, null);
//
//        cursor.moveToFirst();
//        String score = cursor.getString(1);
//        String accuracy = cursor.getString(2);
//        String date = cursor.getString(3);
//
//        ArrayList<String> arr = new ArrayList<>(3);
//        arr.add(score);
//        arr.add(accuracy);
//        arr.add(date);
//        return arr;
//    }

//    public ArrayList<Score> getTop5Scores(int mode) {
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//
//        ArrayList<Score> scoreArrayList = new ArrayList<>();
//        String select = "SELECT * from " + modes2[mode - 5] + " ORDER BY score DESC, accuracy DESC LIMIT 5 OFFSET 0;";
//        Cursor cursor = sqLiteDatabase.rawQuery(select, null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                Score score = new Score();
//                score.setId(Integer.parseInt(cursor.getString(0)));
//                score.setScore(Integer.parseInt(cursor.getString(1)));
//                score.setAccuracy(Double.parseDouble(cursor.getString(2)));
//                score.setDate(cursor.getString(3));
//                scoreArrayList.add(score);
//            } while (cursor.moveToNext());
//        }
//        return scoreArrayList;
//    }

    public ArrayList<Score> getTop5EncryptedScores(int mode) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        sqLiteDatabase.execSQL(createEasyTable);
        sqLiteDatabase.execSQL(createMedTable);
        sqLiteDatabase.execSQL(createHardTable);
        sqLiteDatabase.execSQL(createHardPlusTable);
        sqLiteDatabase.execSQL(createAllModeTable);

        EncryptDecrypt aes = new EncryptDecrypt();

        ArrayList<Score> scoreArrayList = new ArrayList<>();
        String select = "SELECT * from " + modes2[mode - 5] + ";";
        Cursor cursor = sqLiteDatabase.rawQuery(select, null);

        if (cursor.moveToFirst()) {
            do {
                ContentValues contentValues = new ContentValues();

                contentValues.put("id", cursor.getInt(0));
                contentValues.put("score", Integer.parseInt(aes.decrypt(cursor.getString(1))));
                contentValues.put("accuracy", Double.parseDouble(aes.decrypt(cursor.getString(2))));
                contentValues.put("date", aes.decrypt(cursor.getString(3)));

                sqLiteDatabase.insert(modes1[mode-5], null, contentValues);
            } while (cursor.moveToNext());
        }
        cursor.close();

        String select2 = "SELECT * from " + modes1[mode - 5] + " order by score desc limit 5 offset 0;";
        Cursor cursor2 = sqLiteDatabase.rawQuery(select2, null);

        if (cursor2.moveToFirst()) {
            do {
                Score score = new Score();

                score.setId(cursor2.getInt(0));
                score.setScore(cursor2.getInt(1));
                score.setAccuracy(cursor2.getDouble(2));
                score.setDate(cursor2.getString(3));
                scoreArrayList.add(score);
            } while (cursor2.moveToNext());
        }

        cursor2.close();

        sqLiteDatabase.execSQL(dropTable1);
        sqLiteDatabase.execSQL(dropTable2);
        sqLiteDatabase.execSQL(dropTable3);
        sqLiteDatabase.execSQL(dropTable4);
        sqLiteDatabase.execSQL(dropTable5);

        return scoreArrayList;
    }

    public ArrayList<Score> getLast5EncryptedScores(int mode) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        sqLiteDatabase.execSQL(createEasyTable);
        sqLiteDatabase.execSQL(createMedTable);
        sqLiteDatabase.execSQL(createHardTable);
        sqLiteDatabase.execSQL(createHardPlusTable);
        sqLiteDatabase.execSQL(createAllModeTable);

        EncryptDecrypt aes = new EncryptDecrypt();

        ArrayList<Score> scoreArrayList = new ArrayList<>();
        String select = "SELECT * from " + modes2[mode - 5] + ";";
        Cursor cursor = sqLiteDatabase.rawQuery(select, null);

        if (cursor.moveToFirst()) {
            do {
                ContentValues contentValues = new ContentValues();

                contentValues.put("id", cursor.getInt(0));
                contentValues.put("score", Integer.parseInt(aes.decrypt(cursor.getString(1))));
                contentValues.put("accuracy", Double.parseDouble(aes.decrypt(cursor.getString(2))));
                contentValues.put("date", aes.decrypt(cursor.getString(3)));

                sqLiteDatabase.insert(modes1[mode-5], null, contentValues);
            } while (cursor.moveToNext());
        }
        cursor.close();

        String select2 = "SELECT * from " + modes1[mode - 5] + " ORDER BY id DESC LIMIT 5 OFFSET 0;";
        Cursor cursor2 = sqLiteDatabase.rawQuery(select2, null);

        if (cursor2.moveToFirst()) {
            do {
                Score score = new Score();

                score.setId(cursor2.getInt(0));
                score.setScore(cursor2.getInt(1));
                score.setAccuracy(cursor2.getDouble(2));
                score.setDate(cursor2.getString(3));
                scoreArrayList.add(score);
            } while (cursor2.moveToNext());
        }
        cursor2.close();

        sqLiteDatabase.execSQL(dropTable1);
        sqLiteDatabase.execSQL(dropTable2);
        sqLiteDatabase.execSQL(dropTable3);
        sqLiteDatabase.execSQL(dropTable4);
        sqLiteDatabase.execSQL(dropTable5);

        return scoreArrayList;
    }

//    public ArrayList<Score> getLast5Scores(int mode) {
//        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
//
//        ArrayList<Score> scoreArrayList = new ArrayList<>();
//        String select = "SELECT * from " + modes2[mode - 5] + " ORDER BY id DESC LIMIT 5 OFFSET 0;";
//        Cursor cursor = sqLiteDatabase.rawQuery(select, null);
//
//        if (cursor.moveToFirst()) {
//            do {
//                Score score = new Score();
//                score.setId(Integer.parseInt(cursor.getString(0)));
//                score.setScore(Integer.parseInt(cursor.getString(1)));
//                score.setAccuracy(Double.parseDouble(cursor.getString(2)));
//                score.setDate(cursor.getString(3));
//                scoreArrayList.add(score);
//            } while (cursor.moveToNext());
//        }
//        return scoreArrayList;
//    }

    public String getRoundCount(int mode) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String select2 = "SELECT COUNT(id) from " + modes2[mode - 5] + ";";
        Cursor cursor2 = sqLiteDatabase.rawQuery(select2, null);

        cursor2.moveToFirst();
        String count = cursor2.getString(0);
        cursor2.close();

        return count;
    }

    public String getTotalScore(int mode) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        sqLiteDatabase.execSQL(createEasyTable);
        sqLiteDatabase.execSQL(createMedTable);
        sqLiteDatabase.execSQL(createHardTable);
        sqLiteDatabase.execSQL(createHardPlusTable);
        sqLiteDatabase.execSQL(createAllModeTable);

        EncryptDecrypt aes = new EncryptDecrypt();

        ArrayList<Score> scoreArrayList = new ArrayList<>();
        String select = "SELECT * from " + modes2[mode - 5] + ";";
        Cursor cursor = sqLiteDatabase.rawQuery(select, null);

        if (cursor.moveToFirst()) {
            do {
                ContentValues contentValues = new ContentValues();

                contentValues.put("id", cursor.getInt(0));
                contentValues.put("score", Integer.parseInt(aes.decrypt(cursor.getString(1))));
                contentValues.put("accuracy", Double.parseDouble(aes.decrypt(cursor.getString(2))));
                contentValues.put("date", aes.decrypt(cursor.getString(3)));

                sqLiteDatabase.insert(modes1[mode-5], null, contentValues);
            } while (cursor.moveToNext());
        }
        cursor.close();

        String select2 = "SELECT SUM(score) from " + modes1[mode - 5] + ";";
        Cursor cursor2 = sqLiteDatabase.rawQuery(select2, null);

        cursor2.moveToFirst();
        String sum = cursor2.getString(0);
        cursor2.close();

        sqLiteDatabase.execSQL(dropTable1);
        sqLiteDatabase.execSQL(dropTable2);
        sqLiteDatabase.execSQL(dropTable3);
        sqLiteDatabase.execSQL(dropTable4);
        sqLiteDatabase.execSQL(dropTable5);

        return sum;
    }

    public String getAverageAccuracy(int mode) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        sqLiteDatabase.execSQL(createEasyTable);
        sqLiteDatabase.execSQL(createMedTable);
        sqLiteDatabase.execSQL(createHardTable);
        sqLiteDatabase.execSQL(createHardPlusTable);
        sqLiteDatabase.execSQL(createAllModeTable);

        EncryptDecrypt aes = new EncryptDecrypt();

        ArrayList<Score> scoreArrayList = new ArrayList<>();
        String select = "SELECT * from " + modes2[mode - 5] + ";";
        Cursor cursor = sqLiteDatabase.rawQuery(select, null);

        if (cursor.moveToFirst()) {
            do {
                ContentValues contentValues = new ContentValues();

                contentValues.put("id", cursor.getInt(0));
                contentValues.put("score", Integer.parseInt(aes.decrypt(cursor.getString(1))));
                contentValues.put("accuracy", Double.parseDouble(aes.decrypt(cursor.getString(2))));
                contentValues.put("date", aes.decrypt(cursor.getString(3)));

                sqLiteDatabase.insert(modes1[mode-5], null, contentValues);
            } while (cursor.moveToNext());
        }
        cursor.close();

        String select2 = "SELECT AVG(accuracy) from " + modes1[mode - 5] + ";";
        Cursor cursor2 = sqLiteDatabase.rawQuery(select2, null);

        cursor2.moveToFirst();
        String accuracy = cursor2.getString(0);
        cursor2.close();

        sqLiteDatabase.execSQL(dropTable1);
        sqLiteDatabase.execSQL(dropTable2);
        sqLiteDatabase.execSQL(dropTable3);
        sqLiteDatabase.execSQL(dropTable4);
        sqLiteDatabase.execSQL(dropTable5);

        return accuracy;
    }
}