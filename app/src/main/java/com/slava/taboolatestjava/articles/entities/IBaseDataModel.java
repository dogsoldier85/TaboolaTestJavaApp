package com.slava.taboolatestjava.articles.entities;

public interface IBaseDataModel {

    DataType getType();

    int getId();

    String getName();

    String getDescription();

    String getThumbnail();
}
