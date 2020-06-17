package com.slava.taboolatestjava.articles.adapter;

import android.view.View;

import androidx.annotation.NonNull;

import com.slava.taboolatestjava.R;
import com.taboola.android.TaboolaWidget;

public class TaboolaWidgetViewHolder extends BaseViewHolder {

    private TaboolaWidget taboolaWidget;

    public TaboolaWidgetViewHolder(@NonNull View itemView) {
        super(itemView);
        taboolaWidget = itemView.findViewById(R.id.taboola_view);
    }

    public void bind() {
        taboolaWidget.fetchContent();
    }
}
