package com.slava.taboolatestjava.entities;

import androidx.annotation.Nullable;

public class Result<T> {

    private T data;
    private Exception exception;

    public Result(T data, Exception exception) {
        this.data = data;
        this.exception = exception;
    }

    public T getData() {
        return data;
    }

    public Exception getException() {
        return exception;
    }


    @Override
    public boolean equals(@Nullable Object obj) {
        if (obj instanceof Result<?>) {
            return ((Result<?>) obj).getData().equals(data) && ((Result<?>) obj).getException() == exception;
        }
        return false;
    }
}