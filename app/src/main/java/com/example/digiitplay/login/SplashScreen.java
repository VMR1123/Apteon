package com.example.digiitplay.login;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.example.digiitplay.Dashboard.DashboardActivity;
import com.example.digiitplay.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

@SuppressLint("CustomSplashScreen")
public class SplashScreen extends AppCompatActivity {

    FirebaseAuth mAuth;
    FirebaseUser user;
    DatabaseReference dbref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);

        mAuth=FirebaseAuth.getInstance();
        dbref= FirebaseDatabase.getInstance().getReference("Login");
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {


                //To check if user has already logged in previously
                user= FirebaseAuth.getInstance().getCurrentUser();
                if(user!=null){
                    if(user.isEmailVerified()){

                        finish();
                        startActivity(new Intent(SplashScreen.this, DashboardActivity.class));
                    }
                }
                else {
                    finish();
                    startActivity(new Intent(SplashScreen.this, login.class));
                }
            }
        },2000);

    }
}