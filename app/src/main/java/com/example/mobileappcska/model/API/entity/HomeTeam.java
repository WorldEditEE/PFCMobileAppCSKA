package com.example.mobileappcska.model.API.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class HomeTeam {

    @SerializedName("starting_lineups")
    @Expose
    private List<Object> startingLineups = null;
    @SerializedName("substitutes")
    @Expose
    private List<Object> substitutes = null;
    @SerializedName("coaches")
    @Expose
    private List<Object> coaches = null;
    @SerializedName("missing_players")
    @Expose
    private List<Object> missingPlayers = null;

    public List<Object> getStartingLineups() {
        return startingLineups;
    }

    public void setStartingLineups(List<Object> startingLineups) {
        this.startingLineups = startingLineups;
    }

    public List<Object> getSubstitutes() {
        return substitutes;
    }

    public void setSubstitutes(List<Object> substitutes) {
        this.substitutes = substitutes;
    }

    public List<Object> getCoaches() {
        return coaches;
    }

    public void setCoaches(List<Object> coaches) {
        this.coaches = coaches;
    }

    public List<Object> getMissingPlayers() {
        return missingPlayers;
    }

    public void setMissingPlayers(List<Object> missingPlayers) {
        this.missingPlayers = missingPlayers;
    }

}
