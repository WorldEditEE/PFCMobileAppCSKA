package com.example.mobileappcska.model.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class PlayerResponse {

    @SerializedName("success")
    @Expose
    private int success;
    @SerializedName("result")
    @Expose
    private List<Player> result = null;

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public List<Player> getResult() {
        return result;
    }

    public void setResult(List<Player> result) {
        this.result = result;
    }

}