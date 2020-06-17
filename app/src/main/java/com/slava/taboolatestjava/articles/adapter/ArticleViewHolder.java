package com.slava.taboolatestjava.articles.adapter;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.slava.taboolatestjava.R;
import com.slava.taboolatestjava.articles.entities.IBaseDataModel;

public class ArticleViewHolder extends BaseViewHolder {

    private TextView title;
    private TextView description;
    private ImageView image;

    public ArticleViewHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.title);
        description = itemView.findViewById(R.id.description);
        image = itemView.findViewById(R.id.image);
    }

    public void bind(IBaseDataModel articleDataModel) {
        title.setText(articleDataModel.getName());
        description.setText(articleDataModel.getDescription());

        Glide.with(image.getContext()).load(articleDataModel.getThumbnail())
                .transition(DrawableTransitionOptions.withCrossFade()).into(image);
    }
}
