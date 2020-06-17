package com.slava.taboolatestjava.articles.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;

import com.slava.taboolatestjava.R;
import com.slava.taboolatestjava.articles.entities.IBaseDataModel;

import java.util.ArrayList;
import java.util.List;

public class ArticlesAdapter extends RecyclerView.Adapter<BaseViewHolder> {

    private ArrayList<IBaseDataModel> mData = new ArrayList<>();

    enum ArticleViewType {
        ARTICLE(0), TABOOLA_FEED(1), TABOOLA_WIDGET(2);

        private int mValue;

        ArticleViewType(int value) {
            mValue = value;
        }

        public int getValue() {
            return mValue;
        }

        public static ArticleViewType getType(int value) {
            if (value == TABOOLA_WIDGET.getValue())
                return TABOOLA_WIDGET;
            else if (value == TABOOLA_FEED.getValue())
                return TABOOLA_FEED;
            else return ARTICLE;
        }
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view;
        BaseViewHolder viewHolder;
        ArticleViewType status = ArticleViewType.getType(viewType);
        switch (status) {
            case TABOOLA_FEED:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.taboola_widget_feed_view, parent, false);
                viewHolder = new TaboolaFeedViewHolder(view);
                break;
            case TABOOLA_WIDGET:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.taboola_widget_view, parent, false);
                viewHolder = new TaboolaWidgetViewHolder(view);
                break;
            default:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.article_item_view, parent, false);
                viewHolder = new ArticleViewHolder(view);

        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder holder, int position) {
        switch (ArticleViewType.getType(getItemViewType(position))) {
            case ARTICLE:
                ((ArticleViewHolder) holder).bind(mData.get(position));
                break;
            case TABOOLA_WIDGET:
                ((TaboolaWidgetViewHolder) holder).bind();
                break;
            case TABOOLA_FEED:
                ((TaboolaFeedViewHolder) holder).bind();
                break;
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        switch (mData.get(position).getType()) {
            case ARTICLE:
                return ArticleViewType.ARTICLE.getValue();
            case TABOOLA_WIDGET:
                return ArticleViewType.TABOOLA_WIDGET.getValue();
            case TABOOLA_FEED:
                return ArticleViewType.TABOOLA_FEED.getValue();
        }
        return ArticleViewType.ARTICLE.getValue();
    }

    public void loadItems(List<IBaseDataModel> newData) {
        ArticlesDiffUtilCallBack diffCallback = new ArticlesDiffUtilCallBack(mData, newData);
        DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(diffCallback);
        mData.clear();
        mData.addAll(newData);
        diffResult.dispatchUpdatesTo(this);
    }
}
