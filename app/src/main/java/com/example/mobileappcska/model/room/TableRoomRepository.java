package com.example.mobileappcska.model.room;

import android.app.Application;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.mobileappcska.model.entity.Club;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TableRoomRepository {

    private Application application;
    private LiveData<List<Club>> listClubLiveData;
    private ClubsDatabase database;
    private clubsDAO clubsDAO;

    public TableRoomRepository(Application application){

        this.application = application;
        database = ClubsDatabase.getInstance(application);
        clubsDAO = database.clubsDAO();
        listClubLiveData = clubsDAO.getAllClubs();

    }

    public LiveData<List<Club>> getListClubLiveData() {
        return listClubLiveData;
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

        Document document;String place;String name;String image;String games;String wins;String draws;String loses;String points;
        List<Club> clubsRPL = new ArrayList<>();

        try {
            document = Jsoup.connect("https://pfc-cska.com/matches/tables/").get();
            clubsDAO.deleteAllClubs();
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

                if(place.length() == 1){
                    place = "  " + place;
                }
                Club clubRPL = new Club(place,name,image,games,wins,draws,loses,points);
                clubsRPL.add(clubRPL);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d("Key 2", clubsDAO.getAllClubs().toString());
                for(Club club : clubsRPL){
                    clubsDAO.insert(club);
                }


    }

    private String getImageURL(String name){

        String url;

        if(name.equals("??????????")){
            url = "https://img.championat.com/s/60x60/team/logo/14658385841081757673.png";
            return url;
        }else if(name.equals("?????????????? ??")){
            url = "https://img.championat.com/s/60x60/team/logo/1469196810876965292.png";
            return url;
        }else if(name.equals("???????????? ??")){
            url = "https://img.championat.com/s/60x60/team/logo/1465838729886051809.png";
            return url;
        }else if(name.equals("??????????")){
            url = "https://img.championat.com/s/60x60/team/logo/1501229909577937100.png";
            return url;
        }else if(name.equals("?????????????? ??")){
            url = "https://img.championat.com/s/60x60/team/logo/1469196578267020071.png";
            return url;
        }else if(name.equals("?????????????????? ??")){
            url = "https://img.championat.com/s/60x60/team/logo/14658384771344549206.png";
            return url;
        }else if(name.equals("??????????")){
            url = "https://img.championat.com/s/60x60/team/logo/15619832822012126038.png";
            return url;
        }else if(name.equals("????????")){
            url = "https://img.championat.com/s/60x60/team/logo/1531215018203072749.png";
            return url;
        }else if(name.equals("?????? ????????")){
            url = "https://img.championat.com/s/60x60/team/logo/1465837941322079237.png";
            return url;
        }else if(name.equals("??????????")){
            url = "https://img.championat.com/s/60x60/team/logo/1469524664145066757.png";
            return url;
        }else if(name.equals("????????????")){
            url = "https://img.championat.com/s/60x60/team/logo/14658384351941932061.png";
            return url;
        }else if(name.equals("??????????????????")){
            url = "https://img.championat.com/s/60x60/team/logo/14676324111013772033.png";
            return url;
        }else if(name.equals("????????")){
            url = "https://img.championat.com/s/60x60/team/logo/14658380112046023866.png";
            return url;
        }else if(name.equals("??????")){
            url = "https://img.championat.com/s/60x60/team/logo/1598094099349110694.png";
            return url;
        }else if(name.equals("???????????? ??????????????")){
            url = "https://img.championat.com/s/60x60/team/logo/1465838512157254062.png";
            return url;
        }else if(name.equals("???????????? ????????????????")){
            url = "https://img.championat.com/s/60x60/team/logo/1531481481507005825.png";
            return url;
        }else if(name.equals("????????????")){
            url = "https://img.championat.com/s/60x60/team/logo/1596883721853762766.png";
            return url;
        }else if(name.equals("??????????")){
            url = "https://img.championat.com/s/60x60/team/logo/15312149722066119385.png";
            return url;
        }

        return null;
    }

}
