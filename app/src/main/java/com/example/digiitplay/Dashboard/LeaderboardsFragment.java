package com.example.digiitplay.Dashboard;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.digiitplay.Firebase.LeaderBoard_score;
import com.example.digiitplay.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collections;

public class LeaderboardsFragment extends Fragment {

    FirebaseDatabase fd;
    DatabaseReference leaderboard_ref;

    ArrayList<LeaderBoard_score> leaderboard_list=new ArrayList<>();
    ArrayList<LeaderBoard_score> leaderboard_list1=new ArrayList<>();

    RecyclerView rv,rv2;
    RvLeaderboardAdapter rvAdapter,rvAdapter2;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_leaderboards, container, false);

        fd= FirebaseDatabase.getInstance();
        leaderboard_ref = fd.getReference("Leaderboard");

        getLeaderboardData("Digitplay");

        rv=view.findViewById(R.id.rv_1);

        LinearLayoutManager llm=new LinearLayoutManager(view.getContext());
        llm.setReverseLayout(true);

        rv.setLayoutManager(llm);

        rvAdapter=new RvLeaderboardAdapter(view.getContext(),leaderboard_list);
        rv.setAdapter(rvAdapter);



        rv2=view.findViewById(R.id.rv_2);

        getLeaderboardData("ShapeShift");
        LinearLayoutManager llm1=new LinearLayoutManager(view.getContext());
        llm1.setReverseLayout(true);

        rv2.setLayoutManager(llm1);

        rvAdapter2=new RvLeaderboardAdapter(view.getContext(),leaderboard_list1);
        rv2.setAdapter(rvAdapter2);


        return view;
    }

    void getLeaderboardData(String game_name){
        if (game_name.equals("Digitplay")) {

            leaderboard_ref.child(game_name).orderByChild("score").limitToLast(10).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    leaderboard_list.clear();
                    for(DataSnapshot all: snapshot.getChildren()){
                        LeaderBoard_score data= all.getValue(LeaderBoard_score.class);
                        leaderboard_list.add(data);
                    }

                    rvAdapter.notifyDataSetChanged();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

        else if(game_name.equals("ShapeShift")){
            leaderboard_ref.child(game_name).orderByChild("score").limitToLast(10).addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    leaderboard_list1.clear();
                    for(DataSnapshot all: snapshot.getChildren()){
                        LeaderBoard_score data= all.getValue(LeaderBoard_score.class);
                        leaderboard_list1.add(data);
                    }

                    rvAdapter2.notifyDataSetChanged();

                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {

                }
            });
        }

    }
}