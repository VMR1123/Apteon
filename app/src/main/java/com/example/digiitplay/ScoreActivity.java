package com.example.digiitplay;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ScoreActivity extends AppCompatActivity {

    @Override
    public void onBackPressed() {
        Intent setIntent = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(setIntent);
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score);

        Intent i = getIntent();

        TextView viewTotal = findViewById(R.id.textView6);
        TextView viewCorrect = findViewById(R.id.textView8);
        TextView viewIncorrect = findViewById(R.id.textView10);
        TextView viewAccuracy = findViewById(R.id.textView13);
        TextView viewScore = findViewById(R.id.score);

        String correctCount = i.getStringExtra("correct");
        String incorrectCount = i.getStringExtra("incorrect");
        String mode = i.getStringExtra("mode");

        int correctValue = Integer.parseInt(correctCount);
        int incorrectValue = Integer.parseInt(incorrectCount);
        int modeValue = Integer.parseInt(mode);
        int scoreValue;

        if (modeValue == 1)
            scoreValue = correctValue + (-1 * incorrectValue);
        else if (modeValue == 2)
            scoreValue = (2 * correctValue) + (-1 * incorrectValue);
        else if (modeValue == 3)
            scoreValue = (4 * correctValue) + (-2 * incorrectValue);
        else
            scoreValue = (6 * correctValue) + (-3 * incorrectValue);

        double accuracyValue = ((double) correctValue * 100) / (double) (correctValue + incorrectValue);

        viewScore.setText(scoreValue + " Pts");
        viewTotal.setText(String.valueOf(correctValue + incorrectValue));
        viewCorrect.setText(correctCount);
        viewIncorrect.setText(incorrectCount);
        viewAccuracy.setText(String.format("%.2f", accuracyValue) + "%");
    }
}