package com.slava.taboolatestjava.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.slava.taboolatestjava.TaboolaApplication;

@Database(entities = {ArticleDBEntity.class}, version = 1, exportSchema = false)
public abstract class ArticlesDatabase extends RoomDatabase {

    public abstract ArticlesDao articlesDao();

    private static ArticlesDatabase INSTANCE;


    public static ArticlesDatabase getAppDatabase() {
        if (INSTANCE == null) {
            INSTANCE =
                    Room.databaseBuilder(TaboolaApplication.applicationContext, ArticlesDatabase.class, "ArticlesDB.db").allowMainThreadQueries().build();

        }
        return INSTANCE;
    }
}
