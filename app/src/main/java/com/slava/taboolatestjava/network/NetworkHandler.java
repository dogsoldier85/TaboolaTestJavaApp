package com.slava.taboolatestjava.network;

import com.slava.taboolatestjava.BuildConfig;
import com.slava.taboolatestjava.R;
import com.slava.taboolatestjava.TaboolaApplication;
import com.slava.taboolatestjava.entities.Result;
import com.slava.taboolatestjava.network.entities.ArticleServerModel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import timber.log.Timber;

public class NetworkHandler implements INetworkHandler {

    private TaboolaApiServices retrofitService;

    public NetworkHandler() {
        OkHttpClient.Builder builder = new OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS);

        Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
                .baseUrl(BuildConfig.SERVER_URL)
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create());
        retrofitService = retrofitBuilder.build().create(TaboolaApiServices.class);
    }

    @Override
    public Result getData() {
        Timber.d("Fetching articles from remote API");
        Response<ArrayList<ArticleServerModel>> response = null;
        try {
            response = retrofitService.getData().execute();
            if (response.isSuccessful()) {
                Timber.d("Fetching articles from remote API - Succeeded ${response.errorBody()}");
                return new Result(response.body(), null);
            } else {
                Timber.d("Fetching articles from remote API - Failed ${response.errorBody()}");
                return new Result(null, new IOException(TaboolaApplication.applicationContext.getString(R.string.general_error)));
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new Result(null, new IOException(TaboolaApplication.applicationContext.getString(R.string.general_error)));
        }
    }
}
