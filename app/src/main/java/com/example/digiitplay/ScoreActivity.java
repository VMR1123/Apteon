package com.example.digiitplay;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
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

        String correctCount = i.getStringExtra("correct");
        String incorrectCount = i.getStringExtra("incorrect");

        int correctValue = Integer.valueOf(correctCount);
        int incorrectValue = Integer.valueOf(incorrectCount);

        double accuracyValue = (Double.valueOf(correctValue) * 100) / Double.valueOf(correctValue + incorrectValue);

        viewTotal.setText(String.valueOf(correctValue + incorrectValue));
        viewCorrect.setText(correctCount);
        viewIncorrect.setText(incorrectCount);
        viewAccuracy.setText(String.format("%.2f", accuracyValue)+"%");
    }
}