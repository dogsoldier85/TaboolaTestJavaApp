package com.slava.taboolatestjava.persistence;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface ArticlesDao {

    @Query("SELECT * FROM ARTICLES")
    List<ArticleDBEntity> loadAllArticles();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertArticleItem(ArticleDBEntity article);

    @Query("DELETE FROM ARTICLES")
    void deleteAllArticles();
}

