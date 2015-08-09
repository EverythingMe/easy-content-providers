/*
 * Copyright (c) 2014-present, Facebook, Inc.
 * All rights reserved.
 *
 * This source code is licensed under the BSD-style license found in the
 * LICENSE file in the root directory of this source tree. An additional grant
 * of patent rights can be found in the PATENTS file in the same directory.
 */

package me.everything.providers.stetho;

import android.annotation.TargetApi;
import android.content.Context;
import android.database.sqlite.SQLiteException;
import android.os.Build;

import com.facebook.stetho.inspector.jsonrpc.JsonRpcException;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcResult;
import com.facebook.stetho.inspector.jsonrpc.protocol.JsonRpcError;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsDomain;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsMethod;
import com.facebook.stetho.json.ObjectMapper;
import com.facebook.stetho.json.annotation.JsonProperty;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.everything.providers.android.browser.Bookmark;
import me.everything.providers.android.browser.BrowserProvider;
import me.everything.providers.android.browser.Search;
import me.everything.providers.android.calendar.Calendar;
import me.everything.providers.android.calendar.CalendarProvider;
import me.everything.providers.android.calendar.Event;
import me.everything.providers.android.calllog.Call;
import me.everything.providers.android.calllog.CallsProvider;
import me.everything.providers.android.contacts.Contact;
import me.everything.providers.android.contacts.ContactsProvider;
import me.everything.providers.android.dictionary.DictionaryProvider;
import me.everything.providers.android.dictionary.Word;
import me.everything.providers.android.media.Audio;
import me.everything.providers.android.media.File;
import me.everything.providers.android.media.Image;
import me.everything.providers.android.media.MediaProvider;
import me.everything.providers.android.media.Video;
import me.everything.providers.android.telephony.Carrier;
import me.everything.providers.android.telephony.Conversation;
import me.everything.providers.android.telephony.Mms;
import me.everything.providers.android.telephony.Sms;
import me.everything.providers.android.telephony.TelephonyProvider;
import me.everything.providers.core.Entity;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Database implements ChromeDevtoolsDomain {

    private Context mContext;
    private final ProvidersPeerManager mProvidersPeerManager;
    private final ObjectMapper mObjectMapper;
    private final ModuleDatabaseDelegator mModuleDatabaseDelegator;

    public Database(Context context) {
        mContext = context;
        mModuleDatabaseDelegator = new ModuleDatabaseDelegator(mContext);
        mObjectMapper = new ObjectMapper();
        mProvidersPeerManager = new ProvidersPeerManager(mContext);
    }

    @ChromeDevtoolsMethod
    public void enable(JsonRpcPeer peer, JSONObject params) {
        mModuleDatabaseDelegator.enable(peer, params);
        mProvidersPeerManager.addPeer(peer);
    }

    @ChromeDevtoolsMethod
    public void disable(JsonRpcPeer peer, JSONObject params) {
        mModuleDatabaseDelegator.disable(peer, params);
        mProvidersPeerManager.removePeer(peer);
    }

    @ChromeDevtoolsMethod
    public JsonRpcResult getDatabaseTableNames(JsonRpcPeer peer, JSONObject params)
            throws JsonRpcException {

        // fetch database id
        GetDatabaseTableNamesRequest request = mObjectMapper.convertValue(params,
                GetDatabaseTableNamesRequest.class);
        String databaseId = request.databaseId;

        if (mProvidersPeerManager.contains(databaseId)) {
            return getDatabaseTableNames(databaseId);
        }

        return mModuleDatabaseDelegator.getDatabaseTableNames(peer, params);

    }

    private JsonRpcResult getDatabaseTableNames(String databaseId) throws JsonRpcException {

        try {
            GetDatabaseTableNamesResponse response = new GetDatabaseTableNamesResponse();
            response.tableNames = mProvidersPeerManager.getDatabaseTableNames(databaseId);
            return response;
        } catch (SQLiteException e) {
            throw new JsonRpcException(
                    new JsonRpcError(
                            JsonRpcError.ErrorCode.INVALID_REQUEST,
                            e.toString(),
                            null /* data */));
        }

    }

    @ChromeDevtoolsMethod
    public JsonRpcResult executeSQL(JsonRpcPeer peer, JSONObject params) {

        ExecuteSQLRequest request = mObjectMapper.convertValue(params,
                ExecuteSQLRequest.class);

        String databaseId = request.databaseId;

        if (mProvidersPeerManager.contains(databaseId)) {
            return executeSQL(databaseId, request.query);
        }

        return mModuleDatabaseDelegator.executeSQL(peer, params);
    }

    private JsonRpcResult executeSQL(String databaseId, String query) {


        ExecuteSQLResponse response = new ExecuteSQLResponse();

        try {

            List<? extends Entity> entities = null;
            String[] columns = new String[0];

            // TODO - parse query and act accordingly
            // TODO - make it better and move to PeerManager

            if (query.toLowerCase().contains("calendars")) {
                CalendarProvider calendarProvider = new CalendarProvider(mContext);
                entities = calendarProvider.getCalendars().getList();
                columns = Entity.getColumns(Calendar.class);
            } else if (query.toLowerCase().contains("events")) {
                // TODO - temp only for testing
                CalendarProvider calendarProvider = new CalendarProvider(mContext);
                entities = calendarProvider.getEvents(1).getList();
                columns = Entity.getColumns(Event.class);
            }

            // contacts
            else if (query.toLowerCase().contains("contacts")) {
                ContactsProvider contactsProvider = new ContactsProvider(mContext);
                entities = contactsProvider.getContacts().getList();
                columns = Entity.getColumns(Contact.class);
            }

            // call log
            else if (query.toLowerCase().contains("calls")) {
                CallsProvider callsProvider = new CallsProvider(mContext);
                entities = callsProvider.getCalls().getList();
                columns = Entity.getColumns(Call.class);
            }

            // telephony
            else if (query.toLowerCase().contains("sms")) {
                TelephonyProvider telephonyProvider = new TelephonyProvider(mContext);
                entities = telephonyProvider.getSms(TelephonyProvider.Filter.ALL).getList();
                columns = Entity.getColumns(Sms.class);
            } else if (query.toLowerCase().contains("mms")) {
                TelephonyProvider telephonyProvider = new TelephonyProvider(mContext);
                entities = telephonyProvider.getMms(TelephonyProvider.Filter.ALL).getList();
                columns = Entity.getColumns(Mms.class);
            } else if (query.toLowerCase().contains("conversations")) {
                TelephonyProvider telephonyProvider = new TelephonyProvider(mContext);
                entities = telephonyProvider.getConversations().getList();
                columns = Entity.getColumns(Conversation.class);
            } else if (query.toLowerCase().contains("threads")) {
                TelephonyProvider telephonyProvider = new TelephonyProvider(mContext);
                entities = telephonyProvider.getThreads().getList();
                columns = Entity.getColumns(me.everything.providers.android.telephony.Thread.class);
            } else if (query.toLowerCase().contains("carriers")) {
                TelephonyProvider telephonyProvider = new TelephonyProvider(mContext);
                entities = telephonyProvider.getCarriers().getList();
                columns = Entity.getColumns(Carrier.class);
            }

            // browser
            else if (query.toLowerCase().contains("bookmarks")) {
                BrowserProvider browserProvider = new BrowserProvider(mContext);
                entities = browserProvider.getBookmarks().getList();
                columns = Entity.getColumns(Bookmark.class);
            } else if (query.toLowerCase().contains("searches")) {
                BrowserProvider browserProvider = new BrowserProvider(mContext);
                entities = browserProvider.getSearches().getList();
                columns = Entity.getColumns(Search.class);
            }

            // dictionary
            else if (query.toLowerCase().contains("words")) {
                DictionaryProvider dictionaryProvider = new DictionaryProvider(mContext);
                entities = dictionaryProvider.getWords().getList();
                columns = Entity.getColumns(Word.class);
            }

            // media
            else if (query.toLowerCase().contains("files")) {
                // TODO - check of external or internal
                MediaProvider mediaProvider = new MediaProvider(mContext);
                entities = mediaProvider.getFiles(MediaProvider.Storage.EXTERNAL).getList();
                columns = Entity.getColumns(File.class);
            } else if (query.toLowerCase().contains("images")) {
                // TODO - check of external or internal
                MediaProvider mediaProvider = new MediaProvider(mContext);
                entities = mediaProvider.getImages(MediaProvider.Storage.EXTERNAL).getList();
                columns = Entity.getColumns(Image.class);
            } else if (query.toLowerCase().contains("videos")) {
                // TODO - check of external or internal
                MediaProvider mediaProvider = new MediaProvider(mContext);
                entities = mediaProvider.getVideos(MediaProvider.Storage.EXTERNAL).getList();
                columns = Entity.getColumns(Video.class);
            } else if (query.toLowerCase().contains("audios")) {
                // TODO - check of external or internal
                MediaProvider mediaProvider = new MediaProvider(mContext);
                entities = mediaProvider.getAudios(MediaProvider.Storage.EXTERNAL).getList();
                columns = Entity.getColumns(Audio.class);
            }

            response.columnNames = Arrays.asList(columns);
            response.values = flattenRows(columns, entities);

        } catch (Exception e) {
            Error error = new Error();
            error.message = e.getMessage();
            error.code = 8000;
            response.sqlError = error;
        }

        return response;
    }

    private List<Object> flattenRows(String[] columns, List<? extends Entity> entities) {
        List<Object> rows = new ArrayList<Object>();
        for (Entity entity : entities) {
            rows.addAll(Entity.getFlattenedValues(columns, entity));
        }
        return rows;
    }

    private static class GetDatabaseTableNamesRequest {
        @JsonProperty(required = true)
        public String databaseId;
    }

    private static class GetDatabaseTableNamesResponse implements JsonRpcResult {
        @JsonProperty(required = true)
        public List<String> tableNames;
    }

    private static class ExecuteSQLRequest {
        @JsonProperty(required = true)
        public String databaseId;

        @JsonProperty(required = true)
        public String query;
    }

    private static class ExecuteSQLResponse implements JsonRpcResult {
        @JsonProperty
        public List<String> columnNames;

        @JsonProperty
        public List<Object> values;

        @JsonProperty
        public Error sqlError;
    }

    public static class AddDatabaseEvent {
        @JsonProperty(required = true)
        public DatabaseObject database;
    }

    public static class DatabaseObject {
        @JsonProperty(required = true)
        public String id;

        @JsonProperty(required = true)
        public String domain;

        @JsonProperty(required = true)
        public String name;

        @JsonProperty(required = true)
        public String version;
    }

    public static class Error {
        @JsonProperty(required = true)
        public String message;

        @JsonProperty(required = true)
        public int code;
    }

}
