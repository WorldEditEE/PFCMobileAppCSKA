package com.example.mobileappcska.model.API.entity;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Result {

    @SerializedName("event_key")
    @Expose
    private String eventKey;
    @SerializedName("event_date")
    @Expose
    private String eventDate;
    @SerializedName("event_time")
    @Expose
    private String eventTime;
    @SerializedName("event_home_team")
    @Expose
    private String eventHomeTeam;
    @SerializedName("home_team_key")
    @Expose
    private String homeTeamKey;
    @SerializedName("event_away_team")
    @Expose
    private String eventAwayTeam;
    @SerializedName("away_team_key")
    @Expose
    private String awayTeamKey;
    @SerializedName("event_halftime_result")
    @Expose
    private String eventHalftimeResult;
    @SerializedName("event_final_result")
    @Expose
    private String eventFinalResult;
    @SerializedName("event_ft_result")
    @Expose
    private String eventFtResult;
    @SerializedName("event_penalty_result")
    @Expose
    private String eventPenaltyResult;
    @SerializedName("event_status")
    @Expose
    private String eventStatus;
    @SerializedName("country_name")
    @Expose
    private String countryName;
    @SerializedName("league_name")
    @Expose
    private String leagueName;
    @SerializedName("league_key")
    @Expose
    private String leagueKey;
    @SerializedName("league_round")
    @Expose
    private String leagueRound;
    @SerializedName("league_season")
    @Expose
    private String leagueSeason;
    @SerializedName("event_live")
    @Expose
    private String eventLive;
    @SerializedName("event_stadium")
    @Expose
    private String eventStadium;
    @SerializedName("event_referee")
    @Expose
    private String eventReferee;
    @SerializedName("home_team_logo")
    @Expose
    private String homeTeamLogo;
    @SerializedName("away_team_logo")
    @Expose
    private String awayTeamLogo;
    @SerializedName("event_country_key")
    @Expose
    private String eventCountryKey;
    @SerializedName("league_logo")
    @Expose
    private String leagueLogo;
    @SerializedName("country_logo")
    @Expose
    private String countryLogo;
    @SerializedName("event_home_formation")
    @Expose
    private String eventHomeFormation;
    @SerializedName("event_away_formation")
    @Expose
    private String eventAwayFormation;
    @SerializedName("fk_stage_key")
    @Expose
    private String fkStageKey;
    @SerializedName("stage_name")
    @Expose
    private String stageName;
    @SerializedName("goalscorers")
    @Expose
    private List<Object> goalscorers = null;
    @SerializedName("substitutes")
    @Expose
    private List<Object> substitutes = null;
    @SerializedName("cards")
    @Expose
    private List<Object> cards = null;
    @SerializedName("lineups")
    @Expose
    private Lineups lineups;
    @SerializedName("statistics")
    @Expose
    private List<Object> statistics = null;

    public String getEventKey() {
        return eventKey;
    }

    public void setEventKey(String eventKey) {
        this.eventKey = eventKey;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public String getEventTime() {
        return eventTime;
    }

    public void setEventTime(String eventTime) {
        this.eventTime = eventTime;
    }

    public String getEventHomeTeam() {
        return eventHomeTeam;
    }

    public void setEventHomeTeam(String eventHomeTeam) {
        this.eventHomeTeam = eventHomeTeam;
    }

    public String getHomeTeamKey() {
        return homeTeamKey;
    }

    public void setHomeTeamKey(String homeTeamKey) {
        this.homeTeamKey = homeTeamKey;
    }

    public String getEventAwayTeam() {
        return eventAwayTeam;
    }

    public void setEventAwayTeam(String eventAwayTeam) {
        this.eventAwayTeam = eventAwayTeam;
    }

    public String getAwayTeamKey() {
        return awayTeamKey;
    }

    public void setAwayTeamKey(String awayTeamKey) {
        this.awayTeamKey = awayTeamKey;
    }

    public String getEventHalftimeResult() {
        return eventHalftimeResult;
    }

    public void setEventHalftimeResult(String eventHalftimeResult) {
        this.eventHalftimeResult = eventHalftimeResult;
    }

    public String getEventFinalResult() {
        return eventFinalResult;
    }

    public void setEventFinalResult(String eventFinalResult) {
        this.eventFinalResult = eventFinalResult;
    }

    public String getEventFtResult() {
        return eventFtResult;
    }

    public void setEventFtResult(String eventFtResult) {
        this.eventFtResult = eventFtResult;
    }

    public String getEventPenaltyResult() {
        return eventPenaltyResult;
    }

    public void setEventPenaltyResult(String eventPenaltyResult) {
        this.eventPenaltyResult = eventPenaltyResult;
    }

    public String getEventStatus() {
        return eventStatus;
    }

    public void setEventStatus(String eventStatus) {
        this.eventStatus = eventStatus;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getLeagueName() {
        return leagueName;
    }

    public void setLeagueName(String leagueName) {
        this.leagueName = leagueName;
    }

    public String getLeagueKey() {
        return leagueKey;
    }

    public void setLeagueKey(String leagueKey) {
        this.leagueKey = leagueKey;
    }

    public String getLeagueRound() {
        return leagueRound;
    }

    public void setLeagueRound(String leagueRound) {
        this.leagueRound = leagueRound;
    }

    public String getLeagueSeason() {
        return leagueSeason;
    }

    public void setLeagueSeason(String leagueSeason) {
        this.leagueSeason = leagueSeason;
    }

    public String getEventLive() {
        return eventLive;
    }

    public void setEventLive(String eventLive) {
        this.eventLive = eventLive;
    }

    public String getEventStadium() {
        return eventStadium;
    }

    public void setEventStadium(String eventStadium) {
        this.eventStadium = eventStadium;
    }

    public String getEventReferee() {
        return eventReferee;
    }

    public void setEventReferee(String eventReferee) {
        this.eventReferee = eventReferee;
    }

    public String getHomeTeamLogo() {
        return homeTeamLogo;
    }

    public void setHomeTeamLogo(String homeTeamLogo) {
        this.homeTeamLogo = homeTeamLogo;
    }

    public String getAwayTeamLogo() {
        return awayTeamLogo;
    }

    public void setAwayTeamLogo(String awayTeamLogo) {
        this.awayTeamLogo = awayTeamLogo;
    }

    public String getEventCountryKey() {
        return eventCountryKey;
    }

    public void setEventCountryKey(String eventCountryKey) {
        this.eventCountryKey = eventCountryKey;
    }

    public String getLeagueLogo() {
        return leagueLogo;
    }

    public void setLeagueLogo(String leagueLogo) {
        this.leagueLogo = leagueLogo;
    }

    public String getCountryLogo() {
        return countryLogo;
    }

    public void setCountryLogo(String countryLogo) {
        this.countryLogo = countryLogo;
    }

    public String getEventHomeFormation() {
        return eventHomeFormation;
    }

    public void setEventHomeFormation(String eventHomeFormation) {
        this.eventHomeFormation = eventHomeFormation;
    }

    public String getEventAwayFormation() {
        return eventAwayFormation;
    }

    public void setEventAwayFormation(String eventAwayFormation) {
        this.eventAwayFormation = eventAwayFormation;
    }

    public String getFkStageKey() {
        return fkStageKey;
    }

    public void setFkStageKey(String fkStageKey) {
        this.fkStageKey = fkStageKey;
    }

    public String getStageName() {
        return stageName;
    }

    public void setStageName(String stageName) {
        this.stageName = stageName;
    }

    public List<Object> getGoalscorers() {
        return goalscorers;
    }

    public void setGoalscorers(List<Object> goalscorers) {
        this.goalscorers = goalscorers;
    }

    public List<Object> getSubstitutes() {
        return substitutes;
    }

    public void setSubstitutes(List<Object> substitutes) {
        this.substitutes = substitutes;
    }

    public List<Object> getCards() {
        return cards;
    }

    public void setCards(List<Object> cards) {
        this.cards = cards;
    }

    public Lineups getLineups() {
        return lineups;
    }

    public void setLineups(Lineups lineups) {
        this.lineups = lineups;
    }

    public List<Object> getStatistics() {
        return statistics;
    }

    public void setStatistics(List<Object> statistics) {
        this.statistics = statistics;
    }

}
