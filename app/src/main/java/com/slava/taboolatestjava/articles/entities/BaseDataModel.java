package com.slava.taboolatestjava.articles.entities;

public class BaseDataModel implements IBaseDataModel {
    private DataType mType;

    public BaseDataModel(DataType type) {
        mType = type;
    }

    @Override
    public DataType getType() {
        return mType;
    }


    @Override
    public int getId() {
        return 0;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String getDescription() {
        return null;
    }

    @Override
    public String getThumbnail() {
        return null;
    }

}
