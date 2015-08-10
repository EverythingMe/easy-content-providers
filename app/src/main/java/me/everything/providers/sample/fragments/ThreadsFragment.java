package me.everything.providers.sample.fragments;

import android.widget.TextView;

import me.everything.providers.android.telephony.*;
import me.everything.providers.core.Data;
import me.everything.providers.sample.base.GetCursorTask;
import me.everything.providers.sample.base.RecycleViewCursorFragment;

/**
 * Created by sromku
 */
public class ThreadsFragment extends RecycleViewCursorFragment<me.everything.providers.android.telephony.Thread> {

    @Override
    protected String getTitle() {
        return "Threads";
    }

    @Override
    protected void bindEntity(me.everything.providers.android.telephony.Thread thread, TextView title, TextView details) {
        title.setText(thread.snippet);
        details.setText("Num of messages: " + thread.messageCount);
    }

    @Override
    protected GetCursorTask.DataFetcher<me.everything.providers.android.telephony.Thread> getFetcher() {
        return new GetCursorTask.DataFetcher<me.everything.providers.android.telephony.Thread>() {
            @Override
            public Data<me.everything.providers.android.telephony.Thread> getData() {
                TelephonyProvider telephonyProvider = new TelephonyProvider(getApplicationContext());
                return telephonyProvider.getThreads();
            }
        };
    }


}
