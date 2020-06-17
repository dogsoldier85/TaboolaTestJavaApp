//package com.slava.taboolatestjava.persistence;
//
//import androidx.room.Room;
//
//import com.slava.taboolatestjava.TaboolaApplication;
//
//public class ArticlesDataBaseImpl {
//
//    private static ArticlesDataBaseImpl mInstance;
//    private ArticlesDatabase articlesDatabase;
//
//
//    public ArticlesDataBaseImpl() {
//        articlesDatabase = Room.databaseBuilder(TaboolaApplication.applicationContext, ArticlesDatabase.class, "ArticlesDB.db").build();
//    }
//
//    public static synchronized ArticlesDataBaseImpl getInstance() {
//        if (mInstance == null) {
//            mInstance = new ArticlesDataBaseImpl();
//        }
//        return mInstance;
//    }
//
//    public ArticlesDatabase getAppDatabase() {
//        return articlesDatabase;
//    }
//
//    public ArticlesDatabase getArticlesDatabase() {
//        return articlesDatabase;
//    }
//}
