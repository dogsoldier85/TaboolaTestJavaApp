package com.slava.taboolatestjava.articles.decoration;

import android.graphics.Rect;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.slava.taboolatestjava.R;
import com.slava.taboolatestjava.TaboolaApplication;

public class ItemVerticalMarginsDecoration extends RecyclerView.ItemDecoration {

    private int padding =
            TaboolaApplication.applicationContext.getResources().getDimensionPixelSize(R.dimen.new_item_vertical_margins);

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);

        int position = parent.getChildAdapterPosition(view);
        if (position == 0) {
            outRect.top = padding;
        }
        outRect.bottom = padding;
    }
}
