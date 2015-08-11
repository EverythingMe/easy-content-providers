package me.everything.providers.android.calendar;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.provider.BaseColumns;
import android.provider.CalendarContract.Instances;

import me.everything.providers.core.Entity;
import me.everything.providers.core.FieldMapping;
import me.everything.providers.core.IgnoreMapping;

/**
 * Instance entity
 * 
 * @author sromku
 * // @see http
 *      ://developer.android.com/reference/android/provider/CalendarContract.
 *      Instances.html
 */
@TargetApi(Build.VERSION_CODES.ICE_CREAM_SANDWICH)
public class Instance extends Entity {

	@IgnoreMapping
	public static Uri uri = Instances.CONTENT_URI;

	@FieldMapping(columnName = BaseColumns._ID, physicalType = FieldMapping.PhysicalType.Long)
	public long id;
	
	@FieldMapping(columnName = Instances.EVENT_ID, physicalType = FieldMapping.PhysicalType.Long)
	public long eventId;

	@FieldMapping(columnName = Instances.BEGIN, physicalType = FieldMapping.PhysicalType.Long)
	public long begin;

	@FieldMapping(columnName = Instances.END, physicalType = FieldMapping.PhysicalType.Long)
	public long end;

	@FieldMapping(columnName = Instances.START_DAY, physicalType = FieldMapping.PhysicalType.Int)
	public long startDay;

	@FieldMapping(columnName = Instances.START_MINUTE, physicalType = FieldMapping.PhysicalType.Int)
	public long startMinute;

	@FieldMapping(columnName = Instances.END_DAY, physicalType = FieldMapping.PhysicalType.Int)
	public long endDay;

	@FieldMapping(columnName = Instances.END_MINUTE, physicalType = FieldMapping.PhysicalType.Int)
	public long endMinute;

}
