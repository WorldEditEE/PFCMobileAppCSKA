package com.example.mobileappcska.model.entity;

public class News {

    private String heading;
    private String about;
    private String imageURL;
    private String date;

    public News(String heading, String about, String imageURL, String date) {
        this.heading = heading;
        this.about = about;
        this.imageURL = imageURL;
        this.date = date;
    }
    public News(){

    }
    public String getHeading() {
        return heading;
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
