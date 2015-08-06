package me.everything.providers.android.telephony;

import android.annotation.TargetApi;
import android.content.Context;
import android.net.Uri;
import android.os.Build;

import me.everything.providers.core.AbstractProvider;
import me.everything.providers.core.Data;

@TargetApi(Build.VERSION_CODES.KITKAT)
public class TelephonyProvider extends AbstractProvider {

    public TelephonyProvider(Context context) {
        super(context);
    }

    public enum Filter {
        ALL,
        INBOX,
        OUTBOX,
        SENT,
        DRAFT
    }

    /**
     * Get all sms.
     *
     * @return List of sms
     */
    public Data<Sms> getSms(Filter filter) {
        Uri uri = null;
        switch (filter) {
            case ALL:
                uri = Sms.uri;
                break;
            case INBOX:
                uri = Sms.uriInbox;
                break;
            case OUTBOX:
                uri = Sms.uriOutbox;
                break;
            case SENT:
                uri = Sms.uriSent;
                break;
            case DRAFT:
                uri = Sms.uriDraft;
                break;
        }
        Data<Sms> sms = getContentTableData(uri, Sms.class);
        return sms;
    }

    /**
     * Get all mms.
     *
     * @return List of mms
     */
    public Data<Mms> getMms(Filter filter) {
        Uri uri = null;
        switch (filter) {
            case ALL:
                uri = Mms.uri;
                break;
            case INBOX:
                uri = Mms.uriInbox;
                break;
            case OUTBOX:
                uri = Mms.uriOutbox;
                break;
            case SENT:
                uri = Mms.uriSent;
                break;
            case DRAFT:
                uri = Mms.uriDraft;
                break;
        }
        Data<Mms> Mms = getContentTableData(uri, Mms.class);
        return Mms;
    }

    /**
     * Get all conversations.
     *
     * @return List of conversations
     */
    public Data<Conversation> getConversations() {
        Data<Conversation> conversations = getContentTableData(Conversation.uri, Conversation.class);
        return conversations;
    }

    /**
     * Get all threads.
     *
     * @return List if threads
     */
    public Data<Thread> getThreads() {
        Data<Thread> threads = getContentTableData(Thread.uri, Thread.class);
        return threads;
    }

    /**
     * Get all carriers.
     *
     * @return List of carriers
     */
    public Data<Carrier> getCarriers() {
        Data<Carrier> carriers = getContentTableData(Carrier.uri, Carrier.class);
        return carriers;
    }

}
