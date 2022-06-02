package com.example.digiitplay.Dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.digiitplay.DbHandler;

import com.example.digiitplay.EncryptDecrypt;
import com.example.digiitplay.R;

import com.example.digiitplay.login.NetworkState;
import com.example.digiitplay.login.SignUp;
import com.example.digiitplay.login.SignUpData;
import com.example.digiitplay.login.SplashScreen;
import com.example.digiitplay.login.login;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.scottyab.aescrypt.AESCrypt;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    RelativeLayout rl1, rl2, rl3;
    ;
    EncryptDecrypt aes = new EncryptDecrypt();
    DbHandler db;

    Integer imageArray[] = {R.drawable.avatar_1, R.drawable.avatar_2, R.drawable.avatar_3, R.drawable.avatar_4, R.drawable.avatar_5, R.drawable.avatar_6, R.drawable.avatar_7, R.drawable.avatar_8, R.drawable.avatar_9, R.drawable.avatar_10, R.drawable.avatar_11, R.drawable.avatar_12, R.drawable.avatar_13, R.drawable.avatar_14, R.drawable.avatar_15,};

    EditText et_fullname, et_age;
    Button btn_saveprofile, btn_changepassword, btn_logout;

    FirebaseAuth mAuth;
    FirebaseUser user;
    DatabaseReference dbref;

    SignUpData data;
    NetworkState isConnected;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        rl1 = view.findViewById(R.id.rl1);
        rl2 = view.findViewById(R.id.rl2);
        rl3 = view.findViewById(R.id.rl3);

        btn_saveprofile = view.findViewById(R.id.btn_saveprofile);
        btn_changepassword = view.findViewById(R.id.btn_changepassword);
        btn_logout = view.findViewById(R.id.btn_logout);

        et_fullname = view.findViewById(R.id.et_editname);
        et_age = view.findViewById(R.id.et_editAge);

        db = new DbHandler(view.getContext());

        dbref = FirebaseDatabase.getInstance().getReference("Login");
        dbref.keepSynced(true);
        mAuth = FirebaseAuth.getInstance();
        isConnected = new NetworkState();


        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mAuth.signOut();
                db.drop();
                //needs some addition here
                startActivity(new Intent(view.getContext(), login.class));
                getActivity().finish();
            }
        });


        //call update to update changed profile data
        btn_saveprofile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isConnected.networkstate(view.getContext())) {
                    if (!update()) {
                        Toast.makeText(view.getContext(), "No change", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(view.getContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();

                }

            }
        });

        //send password reset link if internet connection is active
        btn_changepassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isConnected.networkstate(view.getContext())) {
                    mAuth.sendPasswordResetEmail(data.getEmail()).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(view.getContext(), "Check email for password reset link!", Toast.LENGTH_LONG).show();
                            } else {
                                Toast.makeText(view.getContext(), "Try Again! Something went wrong\nUser does not exists!", Toast.LENGTH_LONG).show();

                            }
                        }
                    });
                } else {
                    Toast.makeText(view.getContext(), "No Internet Connection", Toast.LENGTH_SHORT).show();

                }
            }
        });


        //Can be required in future
        /*dbref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                data=snapshot.getValue(SignUpData.class);
                if(data!=null) {
                    et_fullname.setText(data.getUsername());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/


        //get data of user from firebase and store it in SignUpData object
        dbref.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if (task.isSuccessful()) {
                    DataSnapshot dbsnap = task.getResult();
                    data = dbsnap.getValue(SignUpData.class);
                    if (data != null) {
                        et_fullname.setText(data.getUsername());
                        et_age.setText(data.getAge());
                    }
                } else {
                    Toast.makeText(view.getContext(), "failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

        return view;
    }

    //update data if data is changed
    boolean update() {
        String name;
        name = String.valueOf(et_fullname.getText());
        String age=" ";
        age =String.valueOf(et_age.getText());
        if (!data.getUsername().equals(name) || !data.getAge().equals(age)) {
            dbref.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("username").setValue(name);
            dbref.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child("age").setValue(age);

            return true;
        } else {
            return false;
        }


    }
}