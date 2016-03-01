package me.everything.providers.android.telephony;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.provider.BaseColumns;
import android.provider.Telephony.TextBasedSmsColumns;

import me.everything.providers.core.Entity;
import me.everything.providers.core.EnumInt;
import me.everything.providers.core.FieldMapping;
import me.everything.providers.core.IgnoreMapping;

@TargetApi(Build.VERSION_CODES.KITKAT)
public class Sms extends Entity {

	@IgnoreMapping
	public static Uri uri = android.provider.Telephony.Sms.CONTENT_URI;

	@IgnoreMapping
	public static Uri uriInbox = android.provider.Telephony.Sms.Inbox.CONTENT_URI;

	@IgnoreMapping
	public static Uri uriOutbox = android.provider.Telephony.Sms.Outbox.CONTENT_URI;

	@IgnoreMapping
	public static Uri uriSent = android.provider.Telephony.Sms.Sent.CONTENT_URI;

	@IgnoreMapping
	public static Uri uriDraft = android.provider.Telephony.Sms.Draft.CONTENT_URI;

	@FieldMapping(columnName = BaseColumns._ID, physicalType = FieldMapping.PhysicalType.Long)
	public long id;

	@FieldMapping(columnName = TextBasedSmsColumns.ADDRESS, physicalType = FieldMapping.PhysicalType.String)
	public String address;

	@FieldMapping(columnName = TextBasedSmsColumns.BODY, physicalType = FieldMapping.PhysicalType.String)
	public String body;

	@FieldMapping(columnName = TextBasedSmsColumns.DATE, physicalType = FieldMapping.PhysicalType.Long)
	public long receivedDate;

	@FieldMapping(columnName = TextBasedSmsColumns.DATE_SENT, physicalType = FieldMapping.PhysicalType.Long)
	public long sentDate;

	@FieldMapping(columnName = TextBasedSmsColumns.ERROR_CODE, physicalType = FieldMapping.PhysicalType.Int)
	public int errorCode;

	@FieldMapping(columnName = TextBasedSmsColumns.LOCKED, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.Boolean)
	public boolean locked;

	@FieldMapping(columnName = TextBasedSmsColumns.PERSON, physicalType = FieldMapping.PhysicalType.Int)
	public int person;

	@FieldMapping(columnName = TextBasedSmsColumns.PROTOCOL, physicalType = FieldMapping.PhysicalType.Int)
	public int protocol;

	@FieldMapping(columnName = TextBasedSmsColumns.READ, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.Boolean)
	public boolean read;

	@FieldMapping(columnName = TextBasedSmsColumns.SEEN, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.Boolean)
	public boolean seen;

	@FieldMapping(columnName = TextBasedSmsColumns.SERVICE_CENTER, physicalType = FieldMapping.PhysicalType.String)
	public String serviceCenter;

	@FieldMapping(columnName = TextBasedSmsColumns.STATUS, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.EnumInt)
	public MessageStatus status;

	@FieldMapping(columnName = TextBasedSmsColumns.SUBJECT, physicalType = FieldMapping.PhysicalType.String)
	public String subject;

	@FieldMapping(columnName = TextBasedSmsColumns.THREAD_ID, physicalType = FieldMapping.PhysicalType.Int)
	public int threadId;

	@FieldMapping(columnName = TextBasedSmsColumns.TYPE, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.EnumInt)
	public MessageType type;

	public static enum MessageType implements EnumInt {
		ALL(TextBasedSmsColumns.MESSAGE_TYPE_ALL),
		INBOX(TextBasedSmsColumns.MESSAGE_TYPE_INBOX),
		SENT(TextBasedSmsColumns.MESSAGE_TYPE_SENT),
		DRAFT(TextBasedSmsColumns.MESSAGE_TYPE_DRAFT),
		OUTBOX(TextBasedSmsColumns.MESSAGE_TYPE_OUTBOX),
		FAILED(TextBasedSmsColumns.MESSAGE_TYPE_FAILED),
		QUEUED(TextBasedSmsColumns.MESSAGE_TYPE_QUEUED);

		int val;

		private MessageType(int val) {
			this.val = val;
		}
			
		public static MessageType fromVal(int val) {
			for (MessageType messageType : values()) {
				if (messageType.val == val) {
					return messageType;
				}
			}
			return null;
		}
		public int getValue(){return val;}
	}

	public static enum MessageStatus implements EnumInt {
		NONE(TextBasedSmsColumns.STATUS_NONE),
		COMPLETE(TextBasedSmsColumns.STATUS_COMPLETE),
		PENDING(TextBasedSmsColumns.STATUS_PENDING),
		FAILED(TextBasedSmsColumns.STATUS_FAILED);

		int val;

		private MessageStatus(int val) {
			this.val = val;
		}

		public static MessageStatus fromVal(int val) {
			for (MessageStatus messageStatus : values()) {
				if (messageStatus.val == val) {
					return messageStatus;
				}
			}
			return null;
		}
		public int getValue(){return val;}
	}
}
