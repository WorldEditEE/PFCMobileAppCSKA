package com.example.mobileappcska.model.API;

import com.example.mobileappcska.model.entity.PlayerResponse;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {

    static final String API_KEY = "730ef4e573b509f5814aadb1a0cb33353323665c801df5439472cc8faaa6bf31";

    @GET("?&met=Topscorers&leagueId=207&APIkey="+API_KEY)
    Observable<PlayerResponse> getPlayers();

}
