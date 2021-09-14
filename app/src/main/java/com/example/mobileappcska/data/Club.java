package com.example.mobileappcska.data;

import android.graphics.Bitmap;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;


@Entity(tableName = "club_db")
public class Club {

    @PrimaryKey
    @NonNull
    private String place;

    private String name;
    private String image;
    private String games;
    private String wins;
    private String draws;
    private String loses;
    private String points;

    public Club(@NonNull String place, String name, String image, String games, String wins, String draws, String loses, String points) {
        this.place = place;
        this.name = name;
        this.image = image;
        this.games = games;
        this.wins = wins;
        this.draws = draws;
        this.loses = loses;
        this.points = points;
    }

    public Club(){

    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGames() {
        return games;
    }

    public void setGames(String games) {
        this.games = games;
    }

    public String getWins() {
        return wins;
    }

    public void setWins(String wins) {
        this.wins = wins;
    }

    public String getDraws() {
        return draws;
    }

    public void setDraws(String draws) {
        this.draws = draws;
    }

    public String getLoses() {
        return loses;
    }

    public void setLoses(String loses) {
        this.loses = loses;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    @Override
    public String toString() {
        return "Club{" +
                "place='" + place + '\'' +
                ", name='" + name + '\'' +
                ", image='" + image + '\'' +
                ", games='" + games + '\'' +
                ", wins='" + wins + '\'' +
                ", draws='" + draws + '\'' +
                ", loses='" + loses + '\'' +
                ", points='" + points + '\'' +
                '}';
    }
}
