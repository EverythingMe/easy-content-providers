package me.everything.providers.android.contacts;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.Build;
import android.provider.BaseColumns;
import android.provider.ContactsContract.CommonDataKinds.Email;
import android.provider.ContactsContract.CommonDataKinds.Phone;

import me.everything.providers.core.Entity;
import me.everything.providers.core.FieldMapping;
import me.everything.providers.core.IgnoreMapping;

@TargetApi(Build.VERSION_CODES.JELLY_BEAN)
/**
 * http://developer.android.com/reference/android/provider/ContactsContract.Contacts.html
 */
public class Contact extends Entity {

    @IgnoreMapping
    public static Uri uri = Phone.CONTENT_URI;

    @IgnoreMapping
    public static Uri uriEmail = Email.CONTENT_URI;

    @FieldMapping(columnName = BaseColumns._ID, physicalType = FieldMapping.PhysicalType.Long)
    public long id;

    @FieldMapping(columnName = Phone.DISPLAY_NAME, physicalType = FieldMapping.PhysicalType.String)
    public String displayName;

    @FieldMapping(columnName = Phone.NUMBER, physicalType = FieldMapping.PhysicalType.String)
    public String phone;

    @FieldMapping(columnName = Phone.NORMALIZED_NUMBER, physicalType = FieldMapping.PhysicalType.String)
    public String normilizedPhone;

    @FieldMapping(columnName = Phone.PHOTO_URI, physicalType = FieldMapping.PhysicalType.String)
    public String uriPhoto;

    @FieldMapping(columnName = Email.ADDRESS, physicalType = FieldMapping.PhysicalType.String)
    public String email;

}
