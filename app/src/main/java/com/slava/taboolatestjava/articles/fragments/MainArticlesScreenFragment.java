package com.slava.taboolatestjava.articles.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.SimpleItemAnimator;

import com.slava.taboolatestjava.R;
import com.slava.taboolatestjava.articles.adapter.ArticlesAdapter;
import com.slava.taboolatestjava.articles.decoration.ItemVerticalMarginsDecoration;
import com.slava.taboolatestjava.articles.entities.IBaseDataModel;
import com.slava.taboolatestjava.articles.viewmodel.MainArticlesScreenViewModel;

import java.util.List;
import java.util.Objects;

public class MainArticlesScreenFragment extends Fragment {
    private ArticlesAdapter mNewsAdapter = new ArticlesAdapter();
    private TextView mErrorMessage;

    public MainArticlesScreenViewModel mViewModel;


    public static MainArticlesScreenFragment newInstance() {
        return new MainArticlesScreenFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.main_articles_screen_fragment, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        mViewModel.fetchArticles();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initNewsList(view);
        mErrorMessage = view.findViewById(R.id.error_label);
        initViewModel();
    }

    private void initNewsList(View view) {
        RecyclerView newsList = view.findViewById(R.id.news_list);
        newsList.setLayoutManager(new LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false));
        ((SimpleItemAnimator) Objects.requireNonNull(newsList.getItemAnimator())).setSupportsChangeAnimations(false);
        newsList.addItemDecoration(new ItemVerticalMarginsDecoration());
        newsList.setAdapter(mNewsAdapter);
        newsList.setOverScrollMode(View.OVER_SCROLL_NEVER);
    }

    private void initViewModel() {
        mViewModel = new ViewModelProvider(this).get(MainArticlesScreenViewModel.class);
        mViewModel.newsLiveData().observe(getViewLifecycleOwner(), new Observer<List<IBaseDataModel>>() {
            @Override
            public void onChanged(List<IBaseDataModel> baseDataModels) {
                mNewsAdapter.loadItems(baseDataModels);
            }
        });

        mViewModel.errorHandlingLiveData().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                mErrorMessage.setText(s);
                mErrorMessage.setVisibility(View.VISIBLE);
            }
        });
    }
}
