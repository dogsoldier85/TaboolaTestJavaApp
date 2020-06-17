package com.slava.taboolatestjava.network;

import com.slava.taboolatestjava.entities.Result;
import com.slava.taboolatestjava.network.entities.ArticleServerModel;

import java.util.List;

public interface INetworkHandler {
    Result<List<ArticleServerModel>> getData();

}
