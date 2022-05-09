package com.example.digiitplay.Dashboard;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.digiitplay.DbHandler;
import com.example.digiitplay.DigitPlay.DigitPlayActivity;
import com.example.digiitplay.EncryptDecrypt;
import com.example.digiitplay.R;
import com.example.digiitplay.ScoreHistory.ScoreHistoryActivity;
import com.scottyab.aescrypt.AESCrypt;

import java.util.ArrayList;

public class ProfileFragment extends Fragment {

    RelativeLayout rl1, rl2, rl3;;
    EncryptDecrypt aes = new EncryptDecrypt();
    DbHandler db;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

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
                Intent i = new Intent(getActivity(), ScoreHistoryActivity.class);
                startActivity(i);
            }
        });

        return view;
    }
}