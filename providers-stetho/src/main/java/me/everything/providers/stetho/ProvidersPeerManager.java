package me.everything.providers.stetho;

import android.content.Context;

import com.facebook.stetho.inspector.helper.ChromePeerManager;
import com.facebook.stetho.inspector.helper.PeerRegistrationListener;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;
import com.facebook.stetho.inspector.protocol.module.Database;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sromku
 */
public class ProvidersPeerManager extends ChromePeerManager {

    private Context mContext;
    private final static String[] databases = new String[] {
            "provider-calendars",
            "provider-contacts",
            "provider-calls",
            "provider-telephony",
            "provider-bookmarks",
            "provider-dictionary",
            "provider-media-external",
            "provider-media-internal",
    };

    public ProvidersPeerManager(Context context) {
        mContext = context;
        setListener(mPeerRegistrationListener);
    }

    private void bootstrapNewPeer(JsonRpcPeer peer) {

        for (String databaseName : databases) {
            Database.DatabaseObject databaseParams = new Database.DatabaseObject();
            databaseParams.id = databaseName;
            databaseParams.name = databaseName;
            databaseParams.domain = mContext.getPackageName();
            databaseParams.version = "N/A";
            Database.AddDatabaseEvent eventParams = new Database.AddDatabaseEvent();
            eventParams.database = databaseParams;
            peer.invokeMethod("Database.addDatabase", eventParams, null /* callback */);
        }

    }

    public List<String> getDatabaseTableNames(String databaseId) {

        List<String> names = new ArrayList<String>();

        switch (databaseId) {
            case "provider-calendars":
                names.add("calendars");
                names.add("events");
                names.add("instances");
                names.add("reminders");
                names.add("attendees");
                break;
            case "provider-contacts":
                names.add("contacts");
                break;
            case "provider-calls":
                names.add("calls");
                break;
            case "provider-telephony":
                names.add("sms");
                names.add("mms");
                names.add("conversations");
                names.add("threads");
                names.add("carriers");
                break;
            case "provider-bookmarks":
                names.add("bookmarks");
                names.add("searches");
                break;
            case "provider-dictionary":
                names.add("words");
                break;
            case "provider-media-external":
            case "provider-media-internal":
                names.add("files");
                names.add("images");
                names.add("audios");
                names.add("videos");
                names.add("albums");
                names.add("genres");
                names.add("playlists");
                names.add("artists");
                break;
        }

        return names;
    }

    public boolean contains(String databaseId) {
        List<String> dbs = Arrays.asList(databases);
        return dbs.contains(databaseId);
    }

    private final PeerRegistrationListener mPeerRegistrationListener =
            new PeerRegistrationListener() {
                @Override
                public void onPeerRegistered(JsonRpcPeer peer) {
                    bootstrapNewPeer(peer);
                }

                @Override
                public void onPeerUnregistered(JsonRpcPeer peer) {
                }
            };

}
