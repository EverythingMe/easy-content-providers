package me.everything.providers.android.telephony;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.provider.Telephony.Sms.Conversations;
import android.provider.Telephony.TextBasedSmsColumns;

import me.everything.providers.core.Entity;
import me.everything.providers.core.FieldMapping;
import me.everything.providers.core.IgnoreMapping;

@TargetApi(Build.VERSION_CODES.KITKAT)
public class Conversation extends Entity {

    @IgnoreMapping
    public static Uri uri = Conversations.CONTENT_URI;

    @FieldMapping(columnName = TextBasedSmsColumns.THREAD_ID, physicalType = FieldMapping.PhysicalType.Int)
    public int threadId;

    @FieldMapping(columnName = Conversations.MESSAGE_COUNT, physicalType = FieldMapping.PhysicalType.Int)
    public int messageCount;

    @FieldMapping(columnName = Conversations.SNIPPET, physicalType = FieldMapping.PhysicalType.String)
    public String snippet;
}
