package com.example.digiitplay.DigitPlay;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.digiitplay.R;
import com.example.digiitplay.ScoreHistory.ScoreHistoryActivity;

public class DigitPlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_digit_play);

//        Button easy = findViewById(R.id.easy);
//        Button medium = findViewById(R.id.med);
//        Button hard = findViewById(R.id.hard);
//        Button hardPlus = findViewById(R.id.hardplus);
//
//        Button easyComp = findViewById(R.id.easyComp);
//        Button mediumComp = findViewById(R.id.medComp);
//        Button hardComp = findViewById(R.id.hardComp);
//        Button hardPlusComp = findViewById(R.id.hardplusComp);
//        Button alltogether = findViewById(R.id.alltogether);

        Button q1 = findViewById(R.id.q1);
        Button q2 = findViewById(R.id.q2);
        Button q3 = findViewById(R.id.q3);
        Button q4 = findViewById(R.id.q4);
        Button q5 = findViewById(R.id.q5);
        Button q6 = findViewById(R.id.q6);
        Button q7 = findViewById(R.id.q7);
        Button q8 = findViewById(R.id.q8);
        Button q9 = findViewById(R.id.q9);


        LinearLayout ll1 = findViewById(R.id.ll1);
        LinearLayout ll2 = findViewById(R.id.ll2);
        LinearLayout ll3 = findViewById(R.id.ll3);
        LinearLayout ll4 = findViewById(R.id.ll4);
        LinearLayout ll5 = findViewById(R.id.ll5);
        LinearLayout ll6 = findViewById(R.id.ll6);
        LinearLayout ll7 = findViewById(R.id.ll7);
        LinearLayout ll8 = findViewById(R.id.ll8);
        LinearLayout ll9 = findViewById(R.id.ll9);

        ll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mode = 1;
                Intent i = new Intent(getApplicationContext(), DigitPlayGameActivity.class);
                i.putExtra("mode", mode);
                startActivity(i);
            }
        });

        ll2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mode = 2;
                Intent i = new Intent(getApplicationContext(), DigitPlayGameActivity.class);
                i.putExtra("mode", mode);
                startActivity(i);
            }
        });

        ll3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mode = 3;
                Intent i = new Intent(getApplicationContext(), DigitPlayGameActivity.class);
                i.putExtra("mode", mode);
                startActivity(i);
            }
        });

        ll4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mode = 4;
                Intent i = new Intent(getApplicationContext(), DigitPlayGameActivity.class);
                i.putExtra("mode", mode);
                startActivity(i);
            }
        });

        ll5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mode = 5;
                Intent i = new Intent(getApplicationContext(), DigitPlayGameActivity.class);
                i.putExtra("mode", mode);
                startActivity(i);
            }
        });

        ll6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mode = 6;
                Intent i = new Intent(getApplicationContext(), DigitPlayGameActivity.class);
                i.putExtra("mode", mode);
                startActivity(i);
            }
        });

        ll7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mode = 7;
                Intent i = new Intent(getApplicationContext(), DigitPlayGameActivity.class);
                i.putExtra("mode", mode);
                startActivity(i);
            }
        });

        ll8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mode = 8;
                Intent i = new Intent(getApplicationContext(), DigitPlayGameActivity.class);
                i.putExtra("mode", mode);
                startActivity(i);
            }
        });

        ll9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mode = 9;
                Intent i = new Intent(getApplicationContext(), DigitPlayGameActivity.class);
                i.putExtra("mode", mode);
                startActivity(i);
            }
        });

        q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder
                        = new AlertDialog.Builder(DigitPlayActivity.this);

                final View customLayout
                        = getLayoutInflater()
                        .inflate(
                                R.layout.custom_alert,
                                null);
                builder.setView(customLayout);

                TextView title = customLayout.findViewById(R.id.title);
                ImageView imageView = customLayout.findViewById(R.id.image);
                TextView desc = customLayout.findViewById(R.id.desc);
                TextView correct = customLayout.findViewById(R.id.correct);
                TextView incorrect = customLayout.findViewById(R.id.incorrect);
                TextView time = customLayout.findViewById(R.id.time);

                title.setText("Easy");
                imageView.setBackgroundResource(R.drawable.easy_example);
                desc.setText("Two Values Given, One To Fill");
                correct.setText("Correct : +1");
                incorrect.setText("Incorrect : -1");
                time.setText("Time : Unlimited(Until You Press Back Button)");

                AlertDialog dialog
                        = builder.create();
                dialog.show();
            }
        });

        q2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder
                        = new AlertDialog.Builder(DigitPlayActivity.this);

                final View customLayout
                        = getLayoutInflater()
                        .inflate(
                                R.layout.custom_alert,
                                null);
                builder.setView(customLayout);

                TextView title = customLayout.findViewById(R.id.title);
                ImageView imageView = customLayout.findViewById(R.id.image);
                TextView desc = customLayout.findViewById(R.id.desc);
                TextView correct = customLayout.findViewById(R.id.correct);
                TextView incorrect = customLayout.findViewById(R.id.incorrect);
                TextView time = customLayout.findViewById(R.id.time);

                title.setText("Moderate");
                imageView.setBackgroundResource(R.drawable.moderate_example);
                desc.setText("Two Values Given, One To Fill");
                correct.setText("Correct : +2");
                incorrect.setText("Incorrect : -1");
                time.setText("Time : Unlimited(Until You Press Back Button)");

                AlertDialog dialog
                        = builder.create();
                dialog.show();
            }
        });
    }
}