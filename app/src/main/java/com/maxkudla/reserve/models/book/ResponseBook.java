package com.maxkudla.reserve.models.book;

public class ResponseBook {

    private boolean error;
    private BookData data;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public BookData getData() {
        return data;
    }

    public void setData(BookData data) {
        this.data = data;
    }
}
