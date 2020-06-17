package com.slava.taboolatestjava.repositories;

import com.slava.taboolatestjava.entities.Result;
import com.slava.taboolatestjava.persistence.ArticleDBEntity;

import java.util.List;

import io.reactivex.Observable;

public interface IArticlesRepository {
    Observable<Result<List<ArticleDBEntity>>> getData();
}
