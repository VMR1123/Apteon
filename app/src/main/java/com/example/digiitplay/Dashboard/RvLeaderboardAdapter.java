package com.example.digiitplay.Dashboard;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digiitplay.Firebase.LeaderBoard_score;
import com.example.digiitplay.R;

import java.util.ArrayList;

public class RvLeaderboardAdapter extends RecyclerView.Adapter<RvLeaderboardAdapter.viewh>{


    ArrayList<LeaderBoard_score> leaderBoard_scores;
    static Context context;
    static int size;
    public RvLeaderboardAdapter(Context con, ArrayList<LeaderBoard_score> leaderBoard_scores) {
        context=con;
        this.leaderBoard_scores=leaderBoard_scores;
        size = leaderBoard_scores.size()-1;

    }

    @NonNull
    @Override
    public viewh onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_rv_card_leaderboard,parent,false);
        viewh vh = new viewh(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull viewh holder, int position) {

        System.out.println("in leaderboard");

        //LeaderBoard_score sc = leaderBoard_scores.get(position);
        holder.tv_name.setText(String.valueOf((leaderBoard_scores.get(position)).getUsername()));
        holder.tv_score.setText(String.valueOf((leaderBoard_scores.get(position)).getScore()));
        String accuracy = String.valueOf((leaderBoard_scores.get(position)).getAccuracy());
        holder.tv_accuracy.setText(accuracy.substring(0,5));
    }

    @Override
    public int getItemCount() {

        Log.d("class2", "getItemCount: "+ leaderBoard_scores.size());
        return leaderBoard_scores.size();
    }

    public static class viewh extends RecyclerView.ViewHolder {

        TextView tv_name,tv_score,tv_accuracy;
        CardView cv;
        public viewh(@NonNull View itemView) {
            super(itemView);
            tv_name = itemView.findViewById(R.id.tv_name);
            tv_score = itemView.findViewById(R.id.tv_score);
            tv_accuracy = itemView.findViewById(R.id.tv_accuracy);

        }
    }
}
