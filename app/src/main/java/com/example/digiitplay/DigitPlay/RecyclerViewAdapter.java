package com.example.digiitplay.DigitPlay;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.digiitplay.R;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context context;
    public RecyclerViewAdapter adapter;
    public ArrayList<Score> scoreArrayList;

    public RecyclerViewAdapter(Context context, ArrayList<Score> scoreArrayList) {
        this.context = context;
        this.scoreArrayList = scoreArrayList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.score_list_template, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.ViewHolder holder, int position) {
        Score scoreposition = scoreArrayList.get(position);

        holder.score.setText(scoreposition.getScore() + " Pts");
        holder.date.setText(scoreposition.getDate());
        holder.accuracy.setText(scoreposition.getAccuracy() + "%");
    }

    @Override
    public int getItemCount() {
        return scoreArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView date;
        public TextView score;
        public TextView accuracy;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
//            itemView.setOnClickListener(this);

            date = itemView.findViewById(R.id.date);
            score = itemView.findViewById(R.id.score);
            accuracy = itemView.findViewById(R.id.accuracy);
        }
    }
}

