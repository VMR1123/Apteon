package com.example.digiitplay;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

public class DbHandler extends SQLiteOpenHelper {

    public static final String[] modes1, modes2, modes3;

    String attributes1 = "id INTEGER,score INTEGER, accuracy REAL, date TEXT";
    String attributes2 = "id INTEGER PRIMARY KEY, score TEXT, accuracy TEXT, date TEXT";
    String attributes3 = "id INTEGER, score INTEGER, accuracy REAL, date TEXT";
    String attributes4 = "id INTEGER PRIMARY KEY, score TEXT, accuracy TEXT, date TEXT";

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

    String createNormalShapeShiftTable = "CREATE TABLE normal(" + attributes3 + ")";
    String createChallengeShapeShiftTable = "CREATE TABLE challenge(" + attributes3 + ")";

    String createNormalEncryptedShapeShiftTable = "CREATE TABLE normal_encrypted(" + attributes4 + ")";
    String createChallengeEncryptedShapeShiftTable = "CREATE TABLE challenge_encrypted(" + attributes4 + ")";

    public String dropTable1 = "DROP TABLE if EXISTS easy_comp";
    public String dropTable2 = "DROP TABLE if EXISTS moderate_comp";
    public String dropTable3 = "DROP TABLE if EXISTS hard_comp";
    public String dropTable4 = "DROP TABLE if EXISTS hardplus_comp";
    public String dropTable5 = "DROP TABLE if EXISTS allmodes_comp";

    public String dropNormal = "DROP TABLE if EXISTS normal";
    public String dropChallenging = "DROP TABLE if EXISTS challenge";

    public String truncate_easy_encrypted = "DELETE FROM easy_encrypted_comp";
    public String truncate_med_encrypted = "DELETE FROM med_encrypted_comp";
    public String truncate_hard_encrypted = "DELETE FROM hard_encrypted_comp";
    public String truncate_hardplus_encrypted = "DELETE FROM hardplus_encrypted_comp";
    public String truncate_all_encrypted = "DELETE FROM all_encrypted_comp";

    public String truncate_normal_encrypted="DELETE FROM normal_encrypted";
    public String truncate_challenge_encrypted="DELETE FROM challenge_encrypted";

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

        sqLiteDatabase.execSQL(createNormalEncryptedShapeShiftTable);
        sqLiteDatabase.execSQL(createChallengeEncryptedShapeShiftTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String dropTable = "DROP TABLE if EXISTS " + "easy_comp";
        sqLiteDatabase.execSQL(dropTable);
    }

    public void insertDataEncrypted(int mode, String score, String accuracy, String date) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("score", score);
        contentValues.put("date", date);
        contentValues.put("accuracy", accuracy);

