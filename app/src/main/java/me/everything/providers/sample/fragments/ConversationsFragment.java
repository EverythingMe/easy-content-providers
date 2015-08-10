package me.everything.providers.sample.fragments;

import android.widget.TextView;

import me.everything.providers.android.telephony.Conversation;
import me.everything.providers.android.telephony.TelephonyProvider;
import me.everything.providers.core.Data;
import me.everything.providers.sample.base.GetCursorTask;
import me.everything.providers.sample.base.RecycleViewCursorFragment;

/**
 * Created by sromku
 */
public class ConversationsFragment extends RecycleViewCursorFragment<Conversation> {

    @Override
    protected String getTitle() {
        return "Conversations";
    }

    @Override
    protected void bindEntity(Conversation conversation, TextView title, TextView details) {
        title.setText(conversation.snippet);
        details.setText(conversation.messageCount + "");
    }

    @Override
    protected GetCursorTask.DataFetcher<Conversation> getFetcher() {
        return new GetCursorTask.DataFetcher<Conversation>() {
            @Override
            public Data<Conversation> getData() {
                TelephonyProvider telephonyProvider = new TelephonyProvider(getApplicationContext());
                return telephonyProvider.getConversations();
            }
        };
    }


}
