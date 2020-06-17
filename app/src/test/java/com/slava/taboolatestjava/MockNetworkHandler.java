package com.slava.taboolatestjava;

import com.slava.taboolatestjava.entities.Result;
import com.slava.taboolatestjava.network.INetworkHandler;
import com.slava.taboolatestjava.network.entities.ArticleServerModel;

import java.util.List;

public class MockNetworkHandler implements INetworkHandler {

    private Result<List<ArticleServerModel>> mResult;

    public MockNetworkHandler(Result<List<ArticleServerModel>> mResult) {
        this.mResult = mResult;
    }

    @Override
    public Result<List<ArticleServerModel>> getData() {
        return mResult;
    }
}
