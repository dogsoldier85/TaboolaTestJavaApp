package com.slava.taboolatestjava;

import com.slava.taboolatestjava.entities.Result;
import com.slava.taboolatestjava.persistence.ArticleDBEntity;
import com.slava.taboolatestjava.persistence.ArticlesDao;

import java.util.ArrayList;
import java.util.List;

public class MockDaoHandler implements ArticlesDao {

    private Result<List<ArticleDBEntity>> mResult;
    private Boolean mSimulate;

    private ArticleDBEntity articleDBItem = new ArticleDBEntity(
            "From DB",
            "description",
            "thumbnail");
    private boolean isFirstFetchHappened = false;

    public MockDaoHandler(Result<List<ArticleDBEntity>> result, Boolean simulate) {
        mResult = result;
        mSimulate = simulate;
    }

    @Override
    public List<ArticleDBEntity> loadAllArticles() {
        if (mSimulate) {
            if (isFirstFetchHappened) {
                if (mResult.getData().isEmpty()) {
                    return new ArrayList<>();
                } else {
                    return mResult.getData();
                }
            } else {
                isFirstFetchHappened = true;
                return new ArrayList<>();
            }
        }
        return mResult.getData() == null ? new ArrayList<ArticleDBEntity>() : mResult.getData();
    }

    @Override
    public void insertArticleItem(ArticleDBEntity article) {

    }

    @Override
    public void deleteAllArticles() {

    }
}
