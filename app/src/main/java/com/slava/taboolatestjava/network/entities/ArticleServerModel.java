package com.slava.taboolatestjava.network.entities;

import com.google.gson.annotations.SerializedName;

public class ArticleServerModel {

    @SerializedName("name")
    private String mName;

    @SerializedName("description")
    private String mDescription;

    @SerializedName("thumbnail")
    private String mThumbnail;

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getThumbnail() {
        return mThumbnail;
    }

    public ArticleServerModel(String mName, String mDescription, String mThumbnail) {
        this.mName = mName;
        this.mDescription = mDescription;
        this.mThumbnail = mThumbnail;
    }
}
