package com.example.digiitplay.ShapeShift;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.digiitplay.OperatorOverload.OperatorOverloadDashboardActivity;
import com.example.digiitplay.R;
import com.example.digiitplay.ShapeShiftScoreHistory.ShapeShiftScoreHistoryActivity;

public class ShapeShiftDashboardActivity extends AppCompatActivity {

    LinearLayout ll1, ll2 , ll3, ll4;
    Button score, q1, q2, q3, q4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shape_shift_dashboard);

        ll1 = findViewById(R.id.ll1);
        ll2 = findViewById(R.id.ll2);
        ll3 = findViewById(R.id.ll5);
        ll4 = findViewById(R.id.ll6);

        q1 = findViewById(R.id.q1);
        q2 = findViewById(R.id.q2);
        q3 = findViewById(R.id.q3);
        q4 = findViewById(R.id.q4);


        ll1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ShapeShiftDashboardActivity.this, ShapeShiftGameActivity.class);
                i.putExtra("mode", 1);
                startActivity(i);
            }
        });

        ll2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ShapeShiftDashboardActivity.this, ShapeShiftGameActivity.class);
                i.putExtra("mode", 2);
                startActivity(i);
            }
        });

        ll3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ShapeShiftDashboardActivity.this, ShapeShiftGameActivity.class);
                i.putExtra("mode", 3);
                startActivity(i);
            }
        });

        ll4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ShapeShiftDashboardActivity.this, ShapeShiftGameActivity.class);
                i.putExtra("mode", 4);
                startActivity(i);
            }
        });

        q1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder
                        = new AlertDialog.Builder(ShapeShiftDashboardActivity.this);

                final View customLayout
                        = getLayoutInflater()
                        .inflate(
                                R.layout.custom_alert_2,
                                null);
                builder.setView(customLayout);

                TextView title = customLayout.findViewById(R.id.title);
                ImageView imageView = customLayout.findViewById(R.id.image);
                TextView desc = customLayout.findViewById(R.id.desc);
                TextView correct = customLayout.findViewById(R.id.correct);
                TextView incorrect = customLayout.findViewById(R.id.incorrect);
                TextView time = customLayout.findViewById(R.id.time);

                title.setText("Normal");
                imageView.setBackgroundResource(R.drawable.normal);
                desc.setText("Match Indexes of Above Figures with the Below Figures");
                correct.setText("Correct : +3");
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
                        = new AlertDialog.Builder(ShapeShiftDashboardActivity.this);

                final View customLayout
                        = getLayoutInflater()
                        .inflate(
                                R.layout.custom_alert_3,
                                null);
                builder.setView(customLayout);

                TextView title = customLayout.findViewById(R.id.title);
                ImageView imageView = customLayout.findViewById(R.id.image);
                TextView desc = customLayout.findViewById(R.id.desc);
                TextView correct = customLayout.findViewById(R.id.correct);
                TextView incorrect = customLayout.findViewById(R.id.incorrect);
                TextView time = customLayout.findViewById(R.id.time);

                title.setText("Challenging");
                imageView.setBackgroundResource(R.drawable.challenging);
                desc.setText("Match Indexes of Above Figures with the Below Figures");
                correct.setText("Correct : +6");
                incorrect.setText("Incorrect : -2");
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
                        = new AlertDialog.Builder(ShapeShiftDashboardActivity.this);

                final View customLayout
                        = getLayoutInflater()
                        .inflate(
                                R.layout.custom_alert_2,
                                null);
                builder.setView(customLayout);

                TextView title = customLayout.findViewById(R.id.title);
                ImageView imageView = customLayout.findViewById(R.id.image);
                TextView desc = customLayout.findViewById(R.id.desc);
                TextView correct = customLayout.findViewById(R.id.correct);
                TextView incorrect = customLayout.findViewById(R.id.incorrect);
                TextView time = customLayout.findViewById(R.id.time);

                title.setText("Normal");
                imageView.setBackgroundResource(R.drawable.normal);
                desc.setText("Match Indexes of Above Figures with the Below Figures");
                correct.setText("Correct : +3");
                incorrect.setText("Incorrect : -1");
                time.setText("Time : 60 Sec. | 1 Min.");
                AlertDialog dialog
                        = builder.create();
                dialog.show();
            }
        });

        q4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                AlertDialog.Builder builder
                        = new AlertDialog.Builder(ShapeShiftDashboardActivity.this);

                final View customLayout
                        = getLayoutInflater()
                        .inflate(
                                R.layout.custom_alert_3,
                                null);
                builder.setView(customLayout);

                TextView title = customLayout.findViewById(R.id.title);
                ImageView imageView = customLayout.findViewById(R.id.image);
                TextView desc = customLayout.findViewById(R.id.desc);
                TextView correct = customLayout.findViewById(R.id.correct);
                TextView incorrect = customLayout.findViewById(R.id.incorrect);
                TextView time = customLayout.findViewById(R.id.time);

                title.setText("Challenging");
                imageView.setBackgroundResource(R.drawable.challenging);
                desc.setText("Match Indexes of Above Figures with the Below Figures");
                correct.setText("Correct : +6");
                incorrect.setText("Incorrect : -2");
                time.setText("Time : 120 Sec. | 2 Min.");
                AlertDialog dialog
                        = builder.create();
                dialog.show();
            }
        });

    }
}