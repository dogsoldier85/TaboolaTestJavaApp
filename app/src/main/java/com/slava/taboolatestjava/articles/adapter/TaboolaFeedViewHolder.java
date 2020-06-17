package com.slava.taboolatestjava.articles.adapter;

import android.view.View;

import androidx.annotation.NonNull;

import com.slava.taboolatestjava.R;
import com.taboola.android.TaboolaWidget;

public class TaboolaFeedViewHolder extends BaseViewHolder {
    private TaboolaWidget taboolaWidget;

    public TaboolaFeedViewHolder(@NonNull View itemView) {
        super(itemView);
        taboolaWidget = itemView.findViewById(R.id.taboola_view);
    }

    public void bind() {
        taboolaWidget.fetchContent();
        taboolaWidget.setInterceptScroll(true);
    }
}
