package com.slava.taboolatestjava;

import com.slava.taboolatestjava.articles.entities.BaseDataModel;
import com.slava.taboolatestjava.articles.entities.DataType;
import com.slava.taboolatestjava.articles.entities.IBaseDataModel;
import com.slava.taboolatestjava.entities.Result;
import com.slava.taboolatestjava.network.INetworkHandler;
import com.slava.taboolatestjava.network.entities.ArticleServerModel;
import com.slava.taboolatestjava.persistence.ArticleDBEntity;
import com.slava.taboolatestjava.persistence.ArticlesDao;
import com.slava.taboolatestjava.repositories.ArticlesRepository;
import com.slava.taboolatestjava.taboola.TaboolaMediaAssembler;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.TestObserver;

import static org.junit.Assert.*;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {


    @Test
    public void testRepositoryFetchingFromRemoteApi() {
        ArticleDBEntity newsDBItem = new ArticleDBEntity(
                "From DB",
                "description",
                "imageUrl"
        );
        newsDBItem.id = 1;
        ArticleServerModel articleServerModel =
                new ArticleServerModel(
                        "From DB",
                        "description",
                        "imageUrl"
                );
        List<ArticleDBEntity> emptyArray = new ArrayList<>();
        List<ArticleServerModel> emptyNetworkArray = new ArrayList<>();
        emptyNetworkArray.add(articleServerModel);

        List<ArticleDBEntity> dbArray = new ArrayList<>();
        dbArray.add(newsDBItem);

        Result<List<ArticleServerModel>> firstValue = new Result<>(emptyNetworkArray, null);
        Result<List<ArticleDBEntity>> firstTestValue = new Result<>(emptyArray, null);
        Result<List<ArticleDBEntity>> secondValue = new Result<>(dbArray, null);

        MockNetworkHandler mockNetworkHandler = new MockNetworkHandler(firstValue);
        ArticlesDao mockNewsDao = new MockDaoHandler(secondValue, true);
        ArticlesRepository newsRepository = new ArticlesRepository(mockNetworkHandler, mockNewsDao);
        TestObserver<Result<List<ArticleDBEntity>>> testObserver = new TestObserver<>();

        newsRepository.getData().subscribe(testObserver);

        ArrayList<Result<List<ArticleDBEntity>>> streamValues = new ArrayList<>();
        streamValues.add(firstTestValue);
        streamValues.add(secondValue);
        testObserver.assertNoErrors().assertValueSequence(streamValues);
    }


    @Test
    public void testRepositoryFetchingFromRemoteDB() {
        ArticleDBEntity newsDBItem = new ArticleDBEntity(
                "From DB",
                "description",
                "imageUrl"
        );
        newsDBItem.id = 1;

        List<ArticleServerModel> emptyNetworkArray = new ArrayList<>();
        List<ArticleDBEntity> dbArray = new ArrayList<>();
        dbArray.add(newsDBItem);

        Result<List<ArticleServerModel>> firstValue = new Result<>(emptyNetworkArray, null);
        Result<List<ArticleDBEntity>> secondValue = new Result<>(dbArray, null);

        INetworkHandler mockNetworkHandler = new MockNetworkHandler(firstValue);
        ArticlesDao mockNewsDao = new MockDaoHandler(secondValue, false);
        ArticlesRepository newsRepository = new ArticlesRepository(mockNetworkHandler, mockNewsDao);
        TestObserver<Result<List<ArticleDBEntity>>> testObserver = new TestObserver<>();

        newsRepository.getData().subscribe(testObserver);

        ArrayList<Result<List<ArticleDBEntity>>> streamValues = new ArrayList<>();
        streamValues.add(secondValue);
        streamValues.add(secondValue);
        testObserver.assertNoErrors().assertValueSequence(streamValues);
    }

    @Test
    public void taboolaMediaAssemblerTest() {
        TaboolaMediaAssembler taboolaMediaAssembler = new TaboolaMediaAssembler();
        ArrayList<IBaseDataModel> data = new ArrayList<>();
        data.add(new BaseDataModel(DataType.ARTICLE));
        data.add(new BaseDataModel(DataType.ARTICLE));
        data.add(new BaseDataModel(DataType.ARTICLE));
        taboolaMediaAssembler.addTaboolaWidgets(data);
        assertEquals(DataType.ARTICLE, data.get(0).getType());
        assertEquals(DataType.ARTICLE, data.get(1).getType());
        assertEquals(DataType.TABOOLA_WIDGET, data.get(2).getType());
        assertEquals(DataType.ARTICLE, data.get(3).getType());
        assertEquals(DataType.TABOOLA_FEED, data.get(4).getType());
    }

    @Test
    public void taboolaMediaAssemblerWithEmptyArticlesListTest() {
        TaboolaMediaAssembler taboolaMediaAssembler = new TaboolaMediaAssembler();
        ArrayList<IBaseDataModel> data = new ArrayList<>();
        taboolaMediaAssembler.addTaboolaWidgets(data);
        assertEquals(DataType.TABOOLA_FEED, data.get(0).getType());
    }

}