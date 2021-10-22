package com.example.mobileappcska.model.API;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.mobileappcska.model.API.entity.Match;
import com.example.mobileappcska.model.API.entity.Result;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PlayerRepository {

    private Application application;
    private MutableLiveData<List<Result>> listMutableLiveData;

    public PlayerRepository(Application application){

        this.application = application;
        listMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<Result>> getListMutableLiveData() {
        return listMutableLiveData;
    }

    public void initListPlayers(){

        ApiFactory apiFactory = ApiFactory.getInstance();
        ApiService apiService = apiFactory.getApiService();

        apiService.getMatches().enqueue(new Callback<Match>() {
            @Override
            public void onResponse(Call<Match> call, Response<Match> response) {
                listMutableLiveData.postValue(response.body().getResult());
            }

            @Override
            public void onFailure(Call<Match> call, Throwable t) {

            }
        });
    }
}
