package me.everything.providers.sample.fragments;

import android.widget.TextView;

import me.everything.providers.android.calendar.Calendar;
import me.everything.providers.android.calendar.CalendarProvider;
import me.everything.providers.core.Data;
import me.everything.providers.sample.GetEntitiesTask;

public class CalendarsFragment extends RecycleViewListFragment<Calendar> {

    @Override
    protected String getTitle() {
        return "Calendar";
    }

    @Override
    protected void bindEntity(Calendar calendar, TextView title, TextView details) {
        title.setText(calendar.displayName);
        details.setText(calendar.ownerAccount);
    }

    @Override
    protected GetEntitiesTask.DataFetcher<Calendar> getFetcher() {
        return new GetEntitiesTask.DataFetcher<Calendar>() {
            @Override
            public Data<Calendar> getData() {
                CalendarProvider calendarProvider = new CalendarProvider(getApplicationContext());
                return calendarProvider.getCalendars();
            }
        };
    }

    @Override
    protected void onSelected(Calendar entity) {
        setFragment(EventsFragment.newInstance(entity.id));
    }

}
