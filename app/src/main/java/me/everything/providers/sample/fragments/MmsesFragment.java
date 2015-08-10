package me.everything.providers.sample.fragments;

import android.widget.TextView;

import me.everything.providers.android.telephony.Mms;
import me.everything.providers.android.telephony.TelephonyProvider;
import me.everything.providers.core.Data;
import me.everything.providers.sample.base.GetEntitiesTask;
import me.everything.providers.sample.base.RecycleViewListFragment;

public class MmsesFragment extends RecycleViewListFragment<Mms> {

    @Override
    protected String getTitle() {
        return "MMS(es)";
    }

    @Override
    protected void bindEntity(Mms mms, TextView title, TextView details) {
        title.setText(mms.messageId);
        details.setText(mms.status + "");
    }

    @Override
    protected GetEntitiesTask.DataFetcher<Mms> getFetcher() {
        return new GetEntitiesTask.DataFetcher<Mms>() {
            @Override
            public Data<Mms> getData() {
                TelephonyProvider provider = new TelephonyProvider(getApplicationContext());
                return provider.getMms(TelephonyProvider.Filter.ALL);
            }
        };
    }

}
