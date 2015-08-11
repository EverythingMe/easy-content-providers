package me.everything.providers.sample.fragments;

import android.os.Bundle;
import android.widget.TextView;

import me.everything.providers.android.calendar.Attendee;
import me.everything.providers.android.calendar.CalendarProvider;
import me.everything.providers.core.Data;
import me.everything.providers.sample.base.GetCursorTask;
import me.everything.providers.sample.base.RecycleViewCursorFragment;

/**
 * Created by sromku
 */
public class AttendeesFragment extends RecycleViewCursorFragment<Attendee> {

    private static final String ARG_EVENT_ID = "arg_event_id";

    private long mEventId;

    public static AttendeesFragment newInstance(long eventId) {
        AttendeesFragment fragment = new AttendeesFragment();
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
        return "Attendees";
    }

    @Override
    protected void bindEntity(Attendee attendee, TextView title, TextView details) {
        title.setText(attendee.name);
        details.setText(attendee.email);
    }

    @Override
    protected GetCursorTask.DataFetcher<Attendee> getFetcher() {
        return new GetCursorTask.DataFetcher<Attendee>() {
            @Override
            public Data<Attendee> getData() {
                CalendarProvider calendarProvider = new CalendarProvider(getApplicationContext());
                return calendarProvider.getAttendees(mEventId);
            }
        };
    }


}
