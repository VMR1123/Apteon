package com.example.digiitplay.OperatorOverload;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.digiitplay.R;

public class OperatorOverloadDashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_operator_overload_dashboard);

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
                Intent i = new Intent(getApplicationContext(), OperatorOverloadGameActivity.class);
                i.putExtra("mode", mode);
                startActivity(i);
            }
        });

        ll2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mode = 2;
                Intent i = new Intent(getApplicationContext(), OperatorOverloadGameActivity.class);
                i.putExtra("mode", mode);
                startActivity(i);
            }
        });

        ll3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mode = 3;
                Intent i = new Intent(getApplicationContext(), OperatorOverloadGameActivity.class);
                i.putExtra("mode", mode);
                startActivity(i);
            }
        });

        ll4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mode = 4;
                Intent i = new Intent(getApplicationContext(), OperatorOverloadGameActivity.class);
                i.putExtra("mode", mode);
                startActivity(i);
            }
        });

        ll5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mode = 5;
                Intent i = new Intent(getApplicationContext(), OperatorOverloadGameActivity.class);
                i.putExtra("mode", mode);
                startActivity(i);
            }
        });

        ll6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mode = 6;
                Intent i = new Intent(getApplicationContext(), OperatorOverloadGameActivity.class);
                i.putExtra("mode", mode);
                startActivity(i);
            }
        });

        ll7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mode = 7;
                Intent i = new Intent(getApplicationContext(), OperatorOverloadGameActivity.class);
                i.putExtra("mode", mode);
                startActivity(i);
            }
        });

        ll8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mode = 8;
                Intent i = new Intent(getApplicationContext(), OperatorOverloadGameActivity.class);
                i.putExtra("mode", mode);
                startActivity(i);
            }
        });

        ll9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mode = 9;
                Intent i = new Intent(getApplicationContext(), OperatorOverloadGameActivity.class);
                i.putExtra("mode", mode);
                startActivity(i);
            }
        });

        q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder
                        = new AlertDialog.Builder(OperatorOverloadDashboardActivity.this);

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
                imageView.setBackgroundResource(R.drawable.easy);
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
                        = new AlertDialog.Builder(OperatorOverloadDashboardActivity.this);

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
                imageView.setBackgroundResource(R.drawable.medium);
                desc.setText("Two Values Given, One To Fill");
                correct.setText("Correct : +2");
                incorrect.setText("Incorrect : -1");
                time.setText("Time : Unlimited(Until You Press Back Button)");

                AlertDialog dialog
                        = builder.create();
                dialog.show();
            }
        });

        q3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder
                        = new AlertDialog.Builder(OperatorOverloadDashboardActivity.this);

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

                title.setText("Hard");
                imageView.setBackgroundResource(R.drawable.hard);
                desc.setText("One Value Given, Two To Fill");
                correct.setText("Correct : +4");
                incorrect.setText("Incorrect : -2");
                time.setText("Time : Unlimited(Until You Press Back Button)");

                AlertDialog dialog
                        = builder.create();
                dialog.show();
            }
        });

        q4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder
                        = new AlertDialog.Builder(OperatorOverloadDashboardActivity.this);

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

                title.setText("Hard+");
                imageView.setBackgroundResource(R.drawable.hard_plus);
                desc.setText("No Values Given, All To Fill");
                correct.setText("Correct : +6");
                incorrect.setText("Incorrect : -3");
                time.setText("Time : Unlimited(Until You Press Back Button)");

                AlertDialog dialog
                        = builder.create();
                dialog.show();
            }
        });

        q5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder
                        = new AlertDialog.Builder(OperatorOverloadDashboardActivity.this);

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
                imageView.setBackgroundResource(R.drawable.easy);
                desc.setText("Two Values Given, One To Fill");
                correct.setText("Correct : +1");
                incorrect.setText("Incorrect : -1");
                time.setText("Time : 45 Sec");

                AlertDialog dialog
                        = builder.create();
                dialog.show();
            }
        });

        q6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder
                        = new AlertDialog.Builder(OperatorOverloadDashboardActivity.this);

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
                imageView.setBackgroundResource(R.drawable.medium);
                desc.setText("Two Values Given, One To Fill");
                correct.setText("Correct : +2");
                incorrect.setText("Incorrect : -1");
                time.setText("Time : 45 Sec");

                AlertDialog dialog
                        = builder.create();
                dialog.show();
            }
        });

        q7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder
                        = new AlertDialog.Builder(OperatorOverloadDashboardActivity.this);

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

                title.setText("Hard");
                imageView.setBackgroundResource(R.drawable.hard);
                desc.setText("One Value Given, Two To Fill");
                correct.setText("Correct : +4");
                incorrect.setText("Incorrect : -2");
                time.setText("Time : 45 Sec");

                AlertDialog dialog
                        = builder.create();
                dialog.show();
            }
        });

        q8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder
                        = new AlertDialog.Builder(OperatorOverloadDashboardActivity.this);

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

                title.setText("Hard+");
                imageView.setBackgroundResource(R.drawable.hard_plus);
                desc.setText("No Values Given, All To Fill");
                correct.setText("Correct : +6");
                incorrect.setText("Incorrect : -3");
                time.setText("Time : 45 Sec");

                AlertDialog dialog
                        = builder.create();
                dialog.show();
            }
        });

        q9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder
                        = new AlertDialog.Builder(OperatorOverloadDashboardActivity.this);

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

                title.setText("All");
                imageView.setBackgroundResource(R.drawable.easy);
                desc.setText("All Game Difficulties in Succession - 30 Sec. Each");
                correct.setText("Correct : As Per Each Round");
                incorrect.setText("Incorrect : As Per Each Round");
                time.setText("Time : 120 Sec. | 2 Mins.");

                AlertDialog dialog
                        = builder.create();
                dialog.show();
            }
        });
    }
}