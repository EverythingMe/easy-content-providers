package me.everything.providers.sample.fragments;

import android.provider.CallLog;
import android.widget.TextView;

import me.everything.providers.android.calllog.Call;
import me.everything.providers.android.calllog.CallsProvider;
import me.everything.providers.core.Data;
import me.everything.providers.sample.GetCursorTask;


/**
 * Created by sromku.
 */
public class CallsFragment extends RecycleViewCursorFragment<Call> {

    private String[] mColumns = new String[] {
            CallLog.Calls.NUMBER,
            CallLog.Calls.TYPE
    };

    @Override
    protected String getTitle() {
        return "Calls";
    }

    @Override
    protected void bindEntity(Call call, TextView title, TextView details) {
        title.setText(call.number);
        details.setText(call.type.toString());
    }

    @Override
    protected String[] getProjectionColumns() {
        return mColumns;
    }

    @Override
    protected GetCursorTask.DataFetcher<Call> getFetcher() {
        return new GetCursorTask.DataFetcher<Call>() {
            @Override
            public Data<Call> getData() {
                CallsProvider callsProvider = new CallsProvider(getApplicationContext());
                return callsProvider.getCalls();
            }
        };
    }


}
