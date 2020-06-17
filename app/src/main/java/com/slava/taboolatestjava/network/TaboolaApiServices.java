package com.slava.taboolatestjava.network;

import com.slava.taboolatestjava.network.entities.ArticleServerModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface TaboolaApiServices {

    @GET("taboola-mobile-sdk/public/home_assignment/data.json")
    Call<ArrayList<ArticleServerModel>> getData();
}
