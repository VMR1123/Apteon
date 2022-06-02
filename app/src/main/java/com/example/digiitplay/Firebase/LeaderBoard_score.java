package com.example.digiitplay.Firebase;

public class LeaderBoard_score {
    String username,uid;
    int score;
    double accuracy;

    public LeaderBoard_score() {
    }

    public LeaderBoard_score(String username, int score, double accuracy,String uid) {
        this.username = username;
        this.score = score;
        this.accuracy = accuracy;
        this.uid = uid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }
}
