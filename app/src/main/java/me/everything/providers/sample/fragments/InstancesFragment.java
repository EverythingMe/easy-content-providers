package me.everything.providers.sample.fragments;

import android.os.Bundle;
import android.widget.TextView;

import me.everything.providers.android.calendar.CalendarProvider;
import me.everything.providers.android.calendar.Instance;
import me.everything.providers.core.Data;
import me.everything.providers.sample.base.GetCursorTask;
import me.everything.providers.sample.base.RecycleViewCursorFragment;

/**
 * Created by sromku
 */
public class InstancesFragment extends RecycleViewCursorFragment<Instance> {

    private static final String ARG_EVENT_ID = "arg_event_id";

    private long mEventId;

    public static InstancesFragment newInstance(long eventId) {
        InstancesFragment fragment = new InstancesFragment();
        Bundle bundle = new Bundle();
        bundle.putLong(ARG_EVENT_ID, eventId);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mEventId = getArguments().getLong(ARG_EVENT_ID);
    }

    @Override
    protected String getTitle() {
        return "Instances";
    }

    @Override
    protected void bindEntity(Instance instance, TextView title, TextView details) {
        title.setText("Id: " + instance.id);
        details.setText("Event Id: " + instance.eventId);
    }

    @Override
    protected GetCursorTask.DataFetcher<Instance> getFetcher() {
        return new GetCursorTask.DataFetcher<Instance>() {
            @Override
            public Data<Instance> getData() {
                long begin = getYearAgo();
                long end = getYearAhead();
                CalendarProvider calendarProvider = new CalendarProvider(getApplicationContext());
                return calendarProvider.getInstances(mEventId, begin, end);
            }
        };
    }

    private long getYearAgo() {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.add(java.util.Calendar.YEAR, -10); // just to be sure Google (Alphabet) was already exist to store our info.
        return calendar.getTimeInMillis();
    }

    private long getYearAhead() {
        java.util.Calendar calendar = java.util.Calendar.getInstance();
        calendar.add(java.util.Calendar.YEAR, 1);
        return calendar.getTimeInMillis();
    }

}
