package com.slava.taboolatestjava.articles.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.slava.taboolatestjava.TaboolaApplication;
import com.slava.taboolatestjava.articles.entities.ArticleDataModel;
import com.slava.taboolatestjava.articles.entities.IBaseDataModel;
import com.slava.taboolatestjava.common.BaseViewModel;
import com.slava.taboolatestjava.entities.Result;
import com.slava.taboolatestjava.persistence.ArticleDBEntity;
import com.slava.taboolatestjava.repositories.IArticlesRepository;
import com.slava.taboolatestjava.taboola.ITaboolaMediaAssembler;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.EntryPoint;
import dagger.hilt.InstallIn;
import dagger.hilt.android.EntryPointAccessors;
import dagger.hilt.android.components.ApplicationComponent;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class MainArticlesScreenViewModel extends BaseViewModel {

    private MutableLiveData<List<IBaseDataModel>> articleLiveData = new MutableLiveData<>();
    private MutableLiveData<String> errorHandling = new MutableLiveData<>();

    private ITaboolaMediaAssembler mTaboolaMediaAssembler;

    @EntryPoint
    @InstallIn(ApplicationComponent.class)
    interface MainArticlesScreenViewModelEntryPoint {
        IArticlesRepository articlesRepository();

        ITaboolaMediaAssembler taboolaMediaAssembler();
    }


    public void fetchArticles() {
        final MainArticlesScreenViewModelEntryPoint hiltEntryPoint =
                EntryPointAccessors.fromApplication(TaboolaApplication.applicationContext, MainArticlesScreenViewModelEntryPoint.class);
        IArticlesRepository mArticlesRepository = hiltEntryPoint.articlesRepository();
        mArticlesRepository.getData()
                .observeOn(Schedulers.io())
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Result<List<ArticleDBEntity>>>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        addDisposeOnClear(d);
                    }

                    @Override
                    public void onNext(Result<List<ArticleDBEntity>> listResult) {
                        ArrayList<IBaseDataModel> uiData = new ArrayList<>();
                        if (!listResult.getData().isEmpty()) {
                            for (ArticleDBEntity datum : listResult.getData()) {
                                uiData.add(new ArticleDataModel(datum));
                            }
                        } else {
                            Timber.d("No data received from server");
                        }
                        mTaboolaMediaAssembler = hiltEntryPoint.taboolaMediaAssembler();
                        mTaboolaMediaAssembler.addTaboolaWidgets(uiData);
                        articleLiveData.postValue(uiData);
                    }

                    @Override
                    public void onError(Throwable e) {
                        errorHandling.postValue(e.getMessage());
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public LiveData<List<IBaseDataModel>> newsLiveData() {
        return articleLiveData;
    }

    public LiveData<String> errorHandlingLiveData() {
        return errorHandling;
    }
}
