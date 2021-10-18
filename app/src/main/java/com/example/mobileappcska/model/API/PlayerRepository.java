package com.example.mobileappcska.model.API;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.example.mobileappcska.model.API.entity.Match;
import com.example.mobileappcska.model.API.entity.Result;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

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

        apiService.getMatches().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Match>() {
                    @Override
                    public void accept(Match match) throws Exception {
                        listMutableLiveData.postValue(match.getResult());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
    }
}
