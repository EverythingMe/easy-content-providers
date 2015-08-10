package me.everything.providers.sample.base;

import android.os.AsyncTask;

import me.everything.providers.core.Data;
import me.everything.providers.core.Entity;

/**
 * Created by sromku.
 */
public class GetCursorTask<T extends Entity> extends AsyncTask<Void, Void, Data<T>> {

    private TaskListener mTaskListener;
    private DataFetcher<T> mFetcher;

    @Override
    protected Data<T> doInBackground(Void... params) {
        Data<T> data = mFetcher.getData();
        return data;
    }

    @Override
    protected void onPostExecute(Data<T> entities) {
        mTaskListener.onComplete(entities);
    }

    public interface DataFetcher<T extends Entity> {
        Data<T> getData();
    }

    public interface TaskListener<T extends Entity> {
        void onComplete(Data<T> entities);
    }

    public static class Builder<T extends Entity> {

        private DataFetcher<T> mFetcher;
        private TaskListener<T> mCallback;

        public Builder setFetcher(DataFetcher<T> fetcher) {
            mFetcher = fetcher;
            return this;
        }

        public Builder setCallback(TaskListener<T> callback) {
            mCallback = callback;
            return this;
        }

        public GetCursorTask<T> build() {
            GetCursorTask<T> getEntitiesTask = new GetCursorTask();
            getEntitiesTask.mTaskListener = mCallback;
            getEntitiesTask.mFetcher = mFetcher;
            return getEntitiesTask;
        }
    }
}
