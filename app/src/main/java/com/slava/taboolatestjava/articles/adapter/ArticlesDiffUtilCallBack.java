package com.slava.taboolatestjava.articles.adapter;

import androidx.recyclerview.widget.DiffUtil;

import com.slava.taboolatestjava.articles.entities.IBaseDataModel;

import java.util.List;

public class ArticlesDiffUtilCallBack extends DiffUtil.Callback {

    private List<IBaseDataModel> mOldList;
    private List<IBaseDataModel> mNewList;

    public ArticlesDiffUtilCallBack(List<IBaseDataModel> oldList,
                                    List<IBaseDataModel> newList) {
        mOldList = oldList;
        mNewList = newList;
    }

    @Override
    public int getOldListSize() {
        return mOldList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldList.get(oldItemPosition).getType() == mNewList.get(newItemPosition).getType();
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        if (!mOldList.get(oldItemPosition).getName().equals(mNewList.get(newItemPosition).getName())) {
            return false;
        } else if (!mOldList.get(oldItemPosition).getDescription().equals(mNewList.get(newItemPosition).getDescription())) {
            return false;
        } else return mOldList.get(oldItemPosition).getThumbnail().equals(mNewList.get(newItemPosition).getThumbnail());
    }
}
