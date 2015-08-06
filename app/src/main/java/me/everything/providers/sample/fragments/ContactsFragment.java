package me.everything.providers.sample.fragments;

import android.content.Context;
import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.TextView;

import me.everything.providers.android.contacts.Contact;
import me.everything.providers.android.contacts.ContactsProvider;
import me.everything.providers.core.Data;
import me.everything.providers.sample.R;

/**
 * Created by sromku
 */
public class ContactsFragment extends BaseFragment {

    private ListView mListView;
    private ContactsAdapter mAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.list_fragment, container, false);
        setToolbar(view, "Contacts");

        mListView = (ListView) view.findViewById(R.id.list);

        // show contacts by using cursor
        new GetContactsTask().execute();

        return view;
    }

    /**
     * Getting the cursor to all contacts
     */
    private class GetContactsTask extends AsyncTask<Void, Void, Data<Contact>> {

        @Override
        protected Data<Contact> doInBackground(Void... params) {
            ContactsProvider contactsProvider = new ContactsProvider(getApplicationContext());
            Data<Contact> contacts = contactsProvider.getContacts();
            return contacts;
        }

        @Override
        protected void onPostExecute(Data<Contact> data) {
            mAdapter = new ContactsAdapter(getApplicationContext(), data);
            mListView.setAdapter(mAdapter);
        }
    }

    /**
     * Example of using cursor. (CursorAdapter)
     */
    private final static class ContactsAdapter extends CursorAdapter {

        private Data<Contact> mData;

        private class ViewHolder {
            TextView name;
            TextView phone;
        }

        public ContactsAdapter(Context context, Data<Contact> data) {
            super(context, data.getCursor(), FLAG_REGISTER_CONTENT_OBSERVER);
            mData = data;
        }

        @Override
        public View newView(Context context, Cursor cursor, ViewGroup parent) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_row_item, parent, false);
            ViewHolder viewHolder = new ViewHolder();
            viewHolder.name = (TextView) view.findViewById(R.id.title);
            viewHolder.phone = (TextView) view.findViewById(R.id.details);
            view.setTag(viewHolder);
            return view;
        }

        @Override
        public void bindView(View view, Context context, final Cursor cursor) {
            ViewHolder viewHolder = (ViewHolder) view.getTag();
            Contact contact = mData.fromCursor(cursor);
            viewHolder.name.setText(contact.displayName);
            viewHolder.phone.setText(contact.normilizedPhone);
        }

    }

}
