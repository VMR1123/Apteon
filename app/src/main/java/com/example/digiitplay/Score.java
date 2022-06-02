package com.example.digiitplay;


public class Score {
    private int id;
    private int score;
    private String date;
    private String time;
    private double accuracy;

    public Score() {

    }

    public int getId() {
        return id;
    }

    public int getScore() {
        return score;
    }

    public String getDate() {
        return date;
    }

    public String getTime() { return time; }

    public double getAccuracy() { return accuracy; }


    public void setId(int id) {
        this.id = id;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) { this.time = time; }

    public void setAccuracy(double accuracy) { this.accuracy = accuracy; }

}
