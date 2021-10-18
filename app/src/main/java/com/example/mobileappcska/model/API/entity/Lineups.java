package com.example.mobileappcska.model.API.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Lineups {

    @SerializedName("home_team")
    @Expose
    private HomeTeam homeTeam;
    @SerializedName("away_team")
    @Expose
    private AwayTeam awayTeam;

    public HomeTeam getHomeTeam() {
        return homeTeam;
    }

    public void setHomeTeam(HomeTeam homeTeam) {
        this.homeTeam = homeTeam;
    }

    public AwayTeam getAwayTeam() {
        return awayTeam;
    }

    public void setAwayTeam(AwayTeam awayTeam) {
        this.awayTeam = awayTeam;
    }

}
