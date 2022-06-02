package com.example.digiitplay.Dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;

import com.example.digiitplay.OperatorOverload.OperatorOverloadDashboardActivity;
import com.example.digiitplay.R;
import com.example.digiitplay.OperatorOverloadScoreHistory.ScoreHistoryActivity;
import com.example.digiitplay.ShapeShift.ShapeShiftDashboardActivity;
import com.example.digiitplay.ShapeShiftScoreHistory.ShapeShiftScoreHistoryActivity;

public class GamesFragment extends Fragment {

    Button b1, b2, b3, b4;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_games, container, false);

        b1 = view.findViewById(R.id.b1);
        b2 = view.findViewById(R.id.b2);
        b3 = view.findViewById(R.id.b3);
        b4 = view.findViewById(R.id.b4);

        b1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(getActivity(), OperatorOverloadDashboardActivity.class);
                startActivity(i);
            }
        });

        b3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(getActivity(), ShapeShiftDashboardActivity.class);
                startActivity(i);
            }
        });

        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ScoreHistoryActivity.class);
                startActivity(i);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getActivity(), ShapeShiftScoreHistoryActivity.class);
                startActivity(i);
            }
        });

        return view;
    }
}