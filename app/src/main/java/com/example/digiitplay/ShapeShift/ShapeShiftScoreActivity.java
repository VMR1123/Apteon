package com.example.digiitplay.ShapeShift;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.digiitplay.DbHandler;
import com.example.digiitplay.EncryptDecrypt;
import com.example.digiitplay.Firebase.FirebaseInsert;
import com.example.digiitplay.Firebase.Score;
import com.example.digiitplay.R;
import com.example.digiitplay.login.NetworkState;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ShapeShiftScoreActivity extends AppCompatActivity {

    DbHandler db;
    FirebaseInsert insert;
    String str_mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_shift_score);

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

        int scoreValue;

        if (modeValue == 3) {
            str_mode = "normal";
            scoreValue = (3 * correctValue) + (-1 * incorrectValue);
        } else {
            str_mode = "challenge";
            scoreValue = (6 * correctValue) + (-2 * incorrectValue);
        }


        viewScore.setText(scoreValue + " Pts");

        double accuracyValue = ((double) correctValue * 100) / (double) (correctValue + incorrectValue);

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

        db.insertEncryptedShapeSwitchScore(modeValue, scoreEncrypted, accuracyEncrypted, dateEncrypted);
        insert = new FirebaseInsert();

        if (new NetworkState().networkstate(this)) {
            insert.insert("ShapeShift", new Score(scoreValue, accuracyValue, str_mode, getDate()), this);
        }
    }

    public String getDate() {
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String currentDate = date.format(new Date());
        System.out.println(currentDate);
        return currentDate;
    }
}