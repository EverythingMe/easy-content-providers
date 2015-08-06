package me.everything.providers.android.telephony;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.provider.BaseColumns;
import android.provider.Telephony.BaseMmsColumns;

import me.everything.providers.core.Entity;
import me.everything.providers.core.EnumInt;
import me.everything.providers.core.FieldMapping;
import me.everything.providers.core.IgnoreMapping;

@TargetApi(Build.VERSION_CODES.KITKAT)
public class Mms extends Entity {

    @IgnoreMapping
    public static Uri uri = android.provider.Telephony.Mms.CONTENT_URI;

    @IgnoreMapping
    public static Uri uriInbox = android.provider.Telephony.Mms.Inbox.CONTENT_URI;

    @IgnoreMapping
    public static Uri uriOutbox = android.provider.Telephony.Mms.Outbox.CONTENT_URI;

    @IgnoreMapping
    public static Uri uriSent = android.provider.Telephony.Mms.Sent.CONTENT_URI;

    @IgnoreMapping
    public static Uri uriDraft = android.provider.Telephony.Mms.Draft.CONTENT_URI;

    @FieldMapping(columnName = BaseColumns._ID, physicalType = FieldMapping.PhysicalType.Long)
    public long id;

    @FieldMapping(columnName = BaseMmsColumns.CONTENT_CLASS, physicalType = FieldMapping.PhysicalType.Int)
    public int contentClass;

    @FieldMapping(columnName = BaseMmsColumns.CONTENT_LOCATION, physicalType = FieldMapping.PhysicalType.String)
    public String contentLocation;

    @FieldMapping(columnName = BaseMmsColumns.CONTENT_TYPE, physicalType = FieldMapping.PhysicalType.String)
    public String contentType;

    @FieldMapping(columnName = BaseMmsColumns.DATE, physicalType = FieldMapping.PhysicalType.Long)
    public long receivedDate;

    @FieldMapping(columnName = BaseMmsColumns.DATE_SENT, physicalType = FieldMapping.PhysicalType.Long)
    public long sentDate;

    @FieldMapping(columnName = BaseMmsColumns.DELIVERY_REPORT, physicalType = FieldMapping.PhysicalType.Int)
    public int deliveryReport;

    @FieldMapping(columnName = BaseMmsColumns.EXPIRY, physicalType = FieldMapping.PhysicalType.Long)
    public long expireDate;

    @FieldMapping(columnName = BaseMmsColumns.LOCKED, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.Boolean)
    public boolean locked;

    @FieldMapping(columnName = BaseMmsColumns.MESSAGE_BOX, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.EnumInt)
    public MessageType type;

    @FieldMapping(columnName = BaseMmsColumns.MESSAGE_CLASS, physicalType = FieldMapping.PhysicalType.String)
    public String messageClass;

    @FieldMapping(columnName = BaseMmsColumns.MESSAGE_ID, physicalType = FieldMapping.PhysicalType.String)
    public String messageId;

    @FieldMapping(columnName = BaseMmsColumns.MESSAGE_SIZE, physicalType = FieldMapping.PhysicalType.Int)
    public int messageSize;

    @FieldMapping(columnName = BaseMmsColumns.MESSAGE_TYPE, physicalType = FieldMapping.PhysicalType.Int)
    public int messageType;

    @FieldMapping(columnName = BaseMmsColumns.MMS_VERSION, physicalType = FieldMapping.PhysicalType.Int)
    public int mmsVersion;

    @FieldMapping(columnName = BaseMmsColumns.PRIORITY, physicalType = FieldMapping.PhysicalType.Int)
    public int priority;

    @FieldMapping(columnName = BaseMmsColumns.READ, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.Boolean)
    public boolean read;

    @FieldMapping(columnName = BaseMmsColumns.READ_REPORT, physicalType = FieldMapping.PhysicalType.Int)
    public int readReport;

    @FieldMapping(columnName = BaseMmsColumns.READ_STATUS, physicalType = FieldMapping.PhysicalType.Int)
    public int readStatus;

    @FieldMapping(columnName = BaseMmsColumns.REPORT_ALLOWED, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.Boolean)
    public boolean reportAllowed;

    @FieldMapping(columnName = BaseMmsColumns.RESPONSE_STATUS, physicalType = FieldMapping.PhysicalType.Int)
    public int responseStatus;

    @FieldMapping(columnName = BaseMmsColumns.RESPONSE_TEXT, physicalType = FieldMapping.PhysicalType.String)
    public String responseText;

    @FieldMapping(columnName = BaseMmsColumns.RETRIEVE_STATUS, physicalType = FieldMapping.PhysicalType.Int)
    public int retrieveStatus;

    @FieldMapping(columnName = BaseMmsColumns.RETRIEVE_TEXT, physicalType = FieldMapping.PhysicalType.String)
    public String retrieveText;

    @FieldMapping(columnName = BaseMmsColumns.RETRIEVE_TEXT_CHARSET, physicalType = FieldMapping.PhysicalType.Int)
    public int retrieveTextCharset;

    @FieldMapping(columnName = BaseMmsColumns.SEEN, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.Boolean)
    public boolean seen;

    @FieldMapping(columnName = BaseMmsColumns.STATUS, physicalType = FieldMapping.PhysicalType.Int)
    public int status;

    @FieldMapping(columnName = BaseMmsColumns.SUBJECT, physicalType = FieldMapping.PhysicalType.String)
    public String subject;

    @FieldMapping(columnName = BaseMmsColumns.SUBJECT_CHARSET, physicalType = FieldMapping.PhysicalType.Int)
    public int subjectCharset;

    @FieldMapping(columnName = BaseMmsColumns.TEXT_ONLY, physicalType = FieldMapping.PhysicalType.Int, logicalType = FieldMapping.LogicalType.Boolean)
    public boolean textOnly;

    @FieldMapping(columnName = BaseMmsColumns.THREAD_ID, physicalType = FieldMapping.PhysicalType.Long)
    public long threadId;

    @FieldMapping(columnName = BaseMmsColumns.TRANSACTION_ID, physicalType = FieldMapping.PhysicalType.String)
    public String transactionId;

    public enum MessageType implements EnumInt {

        ALL(BaseMmsColumns.MESSAGE_BOX_ALL),
        INBOX(BaseMmsColumns.MESSAGE_BOX_INBOX),
        SENT(BaseMmsColumns.MESSAGE_BOX_SENT),
        DRAFT(BaseMmsColumns.MESSAGE_BOX_DRAFTS),
        OUTBOX(BaseMmsColumns.MESSAGE_BOX_OUTBOX);

        int val;

        MessageType(int val) {
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
    }

}
