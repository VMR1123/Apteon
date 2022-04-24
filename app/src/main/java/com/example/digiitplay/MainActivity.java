package com.example.digiitplay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.digiitplay.ScoreHistory.ScoreHistoryActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button easy = findViewById(R.id.easy);
        Button medium = findViewById(R.id.med);
        Button hard = findViewById(R.id.hard);
        Button hardPlus = findViewById(R.id.hardplus);

        Button easyComp = findViewById(R.id.easyComp);
        Button mediumComp = findViewById(R.id.medComp);
        Button hardComp = findViewById(R.id.hardComp);
        Button hardPlusComp = findViewById(R.id.hardplusComp);
        Button alltogether = findViewById(R.id.alltogether);

        Button scoreHistory = findViewById(R.id.score);

        easy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mode = 1;
                Intent i = new Intent(getApplicationContext(), GameActivity.class);
                i.putExtra("mode", mode);
                startActivity(i);
            }
        });

        medium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mode = 2;
                Intent i = new Intent(getApplicationContext(), GameActivity.class);
                i.putExtra("mode", mode);
                startActivity(i);
            }
        });

        hard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mode = 3;
                Intent i = new Intent(getApplicationContext(), GameActivity.class);
                i.putExtra("mode", mode);
                startActivity(i);
            }
        });

        hardPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mode = 4;
                Intent i = new Intent(getApplicationContext(), GameActivity.class);
                i.putExtra("mode", mode);
                startActivity(i);
            }
        });

        easyComp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mode = 5;
                Intent i = new Intent(getApplicationContext(), GameActivity.class);
                i.putExtra("mode", mode);
                startActivity(i);
            }
        });

        mediumComp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mode = 6;
                Intent i = new Intent(getApplicationContext(), GameActivity.class);
                i.putExtra("mode", mode);
                startActivity(i);
            }
        });

        hardComp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mode = 7;
                Intent i = new Intent(getApplicationContext(), GameActivity.class);
                i.putExtra("mode", mode);
                startActivity(i);
            }
        });

        hardPlusComp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mode = 8;
                Intent i = new Intent(getApplicationContext(), GameActivity.class);
                i.putExtra("mode", mode);
                startActivity(i);
            }
        });

        alltogether.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mode = 9;
                Intent i = new Intent(getApplicationContext(), GameActivity.class);
                i.putExtra("mode", mode);
                startActivity(i);
            }
        });

        scoreHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), ScoreHistoryActivity.class);
                startActivity(i);
            }
        });
    }
}