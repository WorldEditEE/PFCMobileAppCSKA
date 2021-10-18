package com.example.mobileappcska.model.API;

import com.example.mobileappcska.model.API.entity.Match;

import io.reactivex.Observable;
import retrofit2.http.GET;

public interface ApiService {

    static final String API_KEY = "730ef4e573b509f5814aadb1a0cb33353323665c801df5439472cc8faaa6bf31";

    @GET("?met=Fixtures&APIkey=c2a5627582e66f9ce31335a91e7a98567035d4f3bb6c05579a19f57d5afda75c&from=2021-10-18&to=2021-11-20&teamId=169")
    Observable<Match> getMatches();

}
