package com.slava.taboolatestjava.taboola;

import com.slava.taboolatestjava.articles.entities.BaseDataModel;
import com.slava.taboolatestjava.articles.entities.DataType;
import com.slava.taboolatestjava.articles.entities.IBaseDataModel;

import java.util.ArrayList;

import timber.log.Timber;

public class TaboolaMediaAssembler implements ITaboolaMediaAssembler {

    @Override
    public void addTaboolaWidgets(ArrayList<IBaseDataModel> data) {
        int TABOOLA_WIDGET_POSITION = 2;
        if (data.size() > TABOOLA_WIDGET_POSITION) {
            data.add(TABOOLA_WIDGET_POSITION, new BaseDataModel(DataType.TABOOLA_WIDGET));
            Timber.d("Successfully add Taboola widget at position ${TABOOLA_WIDGET_POSITION + 1}");
        } else {
            Timber.d("Failed to add Taboola widget at position ${TABOOLA_WIDGET_POSITION + 1}");
        }
        Timber.d("adding Taboola widget feed at the end of the list");
        data.add(new BaseDataModel(DataType.TABOOLA_FEED));
    }
}
