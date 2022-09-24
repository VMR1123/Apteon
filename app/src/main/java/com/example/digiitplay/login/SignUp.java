package com.example.digiitplay.login;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.digiitplay.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

import java.util.regex.Pattern;

public class SignUp extends AppCompatActivity {

    Button btn_sign_up;

    EditText et_email,et_password,et_confirm_password,et_fullname, et_age;

    FirebaseAuth mAuth;

    String str_email,str_password,str_confirm_password,str_fullname, str_age;

    //String password_pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
    String fullname_pattern="[a-zA-Z][a-zA-Z ]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        btn_sign_up=findViewById(R.id.btn_sign_up);

        et_email=findViewById(R.id.et_email);
        et_password=findViewById(R.id.et_password);
        et_confirm_password=findViewById(R.id.et_confirm_password);
        et_fullname=findViewById(R.id.et_fullname);
        et_age=findViewById(R.id.et_age);


        mAuth=FirebaseAuth.getInstance();
        btn_sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sign_up();
            }
        });
    }

    public void sign_up(){

        str_email=String.valueOf(et_email.getText());
        str_password=String.valueOf(et_password.getText());
        str_confirm_password=String.valueOf(et_confirm_password.getText());
        str_fullname=String.valueOf(et_fullname.getText());
        str_age=String.valueOf(et_age.getText());

        if(!Patterns.EMAIL_ADDRESS.matcher(str_email).matches()){
            et_email.setError("Invalid email id");
            et_email.requestFocus();
            return;
        }

        if(!Pattern.matches(fullname_pattern,str_fullname)){
            et_fullname.setError("Invalid full name!");
            et_fullname.requestFocus();
            return;
        }

        /*if(!Pattern.matches(password_pattern,str_password)){
            et_password.setError("Password is not according to rules!");
            et_password.requestFocus();
            return;
        }*/

        if(!str_password.equals(str_confirm_password)){
            et_confirm_password.setError("Passwords are not matching!");
            et_confirm_password.requestFocus();
            return;
        }

        if(Integer.valueOf(str_age)<=0||Integer.valueOf(str_age)>=100){
            et_age.setError("Invalid age!");
            et_age.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(str_email,str_password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            SignUpData sign_up_data1=new SignUpData(str_email,str_fullname,str_password, str_age);
                            FirebaseDatabase.getInstance().getReference("Login")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(sign_up_data1).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {
                                    if(task.isSuccessful()){
                                        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
                                        user.sendEmailVerification();
                                        Toast.makeText(getApplicationContext(),"Verify yourself using the link sent to your mail!",Toast.LENGTH_LONG).show();
                                        Toast.makeText(getApplicationContext(),"Sign-up successful",Toast.LENGTH_LONG).show();
                                        navigate_to_login();

                                    }
                                    else{
                                        Toast.makeText(getApplicationContext(),"failed to sign-up try again!",Toast.LENGTH_LONG).show();
                                    }
                                }
                            });

                        }else{
                            Toast.makeText(getApplicationContext(),"failed to sign-up \nYou already have an account!\nPlease login using the mail-id!",Toast.LENGTH_LONG).show();
                        }
                    }
                });

    }
    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if(keyCode == KeyEvent.KEYCODE_BACK){
            finish();
        }
        return super.onKeyDown(keyCode, event);
    }

    void navigate_to_login(){
        finish();
        Intent i=new Intent(SignUp.this,login.class);
        startActivity(i);
    }
}