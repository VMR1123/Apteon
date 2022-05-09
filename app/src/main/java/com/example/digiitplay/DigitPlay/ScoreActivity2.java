package com.example.digiitplay.DigitPlay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.digiitplay.DbHandler;
import com.example.digiitplay.EncryptDecrypt;
import com.example.digiitplay.R;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ScoreActivity2 extends AppCompatActivity {

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


        String correctCountEasy = i.getStringExtra("correctEasy");
        String correctCountMed = i.getStringExtra("correctMed");
        String correctCountHard = i.getStringExtra("correctHard");
        String correctCountHardPlus = i.getStringExtra("correctHardPlus");

        String incorrectCountEasy = i.getStringExtra("incorrectEasy");
        String incorrectCountMed = i.getStringExtra("incorrectMed");
        String incorrectCountHard = i.getStringExtra("incorrectHard");
        String incorrectCountHardPlus = i.getStringExtra("incorrectHardPlus");


        int correctValueEasy = Integer.parseInt(correctCountEasy);
        int correctValueMed = Integer.parseInt(correctCountMed);
        int correctValueHard = Integer.parseInt(correctCountHard);
        int correctValueHardPlus = Integer.parseInt(correctCountHardPlus);

        int incorrectValueEasy = Integer.parseInt(incorrectCountEasy);
        int incorrectValueMed = Integer.parseInt(incorrectCountMed);
        int incorrectValueHard = Integer.parseInt(incorrectCountHard);
        int incorrectValueHardPlus = Integer.parseInt(incorrectCountHardPlus);


        int scoreValue;

        scoreValue = (correctValueEasy + (-1 * incorrectValueEasy)) + ((2 * correctValueMed) + (-1 * incorrectValueMed)) + ((4 * correctValueHard) + (-2 * incorrectValueHard)) + ((6 * correctValueHardPlus) + (-3 * incorrectValueHardPlus));


        viewScore.setText(scoreValue + " Pts");

        final int i1 = correctValueEasy + correctValueMed + correctValueHard + correctValueHardPlus ;
        final int i2 = incorrectValueEasy + incorrectValueMed + incorrectValueHard + incorrectValueHardPlus;

        double accuracyValue = (((double) correctValueEasy + (double) correctValueMed + (double) correctValueHard + (double) correctValueHardPlus) * 100) / (double) (i1 + i2);
        viewTotal.setText(String.valueOf(i1 + i2));
        viewCorrect.setText(String.valueOf(i1));
        viewIncorrect.setText(String.valueOf(i2));
        viewAccuracy.setText(String.format("%.2f", accuracyValue) + "%");

        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String currentDate = date.format(new Date());

        EncryptDecrypt ec = new EncryptDecrypt();

        String scoreEncrypted = ec.encrypt(String.valueOf(scoreValue));
        String accuracyEncrypted = ec.encrypt(String.format("%.2f", accuracyValue));
        String dateEncrypted = ec.encrypt(currentDate);

        db = new DbHandler(this);

        db.insertDataEncrypted(modeValue, scoreEncrypted, accuracyEncrypted, dateEncrypted);
    }
}