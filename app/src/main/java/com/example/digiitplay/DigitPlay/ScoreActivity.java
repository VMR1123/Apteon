package com.example.digiitplay.DigitPlay;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import androidx.appcompat.app.AppCompatActivity;

import com.example.digiitplay.DbHandler;
import com.example.digiitplay.EncryptDecrypt;
import com.example.digiitplay.R;

public class ScoreActivity extends AppCompatActivity {

    DbHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

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

        if (modeValue == 5)
            scoreValue = correctValue + (-1 * incorrectValue);
        else if (modeValue == 6)
            scoreValue = (2 * correctValue) + (-1 * incorrectValue);
        else if (modeValue == 7)
            scoreValue = (4 * correctValue) + (-2 * incorrectValue);
        else
            scoreValue = (6 * correctValue) + (-3 * incorrectValue);

        viewScore.setText(scoreValue + " Pts");

        double accuracyValue = ((double) correctValue * 100) / (double) (correctValue + incorrectValue);
        viewTotal.setText(String.valueOf(correctValue + incorrectValue));
        viewCorrect.setText(correctCount);
        viewIncorrect.setText(incorrectCount);
        viewAccuracy.setText(String.format("%.2f", accuracyValue) + "%");

        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String currentDate = date.format(new Date());

        EncryptDecrypt ec = new EncryptDecrypt();

        String scoreEncrypted = ec.encrypt(String.valueOf(scoreValue));
        String accuracyEncrypted = ec.encrypt(String.valueOf(accuracyValue));
        String dateEncrypted = ec.encrypt(currentDate);

        String dateDecrypted = ec.decrypt(dateEncrypted);

        db = new DbHandler(this);

        db.insertDataEncrypted(scoreEncrypted, accuracyEncrypted, dateEncrypted);

        db.insertData(modeValue, scoreValue, accuracyValue, currentDate);
    }
}