package me.everything.providers.sample.fragments;

import android.os.Bundle;
import android.widget.TextView;

import me.everything.providers.android.calendar.CalendarProvider;
import me.everything.providers.android.calendar.Reminder;
import me.everything.providers.core.Data;
import me.everything.providers.sample.base.GetCursorTask;
import me.everything.providers.sample.base.RecycleViewCursorFragment;

/**
 * Created by sromku
 */
public class RemindersFragment extends RecycleViewCursorFragment<Reminder> {

    private static final String ARG_EVENT_ID = "arg_event_id";

    private long mEventId;

    public static RemindersFragment newInstance(long eventId) {
        RemindersFragment fragment = new RemindersFragment();
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
        return "Reminders";
    }

    @Override
    protected void bindEntity(Reminder reminder, TextView title, TextView details) {
        title.setText("Method: " + reminder.method.name());
        details.setText("Minutes: " + reminder.minutes + "");
    }

    @Override
    protected GetCursorTask.DataFetcher<Reminder> getFetcher() {
        return new GetCursorTask.DataFetcher<Reminder>() {
            @Override
            public Data<Reminder> getData() {
                CalendarProvider calendarProvider = new CalendarProvider(getApplicationContext());
                return calendarProvider.getReminders(mEventId);
            }
        };
    }


}
