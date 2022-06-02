package com.example.digiitplay.Firebase;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.digiitplay.login.SignUpData;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class FirebaseInsert {

    FirebaseDatabase fd;
    DatabaseReference DbRef,leaderboard_ref,login_ref;
    String uid;
    String username;
    SignUpData signUpData;

    ArrayList<LeaderBoard_score> leaderboard_list=new ArrayList<>();

    public FirebaseInsert(){

        fd= FirebaseDatabase.getInstance();
        DbRef = fd.getReference("Score");
        leaderboard_ref = fd.getReference("Leaderboard");
        login_ref= FirebaseDatabase.getInstance().getReference("Login");
        uid= FirebaseAuth.getInstance().getCurrentUser().getUid();

        login_ref.child(uid).get().addOnCompleteListener(new OnCompleteListener<DataSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DataSnapshot> task) {
                if(task.isSuccessful()){
                    DataSnapshot dbsnap=task.getResult();
                    signUpData =dbsnap.getValue(SignUpData.class);
                    if(signUpData!=null) {
                        username = signUpData.getUsername();
                        System.out.println(username);
                    }
                }
                else{
                    System.out.println("failed while collecting username");
                }
            }
        });

        login_ref.keepSynced(true);
        leaderboard_ref.keepSynced(true);
        DbRef.keepSynced(true);

    }


    public boolean insert(String game_name, Score score, Context context){

        boolean result=false;
        String date_time= getDate();

        DbRef.child(uid).child(game_name).child(score.getGame_type()).child(date_time).setValue(score).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    LeaderBoard_score ld_score= new LeaderBoard_score(username, score.getScore(),score.getAccuracy(),uid);
                    boolean res = leaderboard_check(game_name,ld_score);
                    Log.d("Leaderboard","got the data successfully");

                    Toast.makeText(context, "Data Inserted", Toast.LENGTH_LONG).show();
                }
                else{
                    Toast.makeText(context, "failed", Toast.LENGTH_LONG).show();
                }
            }
        });
        return true;
    }
    
    boolean leaderboard_check(String game_name, LeaderBoard_score score){
        getLeaderboardData(game_name);

            leaderboard_ref.child(game_name).child(getDate()).setValue(score).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if(task.isSuccessful()){
                        Log.d("Leaderboard","got the data successfully");
                    }
                }
            });

        getLeaderboardData(game_name);
        return true;
    }

    void getLeaderboardData(String game_name){
        leaderboard_ref.child(game_name).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                leaderboard_list.clear();
                for(DataSnapshot all: snapshot.getChildren()){
                    LeaderBoard_score data= all.getValue(LeaderBoard_score.class);
                    leaderboard_list.add(data);
                    Log.d("Leaderboard", "Size of leaderboard data: "+ leaderboard_list.size());
                }

                System.out.println(leaderboard_list.size());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public String getDate(){
        SimpleDateFormat date = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss", Locale.getDefault());
        String currentDate = date.format(new Date());
        System.out.println(currentDate);
        return currentDate;
    }
}
