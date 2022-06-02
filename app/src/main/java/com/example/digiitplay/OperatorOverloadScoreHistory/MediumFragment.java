package com.example.digiitplay.OperatorOverloadScoreHistory;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digiitplay.DbHandler;
import com.example.digiitplay.R;
import com.example.digiitplay.RecyclerViewAdapter;
import com.example.digiitplay.Score;

import java.util.ArrayList;

public class MediumFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_medium, container, false);

        DbHandler dbh;
        dbh = new DbHandler(view.getContext());

        RecyclerView recyclerView1 = view.findViewById(R.id.rv1);
        recyclerView1.setHasFixedSize(true);
        recyclerView1.setLayoutManager(new LinearLayoutManager(view.getContext()));

        RecyclerView recyclerView2 = view.findViewById(R.id.rv2);
        recyclerView2.setHasFixedSize(true);
        recyclerView2.setLayoutManager(new LinearLayoutManager(view.getContext()));

        ArrayList<Score> top5 = dbh.getTop5EncryptedScores(6);
        ArrayList<Score> last5 = dbh.getLast5EncryptedScores(6);

        RecyclerViewAdapter recyclerViewAdapter1 = new RecyclerViewAdapter(view.getContext(), top5);
        recyclerView1.setAdapter(recyclerViewAdapter1);

        RecyclerViewAdapter recyclerViewAdapter2 = new RecyclerViewAdapter(view.getContext(), last5);
        recyclerView2.setAdapter(recyclerViewAdapter2);

        TextView textView = view.findViewById(R.id.textView17);
        textView.setText(dbh.getRoundCount(6));

        String totalscore;
        TextView textView1 = view.findViewById(R.id.textView19);
        totalscore = dbh.getTotalScore(6) + " Pts";
        if (dbh.getTotalScore(6) == null)
            totalscore = 0 + " Pts";

        textView1.setText(totalscore);

        String avgAccuracy;
        avgAccuracy = dbh.getAverageAccuracy(6) + " Pts";

        if (dbh.getAverageAccuracy(6) == null)
            avgAccuracy = 0 + " Pts";

        TextView textView2 = view.findViewById(R.id.textView21);
        textView2.setText(avgAccuracy);

        return view;
    }
}