package com.slava.taboolatestjava.di;

import com.slava.taboolatestjava.network.INetworkHandler;
import com.slava.taboolatestjava.network.NetworkHandler;
import com.slava.taboolatestjava.persistence.ArticlesDatabase;
import com.slava.taboolatestjava.repositories.ArticlesRepository;
import com.slava.taboolatestjava.repositories.IArticlesRepository;
import com.slava.taboolatestjava.taboola.ITaboolaMediaAssembler;
import com.slava.taboolatestjava.taboola.TaboolaMediaAssembler;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import dagger.hilt.InstallIn;
import dagger.hilt.android.components.ApplicationComponent;

@InstallIn(ApplicationComponent.class)
@Module
public abstract class MyApplicationModule {

    @Provides
    @Singleton
    public static INetworkHandler provideNetworkHandler() {
        return new NetworkHandler();
    }

    @Provides
    @Singleton
    public static IArticlesRepository provideArticlesRepository(INetworkHandler networkHandler) {
        return new ArticlesRepository(networkHandler, ArticlesDatabase.getAppDatabase().articlesDao());
    }

    @Provides
    public static ITaboolaMediaAssembler provideTaboolaMediaAssembler() {
        return new TaboolaMediaAssembler();
    }
}
