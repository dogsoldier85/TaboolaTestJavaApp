package com.slava.taboolatestjava.repositories;

import com.slava.taboolatestjava.entities.Result;
import com.slava.taboolatestjava.network.INetworkHandler;
import com.slava.taboolatestjava.network.entities.ArticleServerModel;
import com.slava.taboolatestjava.persistence.ArticleDBEntity;
import com.slava.taboolatestjava.persistence.ArticlesDao;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import timber.log.Timber;

public class ArticlesRepository implements IArticlesRepository {

    private INetworkHandler mNetworkHandler;
    private ArticlesDao mArticlesDao;

    @Inject
    public ArticlesRepository(INetworkHandler networkHandler, ArticlesDao articlesDao) {
        mNetworkHandler = networkHandler;
        mArticlesDao = articlesDao;
    }

    @Override
    public Observable<Result<List<ArticleDBEntity>>> getData() {
        return Observable.create(new ObservableOnSubscribe<Result<List<ArticleDBEntity>>>() {
            @Override
            public void subscribe(ObservableEmitter<Result<List<ArticleDBEntity>>> emitter) {
                emitter.onNext(new Result<>(mArticlesDao.loadAllArticles(), null));
                Result<List<ArticleServerModel>> networkResponse = mNetworkHandler.getData();
                if (networkResponse.getException() == null) {
                    Timber.d("Clearing all ArticleDB");
                    mArticlesDao.deleteAllArticles();
                    Timber.d("Saving all articles from remote to DB");
                    if (networkResponse.getData() != null) {
                        for (ArticleServerModel item : networkResponse.getData()) {
                            Timber.d("Inserting article item to DB $item");
                            mArticlesDao.insertArticleItem(new ArticleDBEntity(item.getName(), item.getDescription(), item.getThumbnail()));
                        }
                    }
                    emitter.onNext(new Result<>(mArticlesDao.loadAllArticles(), null));
                }
            }
        });
    }
}
