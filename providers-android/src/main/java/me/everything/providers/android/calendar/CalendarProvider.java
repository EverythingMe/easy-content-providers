package me.everything.providers.android.calendar;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.net.Uri;
import android.os.Build;
import android.provider.CalendarContract.Attendees;
import android.provider.CalendarContract.Calendars;
import android.provider.CalendarContract.Events;
import android.provider.CalendarContract.Instances;
import android.provider.CalendarContract.Reminders;

import me.everything.providers.core.AbstractProvider;
import me.everything.providers.core.Data;

/**
 * Api for CRUD operations on calendar. It is important to make calendar calls
 * not on UI thread. <br>
 * <br>
 * <b>Relevant permissions:</b>
 *
 * <pre>
 * {@code <}uses-permission android:name="android.permission.READ_CALENDAR"/{@code >}
 * {@code <}uses-permission android:name="android.permission.WRITE_CALENDAR"/{@code >}
 * </pre>
 *
 * @author sromku
 */
@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class CalendarProvider extends AbstractProvider {

    public CalendarProvider(Context context) {
        super(context);
    }

    /**
     * Get calendars.
     *
     * @return List of calendars
     */
    public Data<Calendar> getCalendars() {
        Data<Calendar> calendars = getContentTableData(Calendar.uri, Calendar.class);
        return calendars;
    }

    /**
     * Get specific calendar.
     *
     * @param calendarId
     */
    public Calendar getCalendar(long calendarId) {
        String selection = "(" + Calendars._ID + " = ?)";
        String[] selectionArgs = new String[] { String.valueOf(calendarId) };
        Calendar calendar = getContentRowData(Calendar.uri, selection, selectionArgs, null, Calendar.class);
        return calendar;
    }

    /**
     * Get events of specific calendar.
     *
     * @param calendarId
     * @return List of events which relates to given calendar.
     */
    public Data<Event> getEvents(long calendarId) {
        String selection = "(" + Events.CALENDAR_ID + " = ?)";
        String[] selectionArgs = new String[] { String.valueOf(calendarId) };
        Data<Event> events = getContentTableData(Event.uri, selection, selectionArgs, null, Event.class);
        return events;
    }

    /**
     * Get events of specific calendar.
     *
     * @param eventId
     * @return List of events which relates to given calendar.
     */
    public Event getEvent(long eventId) {
        String selection = "(" + Events._ID + " = ?)";
        String[] selectionArgs = new String[] { String.valueOf(eventId) };
        Event event = getContentRowData(Event.uri, selection, selectionArgs, null, Event.class);
        return event;
    }

    /**
     * Get instances from date to date.
     *
     * @param begin - start time, end - end time
     * @return List of instances.
     */
    public Data<Instance> getInstances(long begin, long end) {
        Uri.Builder builder = Instance.uri.buildUpon();
        ContentUris.appendId(builder, begin);
        ContentUris.appendId(builder, end);
        Uri uri = builder.build();
        Data<Instance> instances = getContentTableData(uri, Instance.class);
        return instances;
    }

    /**
     * Get instances of given event from date to date.
     *
     * @param eventId
     * @return List of instances.
     */
    public Data<Instance> getInstances(long eventId, long begin, long end) {
        String selection = "(" + Instances.EVENT_ID + " = ?)";
        String[] selectionArgs = new String[] { String.valueOf(eventId) };
        Uri.Builder builder = Instance.uri.buildUpon();
        ContentUris.appendId(builder, begin);
        ContentUris.appendId(builder, end);
        Uri uri = builder.build();
        Data<Instance> instances = getContentTableData(uri, selection, selectionArgs, null, Instance.class);
        return instances;
    }

    /**
     * Get attendees of passed event.
     *
     * @param eventId
     * @return List of attendees.
     */
    public Data<Attendee> getAttendees(long eventId) {
        String selection = "(" + Attendees.EVENT_ID + "=?)";
        String[] selectionArgs = new String[] { Long.toString(eventId) };
        Data<Attendee> attendees = getContentTableData(Attendee.uri, selection, selectionArgs, null, Attendee.class);
        return attendees;
    }

    /**
     * Get reminders of given event.
     *
     * @param eventId
     * @return List of reminders.
     */
    public Data<Reminder> getReminders(long eventId) {
        String selection = "(" + Reminders.EVENT_ID + "=?)";
        String[] selectionArgs = new String[] { Long.toString(eventId) };
        Data<Reminder> reminders = getContentTableData(Reminder.uri, selection, selectionArgs, null, Reminder.class);
        return reminders;
    }

    /**
     * Update calendar.
     *
     * @param calendar
     * @return number of updated rows
     */
    public int update(Calendar calendar) {
        return updateTableRow(Calendar.uri, calendar);
    }

    /**
     * Update event
     *
     * @param event
     * @return number of updated rows
     */
    public int update(Event event) {
        return updateTableRow(Event.uri, event);
    }

    /**
     * Update instance
     *
     * @param instance
     * @return
     */
    public int update(Instance instance) {
        return updateTableRow(Instance.uri, instance);
    }

    /**
     * Update reminder
     *
     * @param reminder
     * @return
     */
    public int update(Reminder reminder) {
        return updateTableRow(Reminder.uri, reminder);
    }

    /**
     * Update attendee
     *
     * @param attendee
     * @return
     */
    public int update(Attendee attendee) {
        return updateTableRow(Attendee.uri, attendee);
    }

}
