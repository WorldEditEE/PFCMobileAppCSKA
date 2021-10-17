package com.example.mobileappcska.model.API;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.mobileappcska.model.entity.Player;
import com.example.mobileappcska.model.entity.PlayerResponse;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class PlayerRepository {

    private Application application;
    private MutableLiveData<List<Player>> listMutableLiveData;

    public PlayerRepository(Application application){

        this.application = application;
        listMutableLiveData = new MutableLiveData<>();
    }

    public MutableLiveData<List<Player>> getListMutableLiveData() {
        return listMutableLiveData;
    }

    public void initListPlayers(){

        ApiFactory apiFactory = ApiFactory.getInstance();
        ApiService apiService = apiFactory.getApiService();

        apiService.getPlayers().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<PlayerResponse>() {
                    @Override
                    public void accept(PlayerResponse playerResponse) throws Exception {
                        listMutableLiveData.postValue(playerResponse.getResult());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.d("TestkEY",throwable.getMessage());
                    }
                });
    }
}
