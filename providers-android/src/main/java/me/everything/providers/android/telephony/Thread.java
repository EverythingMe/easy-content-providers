package me.everything.providers.android.telephony;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.provider.BaseColumns;
import android.provider.Telephony.Threads;
import android.provider.Telephony.ThreadsColumns;

import me.everything.providers.core.Entity;
import me.everything.providers.core.EnumInt;
import me.everything.providers.core.FieldMapping;
import me.everything.providers.core.IgnoreMapping;

@TargetApi(Build.VERSION_CODES.KITKAT)
public class Thread extends Entity {

	@IgnoreMapping
	public static Uri uri = Threads.CONTENT_URI;

	@FieldMapping(columnName = BaseColumns._ID, physicalType = FieldMapping.PhysicalType.Long)
	public long id;

	@FieldMapping(columnName = ThreadsColumns.DATE, physicalType = FieldMapping.PhysicalType.Long)
	public long createdDate;

	@FieldMapping(columnName = ThreadsColumns.ERROR, physicalType = FieldMapping.PhysicalType.Int)
	@IgnoreMapping
	public int error;

	@FieldMapping(columnName = ThreadsColumns.HAS_ATTACHMENT, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.Boolean)
	@IgnoreMapping
	public boolean hasAttachment;

	@FieldMapping(columnName = ThreadsColumns.MESSAGE_COUNT, physicalType = FieldMapping.PhysicalType.Int)
	@IgnoreMapping
	public int messageCount;

	@FieldMapping(columnName = ThreadsColumns.READ, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.Boolean)
	public boolean read;

	@FieldMapping(columnName = ThreadsColumns.RECIPIENT_IDS, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.Array)
	@IgnoreMapping
	public String[] recipientIds;

	@FieldMapping(columnName = ThreadsColumns.SNIPPET, physicalType = FieldMapping.PhysicalType.String)
	@IgnoreMapping
	public String snippet;

	@FieldMapping(columnName = ThreadsColumns.SNIPPET_CHARSET, physicalType = FieldMapping.PhysicalType.Int)
	@IgnoreMapping
	public int snippetCharset;

	@FieldMapping(columnName = ThreadsColumns.TYPE, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.EnumInt)
	public ThreadType type;

	public static enum ThreadType implements EnumInt {
		COMMON(Threads.COMMON_THREAD),
		BROADCAST(Threads.BROADCAST_THREAD);

		int val;

		private ThreadType(int val) {
			this.val = val;
		}

		public static ThreadType fromVal(int val) {
			for (ThreadType threadType : values()) {
				if (threadType.val == val) {
					return threadType;
				}
			}
			return null;
		}
	}
}
