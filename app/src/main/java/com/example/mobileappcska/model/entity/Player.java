package com.example.mobileappcska.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Player {

    @SerializedName("player_place")
    @Expose
    private String playerPlace;
    @SerializedName("player_name")
    @Expose
    private String playerName;
    @SerializedName("player_key")
    @Expose
    private Long playerKey;
    @SerializedName("team_name")
    @Expose
    private String teamName;
    @SerializedName("team_key")
    @Expose
    private String teamKey;
    @SerializedName("goals")
    @Expose
    private String goals;
    @SerializedName("assists")
    @Expose
    private String assists;
    @SerializedName("penalty_goals")
    @Expose
    private String penaltyGoals;

    public String getPlayerPlace() {
        return playerPlace;
    }

    public void setPlayerPlace(String playerPlace) {
        this.playerPlace = playerPlace;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public long getPlayerKey() {
        return playerKey;
    }

    public void setPlayerKey(long playerKey) {
        this.playerKey = playerKey;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamKey() {
        return teamKey;
    }

    public void setTeamKey(String teamKey) {
        this.teamKey = teamKey;
    }

    public String getGoals() {
        return goals;
    }

    public void setGoals(String goals) {
        this.goals = goals;
    }

    public String getAssists() {
        return assists;
    }

    public void setAssists(String assists) {
        this.assists = assists;
    }

    public String getPenaltyGoals() {
        return penaltyGoals;
    }

    public void setPenaltyGoals(String penaltyGoals) {
        this.penaltyGoals = penaltyGoals;
    }

}
