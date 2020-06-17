package com.slava.taboolatestjava.common;

import androidx.lifecycle.ViewModel;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public abstract class BaseViewModel extends ViewModel {

    private CompositeDisposable disposeOnClear = new CompositeDisposable();

    @Override
    protected void onCleared() {
        super.onCleared();
        disposeOnClear.clear();
    }

    protected void addDisposeOnClear(@NonNull Disposable disposable) {
        disposeOnClear.add(disposable);
    }
}
