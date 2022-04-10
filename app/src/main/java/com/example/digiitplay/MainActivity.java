package com.example.digiitplay;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button easy = findViewById(R.id.easy);
        Button medium = findViewById(R.id.med);
        Button hard = findViewById(R.id.hard);
        Button hardplus = findViewById(R.id.hardplus);

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

        hardplus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int mode = 4;
                Intent i = new Intent(getApplicationContext(), GameActivity.class);
                i.putExtra("mode", mode);
                startActivity(i);
            }
        });
    }
}