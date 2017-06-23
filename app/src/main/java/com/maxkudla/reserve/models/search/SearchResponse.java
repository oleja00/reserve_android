package com.maxkudla.reserve.models.search;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Developer on 07.05.2017.
 */

public class SearchResponse {

    private boolean error;

    private Data data;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data {

        private long results;
        @SerializedName("query_id")
        private String queryId;

        public long getResults() {
            return results;
        }

        public void setResults(long results) {
            this.results = results;
        }

        public String getQueryId() {
            return queryId;
        }

        public void setQueryId(String queryId) {
            this.queryId = queryId;
        }

    }
}
