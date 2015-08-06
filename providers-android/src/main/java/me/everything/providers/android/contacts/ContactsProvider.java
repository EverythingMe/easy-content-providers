package me.everything.providers.android.contacts;

import android.annotation.TargetApi;
import android.content.ContentUris;
import android.content.Context;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.ContactsContract;

import me.everything.providers.core.AbstractProvider;
import me.everything.providers.core.Data;

import java.io.ByteArrayInputStream;

@TargetApi(Build.VERSION_CODES.KITKAT)
public class ContactsProvider extends AbstractProvider {

    public ContactsProvider(Context context) {
        super(context);
    }

    /**
     * Get all carriers.
     *
     * @return List of carriers
     */
    public Data<Contact> getContacts() {
        Data<Contact> contactsNoEmail = getContentTableData(Contact.uri, Contact.class);
        // List<Contact> contactsWithEmail =
        // getContentTableData(Contact.uriEmail, Contact.class);

        return contactsNoEmail;
    }

    public Uri getPhotoUri(Context context, String contactId) {
        try {
            Cursor cur = context.getContentResolver().query(ContactsContract.Data.CONTENT_URI, null,
                    ContactsContract.Data.CONTACT_ID + "=" + contactId + " AND " + ContactsContract.Data.MIMETYPE + "='" + ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE + "'", null, null);
            if (cur != null) {
                if (!cur.moveToFirst()) {
                    return null; // no photo
                }
            } else {
                return null; // error in cursor process
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        Uri person = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long.parseLong(contactId));
        return Uri.withAppendedPath(person, ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);
    }

    public Bitmap getContactPhoto(Context context, String contactId) {
        Uri contactUri = ContentUris.withAppendedId(ContactsContract.Contacts.CONTENT_URI, Long.parseLong(contactId));
        Uri photoUri = Uri.withAppendedPath(contactUri, ContactsContract.Contacts.Photo.CONTENT_DIRECTORY);
        Cursor cursor = context.getContentResolver().query(photoUri,
                new String[] {ContactsContract.Contacts.Photo.PHOTO}, null, null, null);
        if (cursor == null) {
            return null;
        }
        try {
            if (cursor.moveToFirst()) {
                byte[] data = cursor.getBlob(0);
                if (data != null) {
                    return BitmapFactory.decodeStream(new ByteArrayInputStream(data));
                }
            }
        } finally {
            cursor.close();
        }
        return null;

    }

}
