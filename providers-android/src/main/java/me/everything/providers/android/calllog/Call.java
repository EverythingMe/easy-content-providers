package me.everything.providers.android.calllog;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.provider.BaseColumns;
import android.provider.CallLog.Calls;

import me.everything.providers.core.Entity;
import me.everything.providers.core.EnumInt;
import me.everything.providers.core.FieldMapping;
import me.everything.providers.core.IgnoreMapping;

@TargetApi(Build.VERSION_CODES.KITKAT)
public class Call extends Entity {

	@IgnoreMapping
	public static Uri uri = Calls.CONTENT_URI;

	@FieldMapping(columnName = BaseColumns._ID, physicalType = FieldMapping.PhysicalType.Long)
	public long id;

	@FieldMapping(columnName = Calls.CACHED_NAME, physicalType = FieldMapping.PhysicalType.String)
	public String name;

	@FieldMapping(columnName = Calls.DATE, physicalType = FieldMapping.PhysicalType.Long)
	public long callDate;

	@FieldMapping(columnName = Calls.DURATION, physicalType = FieldMapping.PhysicalType.Long)
	public long duration;

	@FieldMapping(columnName = Calls.IS_READ, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.Boolean)
	public boolean isRead;

	@FieldMapping(columnName = Calls.NUMBER, physicalType = FieldMapping.PhysicalType.String)
	public String number;

	@FieldMapping(columnName = Calls.TYPE, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.EnumInt)
	public CallType type;

	public enum CallType implements EnumInt {
		INCOMING(Calls.INCOMING_TYPE),
		OUTGOING(Calls.OUTGOING_TYPE),
		MISSED(Calls.MISSED_TYPE);

		int val;

		CallType(int val) {
			this.val = val;
		}

		public static CallType fromVal(int val) {
			for (CallType messageStatus : values()) {
				if (messageStatus.val == val) {
					return messageStatus;
				}
			}
			return null;
		}
	}
}
