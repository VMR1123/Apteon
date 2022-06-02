package com.example.digiitplay.login;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.example.digiitplay.DbHandler;
import com.example.digiitplay.EncryptDecrypt;
import com.example.digiitplay.Firebase.Score;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class FetchData {

    String uid;
    DatabaseReference ScoreRef;

    ArrayList<Score> score_data;
    DbHandler db;

    public FetchData(Context context) {
        ScoreRef = FirebaseDatabase.getInstance().getReference("Score");
        uid = FirebaseAuth.getInstance().getUid();
        score_data = new ArrayList<>();
        db = new DbHandler(context);

    }


    public void getScore(String game_name, String game_type) {
        ScoreRef.child(uid).child(game_name).child(game_type).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                score_data.clear();
                for (DataSnapshot all : snapshot.getChildren()) {
                    Score data = all.getValue(Score.class);
                    score_data.add(data);
                }

                Log.d("fetchdata", "onDataChange: " + score_data.size());
                insertIntoSql(score_data);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void insertIntoSql(ArrayList<Score> score_data) {

        Log.d("fetchdata", "insertIntoSql: in if 1");

        Log.d("fetchdata", "insertIntoSql: " + score_data.size());
        for (int i = 0; i < score_data.size(); i++) {

            System.out.println("in insert data");
            Log.d("fetchdata", "insertIntoSql: in if");

            Score score = score_data.get(i);
            EncryptDecrypt ec = new EncryptDecrypt();

            String scoreEncrypted = ec.encrypt(String.valueOf(score.getScore()));
            String accuracyEncrypted = ec.encrypt(String.format("%.2f", score.getAccuracy()));
            String dateEncrypted = ec.encrypt(score.getDate());

            int modeValue = calModeValue(score.getGame_type());

            if(modeValue ==3 || modeValue ==4){
                db.insertEncryptedShapeSwitchScore(modeValue,scoreEncrypted,accuracyEncrypted,dateEncrypted);
            }
            else if (modeValue > 4) {
                System.out.println("in insert db");
                Log.d("fetchdata", "insertIntoSql: in dbinsert");
                db.insertDataEncrypted(modeValue, scoreEncrypted, accuracyEncrypted, dateEncrypted);
            }
        }
    }

    public int calModeValue(String mode) {
        switch (mode) {
            case "normal":
                return 3;
            case "challenge":
                return 4;
            case "easy":
                return 5;
            case "moderate":
                return 6;
            case "hard":
                return 7;
            case "hard+":
                return 8;
            case "all":
                return 9;
        }
        return 0;
    }


}
