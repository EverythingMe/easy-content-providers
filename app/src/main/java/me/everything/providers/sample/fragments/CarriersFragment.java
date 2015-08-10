package me.everything.providers.sample.fragments;

import android.widget.TextView;

import me.everything.providers.android.telephony.Carrier;
import me.everything.providers.android.telephony.TelephonyProvider;
import me.everything.providers.core.Data;
import me.everything.providers.sample.base.GetCursorTask;
import me.everything.providers.sample.base.RecycleViewCursorFragment;

/**
 * Created by sromku
 */
public class CarriersFragment extends RecycleViewCursorFragment<Carrier> {

    @Override
    protected String getTitle() {
        return "Carriers";
    }

    @Override
    protected void bindEntity(Carrier carrier, TextView title, TextView details) {
        title.setText(carrier.name);
        details.setText(carrier.server);
    }

    @Override
    protected GetCursorTask.DataFetcher<Carrier> getFetcher() {
        return new GetCursorTask.DataFetcher<Carrier>() {
            @Override
            public Data<Carrier> getData() {
                TelephonyProvider telephonyProvider = new TelephonyProvider(getApplicationContext());
                return telephonyProvider.getCarriers();
            }
        };
    }


}
