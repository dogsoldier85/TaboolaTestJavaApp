package com.slava.taboolatestjava.articles.entities;

import com.slava.taboolatestjava.persistence.ArticleDBEntity;

public class ArticleDataModel implements IBaseDataModel {

    private int mId;
    private String mName;
    private String mDescription;
    private String mThumbnail;

    public ArticleDataModel(ArticleDBEntity articleDBEntity) {
        this.mId = articleDBEntity.id;
        this.mName = articleDBEntity.name;
        this.mDescription = articleDBEntity.description;
        this.mThumbnail = articleDBEntity.thumbnail;
    }

    @Override
    public DataType getType() {
        return DataType.ARTICLE;
    }

    public int getId() {
        return mId;
    }

    public String getName() {
        return mName;
    }

    public String getDescription() {
        return mDescription;
    }

    public String getThumbnail() {
        return mThumbnail;
    }
}
