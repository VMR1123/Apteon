package com.example.digiitplay;


public class Score {
    private int id;
    private String score;
    private String date;
    private String time;
    private String accuracy;

    public Score() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTime(String time) { this.time = time; }

    public void setAccuracy(String accuracy) { this.accuracy = accuracy; }
}
