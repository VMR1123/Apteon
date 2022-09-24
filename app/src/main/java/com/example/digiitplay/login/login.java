package com.example.digiitplay.login;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.digiitplay.Dashboard.DashboardActivity;
import com.example.digiitplay.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.regex.Pattern;

public class login extends AppCompatActivity {

    Button btn_login,btn_sign_up,btn_forgot_password;
    EditText et_email,et_password;
    //TextInputLayout etl_email,etl_password;

    String str_email,str_password;

    FirebaseAuth mAuth;
    FirebaseUser user;

    String password_pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth=FirebaseAuth.getInstance();

        //To check if user has already logged in previously
        /*user= FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            if(user.isEmailVerified()){
                finish();
                navigate_to_dashboard();
            }

        }*/

        //To check if connected device has internet connectivity
        if(!networkstate()){
            Toast.makeText(this, "No internet", Toast.LENGTH_LONG).show();
            //Intent no_connection = new Intent(login.this,no_internet.class);
            //startActivity(no_connection);
        }


        //initialization of variables
        btn_login=findViewById(R.id.btn_login);
        btn_sign_up=findViewById(R.id.btn_sign_up);
        btn_forgot_password=findViewById(R.id.btn_forgot_password);

        et_email=findViewById(R.id.et_email);
        et_password=findViewById(R.id.et_password);

       // etl_email=findViewById(R.id.etl_email);
        //etl_password=findViewById(R.id.etl_password);
        //

        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                navigate_to_sign_up();
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //etl_email.setErrorEnabled(false);
                //l_password.setErrorEnabled(false);
                login_check();
            }
        });


        btn_forgot_password.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                forgot_password();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        /*
        user= FirebaseAuth.getInstance().getCurrentUser();
        if(user!=null){
            if(user.isEmailVerified()){
                finish();
                navigate_to_main_screen();
            }
            else {

                Toast.makeText(getApplicationContext(),"please verify your email using the link sent to your email id",Toast.LENGTH_LONG).show();
            }

        }*/


    }

    @Override
    protected void onResume() {
        super.onResume();

    }

    private boolean networkstate(){

        ConnectivityManager cm= (ConnectivityManager) getSystemService(login.CONNECTIVITY_SERVICE);
        boolean isconnected = cm.getActiveNetworkInfo()!=null && cm.getActiveNetworkInfo().isConnected();
        return isconnected;
    }




    void login_check(){
        str_email=String.valueOf(et_email.getText());
        str_password= String.valueOf(et_password.getText());

        if(str_email.isEmpty()){
            et_email.setError("Email can't be empty!");
            et_email.requestFocus();
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(str_email).matches()){
            et_email.setError("Invalid email!");
            et_email.requestFocus();
            return;
        }

        /*
        if(!Pattern.matches(password_pattern,str_password)){
            et_password.setError("Invalid password!");
            et_password.requestFocus();
            return;
        }*/

        if(!networkstate()){
            Toast.makeText(this, "No internet", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.signInWithEmailAndPassword(str_email,str_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if(task.isSuccessful()){
                    FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                    if(user.isEmailVerified()){

                        FetchData fetchData = new FetchData(login.this);
                        fetchData.getScore("Digitplay","easy");
                        fetchData.getScore("Digitplay","moderate");
                        fetchData.getScore("Digitplay","hard");
                        fetchData.getScore("Digitplay","hard+");
                        fetchData.getScore("Digitplay","all");
                        //fetchData.insertIntoSql(sc);

                        fetchData.getScore("ShapeShift","normal");
                        fetchData.getScore("ShapeShift","challenge");

                        navigate_to_dashboard();
                    }

                    else{
                        user.sendEmailVerification();
                        Toast.makeText(getApplicationContext(),"please verify your email using the link sent to your email id",Toast.LENGTH_LONG).show();
                        //etl_email.setError("Email is not verified");
                        et_email.requestFocus();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(),"Invalid credentials!",Toast.LENGTH_LONG).show();

                }
            }
        });
    }

    void forgot_password(){
        String str_email=String.valueOf(et_email.getText());

        if(str_email.isEmpty()){
            et_email.setError("Email can't be empty!");
            return;
        }
        if(!Patterns.EMAIL_ADDRESS.matcher(str_email).matches()){
            et_email.setError("Invalid email!");
            return;
        }

        if(!networkstate()){
            Toast.makeText(this, "No internet", Toast.LENGTH_SHORT).show();
            return;
        }

        mAuth.sendPasswordResetEmail(str_email).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Check email for password reset link!",Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(getApplicationContext(),"Try Again! Something went wrong\nUser does not exists!",Toast.LENGTH_LONG).show();

                }
            }
        });



    }
    void navigate_to_dashboard(){

        finish();
        Intent i=new Intent(getApplicationContext(), DashboardActivity.class);
        startActivity(i);
    }
    void navigate_to_sign_up(){

        Intent i=new Intent(getApplicationContext(),SignUp.class);
        startActivity(i);
    }


}