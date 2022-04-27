package com.example.digiitplay.Dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import androidx.fragment.app.Fragment;

import com.example.digiitplay.DigitPlay.DigitPlayActivity;
import com.example.digiitplay.R;

public class GamesFragment extends Fragment {

    RelativeLayout rl1, rl2, rl3;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_games, container, false);

        rl1 = view.findViewById(R.id.rl1);
        rl2 = view.findViewById(R.id.rl2);
        rl3 = view.findViewById(R.id.rl3);

        rl1.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(getActivity(), DigitPlayActivity.class);
                startActivity(i);
            }
        });

        rl2.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(getActivity(), DigitPlayActivity.class);
                startActivity(i);
            }
        });

        rl3.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Intent i = new Intent(getActivity(), DigitPlayActivity.class);
                startActivity(i);
            }
        });

        return view;
    }
}