        sqLiteDatabase.insert(modes2[mode - 5], null, contentValues);
    }

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

    public void insertEncryptedShapeSwitchScore(int mode, String score, String accuracy, String date) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("score", score);
        contentValues.put("date", date);
        contentValues.put("accuracy", accuracy);

        if (mode == 3)
            sqLiteDatabase.insert("normal_encrypted", null, contentValues);
        else
            sqLiteDatabase.insert("challenge_encrypted", null, contentValues);

    }

    public ArrayList<Score> getTop5EncryptedShapeShiftScores(int mode) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        String select, db1, db2;

        sqLiteDatabase.execSQL(createNormalShapeShiftTable);
        sqLiteDatabase.execSQL(createChallengeShapeShiftTable);

        EncryptDecrypt aes = new EncryptDecrypt();

        ArrayList<Score> scoreArrayList = new ArrayList<>();
        if (mode == 3) {
            db1 = "normal_encrypted";
            db2 = "normal";
        } else {
            db1 = "challenge_encrypted";
            db2 = "challenge";
        }

        select = "SELECT * FROM " + db1 + ";";

        Cursor cursor = sqLiteDatabase.rawQuery(select, null);

        if (cursor.moveToFirst()) {
            do {
                ContentValues contentValues = new ContentValues();

                contentValues.put("id", cursor.getInt(0));
                contentValues.put("score", Integer.parseInt(aes.decrypt(cursor.getString(1))));
                contentValues.put("accuracy", Double.parseDouble(aes.decrypt(cursor.getString(2))));
                contentValues.put("date", aes.decrypt(cursor.getString(3)));

                sqLiteDatabase.insert(db2, null, contentValues);
            } while (cursor.moveToNext());
        }
        cursor.close();

        String select2 = "SELECT * from " + db2 + " order by score desc limit 5 offset 0;";
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

        sqLiteDatabase.execSQL(dropNormal);
        sqLiteDatabase.execSQL(dropChallenging);

        return scoreArrayList;
    }

    public ArrayList<Score> getLast5ShapeShiftEncryptedScores(int mode) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String select, db1, db2;

        sqLiteDatabase.execSQL(createNormalShapeShiftTable);
        sqLiteDatabase.execSQL(createChallengeShapeShiftTable);

        EncryptDecrypt aes = new EncryptDecrypt();

        ArrayList<Score> scoreArrayList = new ArrayList<>();
        if (mode == 3) {
            db1 = "normal_encrypted";
            db2 = "normal";
        } else {
            db1 = "challenge_encrypted";
            db2 = "challenge";
        }
        select = "SELECT * from " + db1 + ";";
        Cursor cursor = sqLiteDatabase.rawQuery(select, null);

        if (cursor.moveToFirst()) {
            do {
                ContentValues contentValues = new ContentValues();

                contentValues.put("id", cursor.getInt(0));
                contentValues.put("score", Integer.parseInt(aes.decrypt(cursor.getString(1))));
                contentValues.put("accuracy", Double.parseDouble(aes.decrypt(cursor.getString(2))));
                contentValues.put("date", aes.decrypt(cursor.getString(3)));

                sqLiteDatabase.insert(db2, null, contentValues);
            } while (cursor.moveToNext());
        }
        cursor.close();

        String select2 = "SELECT * from " + db2 + " ORDER BY id DESC LIMIT 5 OFFSET 0;";
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

        sqLiteDatabase.execSQL(dropNormal);
        sqLiteDatabase.execSQL(dropChallenging);

        return scoreArrayList;
    }

    public String getShapeShiftRoundCount(int mode) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String db1;

        if (mode == 3)
            db1 = "normal_encrypted";
        else
            db1 = "challenge_encrypted";

        String select2 = "SELECT COUNT(id) from " + db1 + ";";
        Cursor cursor2 = sqLiteDatabase.rawQuery(select2, null);

        cursor2.moveToFirst();
        String count = cursor2.getString(0);
        cursor2.close();

        return count;
    }

    public String getTotalShapeShiftEncryptedScores(int mode) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String select, db1, db2;

        sqLiteDatabase.execSQL(createNormalShapeShiftTable);
        sqLiteDatabase.execSQL(createChallengeShapeShiftTable);

        EncryptDecrypt aes = new EncryptDecrypt();

        ArrayList<Score> scoreArrayList = new ArrayList<>();
        if (mode == 3) {
            db1 = "normal_encrypted";
            db2 = "normal";
        } else {
            db1 = "challenge_encrypted";
            db2 = "challenge";
        }
        select = "SELECT * from " + db1 + ";";
        Cursor cursor = sqLiteDatabase.rawQuery(select, null);

        if (cursor.moveToFirst()) {
            do {
                ContentValues contentValues = new ContentValues();

                contentValues.put("id", cursor.getInt(0));
                contentValues.put("score", Integer.parseInt(aes.decrypt(cursor.getString(1))));
                contentValues.put("accuracy", Double.parseDouble(aes.decrypt(cursor.getString(2))));
                contentValues.put("date", aes.decrypt(cursor.getString(3)));

                sqLiteDatabase.insert(db2, null, contentValues);
            } while (cursor.moveToNext());
        }
        cursor.close();

        String select2 = "SELECT SUM(score) from " + db2 + ";";
        Cursor cursor2 = sqLiteDatabase.rawQuery(select2, null);

        cursor2.moveToFirst();
        String sum = cursor2.getString(0);
        cursor2.close();

        sqLiteDatabase.execSQL(dropNormal);
        sqLiteDatabase.execSQL(dropChallenging);

        return sum;
    }

    public String getShapeShiftAverageAccuracy(int mode) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        String select, db1, db2;

        sqLiteDatabase.execSQL(createNormalShapeShiftTable);
        sqLiteDatabase.execSQL(createChallengeShapeShiftTable);

        EncryptDecrypt aes = new EncryptDecrypt();

        ArrayList<Score> scoreArrayList = new ArrayList<>();
        if (mode == 3) {
            db1 = "normal_encrypted";
            db2 = "normal";
        } else {
            db1 = "challenge_encrypted";
            db2 = "challenge";
        }
        select = "SELECT * from " + db1 + ";";
        Cursor cursor = sqLiteDatabase.rawQuery(select, null);

        if (cursor.moveToFirst()) {
            do {
                ContentValues contentValues = new ContentValues();

                contentValues.put("id", cursor.getInt(0));
                contentValues.put("score", Integer.parseInt(aes.decrypt(cursor.getString(1))));
                contentValues.put("accuracy", Double.parseDouble(aes.decrypt(cursor.getString(2))));
                contentValues.put("date", aes.decrypt(cursor.getString(3)));

                sqLiteDatabase.insert(db2, null, contentValues);
            } while (cursor.moveToNext());
        }
        cursor.close();

        String select2 = "SELECT AVG(accuracy) from " + db2 + ";";
        Cursor cursor2 = sqLiteDatabase.rawQuery(select2, null);

        cursor2.moveToFirst();
        String sum = cursor2.getString(0);
        cursor2.close();

        sqLiteDatabase.execSQL(dropNormal);
        sqLiteDatabase.execSQL(dropChallenging);

        return sum;
    }

    public void drop(){
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();

        sqLiteDatabase.execSQL(truncate_easy_encrypted);
        sqLiteDatabase.execSQL(truncate_med_encrypted);
        sqLiteDatabase.execSQL(truncate_hard_encrypted);
        sqLiteDatabase.execSQL(truncate_hardplus_encrypted);
        sqLiteDatabase.execSQL(truncate_all_encrypted);

        sqLiteDatabase.execSQL(truncate_normal_encrypted);
        sqLiteDatabase.execSQL(truncate_challenge_encrypted);

    }
}