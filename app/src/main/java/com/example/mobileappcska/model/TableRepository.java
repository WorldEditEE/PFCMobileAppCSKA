package com.example.mobileappcska.model;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.mobileappcska.data.Club;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TableRepository {

    private Application application;
    private MutableLiveData<List<Club>> clubList;

    public TableRepository(Application application){

        this.application = application;
        clubList = new MutableLiveData<>();

    }

    public MutableLiveData<List<Club>> getClubList() {
        return clubList;
    }

    public void initTable(){

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                initClubList();
            }
        });

        thread.start();
    }

    private void initClubList(){

        Document document;
        String place;
        String name;
        String image;
        String games;
        String wins;
        String draws;
        String loses;
        String points;
        List<Club> clubListRPL = new ArrayList<>();

        try {
            document = Jsoup.connect("https://pfc-cska.com/matches/tables/").get();
            Elements elementsFromWeb = document.getElementsByTag("tr");
            for(int i = 1 ; i < elementsFromWeb.size() ; i++){
                Element club = elementsFromWeb.get(i);
                Elements elementsClub = club.getAllElements();
                place = elementsClub.get(1).text().trim();
                int limit = 10;
                String tempName = elementsClub.get(2).text().trim();
                name = tempName.length() > limit ? tempName.substring(0,limit) : tempName;
                games = elementsClub.get(4).text().trim();
                wins = elementsClub.get(5).text().trim();
                draws = elementsClub.get(6).text().trim();
                loses = elementsClub.get(7).text().trim();
                points = elementsClub.get(10).text().trim();
                image = getImageURL(tempName);
//                if(wins.length() == 1 && draws.length() == 1){
//                    draws = "  " + draws + " ";
//                }else if(draws.length() == 1 && loses.length() == 1){
//                    loses = loses + "   ";
//                }else {
//                    loses = loses + " ";
//                }
                if(place.length() == 1){
                    place = "  " + place;
                }
                Club clubRPL = new Club(place,name,image,games,wins,draws,loses,points);
                clubListRPL.add(clubRPL);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("Key 2", clubListRPL.toString());


        clubList.postValue(clubListRPL);
    }

    private String getImageURL(String name){

        String url;

        if(name.equals("Зенит")){
            url = "https://img.championat.com/s/60x60/team/logo/14658385841081757673.png";
            return url;
        }else if(name.equals("Арсенал Т")){
            url = "https://img.championat.com/s/60x60/team/logo/1469196810876965292.png";
            return url;
        }else if(name.equals("Динамо М")){
            url = "https://img.championat.com/s/60x60/team/logo/1465838729886051809.png";
            return url;
        }else if(name.equals("Ахмат")){
            url = "https://img.championat.com/s/60x60/team/logo/1501229909577937100.png";
            return url;
        }else if(name.equals("Спартак М")){
            url = "https://img.championat.com/s/60x60/team/logo/1469196578267020071.png";
            return url;
        }else if(name.equals("Локомотив М")){
            url = "https://img.championat.com/s/60x60/team/logo/14658384771344549206.png";
            return url;
        }else if(name.equals("Рубин")){
            url = "https://img.championat.com/s/60x60/team/logo/15619832822012126038.png";
            return url;
        }else if(name.equals("Сочи")){
            url = "https://img.championat.com/s/60x60/team/logo/1531215018203072749.png";
            return url;
        }else if(name.equals("ПФК ЦСКА")){
            url = "https://img.championat.com/s/60x60/team/logo/1465837941322079237.png";
            return url;
        }else if(name.equals("Химки")){
            url = "https://img.championat.com/s/60x60/team/logo/1469524664145066757.png";
            return url;
        }else if(name.equals("Ростов")){
            url = "https://img.championat.com/s/60x60/team/logo/14658384351941932061.png";
            return url;
        }else if(name.equals("Краснодар")){
            url = "https://img.championat.com/s/60x60/team/logo/14676324111013772033.png";
            return url;
        }else if(name.equals("Урал")){
            url = "https://img.championat.com/s/60x60/team/logo/14658380112046023866.png";
            return url;
        }else if(name.equals("Уфа")){
            url = "https://img.championat.com/s/60x60/team/logo/1598094099349110694.png";
            return url;
        }else if(name.equals("Крылья Советов")){
            url = "https://img.championat.com/s/60x60/team/logo/1465838512157254062.png";
            return url;
        }else if(name.equals("Нижний Новгород")){
            url = "https://img.championat.com/s/60x60/team/logo/1531481481507005825.png";
            return url;
        }else if(name.equals("Тамбов")){
            url = "https://img.championat.com/s/60x60/team/logo/1596883721853762766.png";
            return url;
        }else if(name.equals("Ротор")){
            url = "https://img.championat.com/s/60x60/team/logo/15312149722066119385.png";
            return url;
        }

        return null;
    }
}
