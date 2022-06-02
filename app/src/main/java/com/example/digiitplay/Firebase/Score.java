package com.example.digiitplay.Firebase;

public class Score {

    int score;
    double accuracy;
    String game_type;
    String date;

    public Score(){

    }

    public Score(int score, double accuracy, String game_type, String date) {
        this.score = score;
        this.accuracy = accuracy;
        this.game_type = game_type;
        this.date =  date;
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

    public void setAccuracy(float accuracy) {
        this.accuracy = accuracy;
    }

    public String getGame_type() {
        return game_type;
    }

    public void setGame_type(String game_type) {
        this.game_type = game_type;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
