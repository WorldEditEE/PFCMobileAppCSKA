package com.example.mobileappcska.model.API;

import android.app.Application;
import android.widget.Toast;

import androidx.lifecycle.MutableLiveData;

import com.example.mobileappcska.model.API.entity.Match;
import com.example.mobileappcska.model.API.entity.Result;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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

        Calendar currentTime = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar afterMonthTime = Calendar.getInstance();
        afterMonthTime.add(Calendar.DATE,30);
        Date currentDate = currentTime.getTime();
        Date afterMonthDate = afterMonthTime.getTime();

        apiService.getMatches(dateFormat.format(currentDate),
                dateFormat.format(afterMonthDate), 169)
                .enqueue(new Callback<Match>() {
            @Override
            public void onResponse(Call<Match> call, Response<Match> response) {
                listMutableLiveData.postValue(response.body().getResult());
            }

            @Override
            public void onFailure(Call<Match> call, Throwable t) {
                Toast.makeText(application.getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
