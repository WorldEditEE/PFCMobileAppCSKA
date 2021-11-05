package com.example.mobileappcska.model.API;

import com.example.mobileappcska.model.API.entity.Match;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("?met=Fixtures&APIkey=c2a5627582e66f9ce31335a91e7a98567035d4f3bb6c05579a19f57d5afda75c")
    Call<Match> getMatches(@Query("from") String from,
                           @Query("to") String to,
                           @Query("teamId") int teamId

    );

}
