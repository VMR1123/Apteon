package com.example.digiitplay.OperatorOverload;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

import com.example.digiitplay.DbHandler;
import com.example.digiitplay.EncryptDecrypt;
import com.example.digiitplay.Firebase.FirebaseInsert;
import com.example.digiitplay.Firebase.Score;
import com.example.digiitplay.R;
import com.example.digiitplay.login.NetworkState;

public class OperatorOverloadScoreActivity extends AppCompatActivity {

    DbHandler db;
    FirebaseInsert insert;
    String str_mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operator_overload_score);

        TextView viewTotal = findViewById(R.id.textView6);
        TextView viewCorrect = findViewById(R.id.textView8);
        TextView viewIncorrect = findViewById(R.id.textView10);
        TextView viewAccuracy = findViewById(R.id.textView13);
        TextView viewScore = findViewById(R.id.score);

        Intent i = getIntent();

        String mode = i.getStringExtra("mode");
        int modeValue = Integer.parseInt(mode);

        String correctCount = i.getStringExtra("correct");
        String incorrectCount = i.getStringExtra("incorrect");

        int correctValue = Integer.parseInt(correctCount);

        int incorrectValue = Integer.parseInt(incorrectCount);

        double accuracyValue = 0;

        int scoreValue;

        if (modeValue == 5){
            str_mode = "easy";
            scoreValue = correctValue + (-1 * incorrectValue);}
        else if (modeValue == 6){
            str_mode= "moderate";
            scoreValue = (2 * correctValue) + (-1 * incorrectValue);}
        else if (modeValue == 7){
            str_mode = "hard";
            scoreValue = (4 * correctValue) + (-2 * incorrectValue);}
        else{
            str_mode = "hard+";
            scoreValue = (6 * correctValue) + (-3 * incorrectValue);}

        viewScore.setText(scoreValue + " Pts");

        accuracyValue = ((double) correctValue * 100) / (double) (correctValue + incorrectValue);

        if(correctValue==0&&incorrectValue==0)
            accuracyValue=0;

        viewTotal.setText(String.valueOf(correctValue + incorrectValue));
        viewCorrect.setText(correctCount);
        viewIncorrect.setText(incorrectCount);
        viewAccuracy.setText(String.format("%.2f", accuracyValue) + "%");

        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String currentDate = date.format(new Date());

        EncryptDecrypt ec = new EncryptDecrypt();

        String scoreEncrypted = ec.encrypt(String.valueOf(scoreValue));
        String accuracyEncrypted = ec.encrypt(String.format("%.2f", accuracyValue));
        String dateEncrypted = ec.encrypt(currentDate);

        db = new DbHandler(this);

        db.insertDataEncrypted(modeValue, scoreEncrypted, accuracyEncrypted, dateEncrypted);

        Log.d("score", scoreValue +" "+ accuracyValue);
        insert=new FirebaseInsert();

        if(new NetworkState().networkstate(this)){
            insert.insert("Digitplay", new Score(scoreValue, accuracyValue, str_mode,getDate()), this);
        }
    }

    public String getDate(){
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String currentDate = date.format(new Date());
        System.out.println(currentDate);
        return currentDate;
    }
}