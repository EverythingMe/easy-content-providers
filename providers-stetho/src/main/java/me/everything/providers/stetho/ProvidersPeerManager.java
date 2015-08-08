package me.everything.providers.stetho;

import android.content.Context;

import com.facebook.stetho.inspector.helper.ChromePeerManager;
import com.facebook.stetho.inspector.helper.PeerRegistrationListener;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;
import com.facebook.stetho.inspector.protocol.module.Database;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sromku
 */
public class ProvidersPeerManager extends ChromePeerManager {

    private Context mContext;

    public ProvidersPeerManager(Context context) {
        mContext = context;
        setListener(mPeerRegistrationListener);
    }

    private void bootstrapNewPeer(JsonRpcPeer peer) {

        // TODO - calendars only right now, extend it

//      for (Entity entity : entities) {
        Database.DatabaseObject databaseParams = new Database.DatabaseObject();
        databaseParams.id = "android-calendars" /*database.getPath()*/;
        databaseParams.name = "Calendars" /*"database.getName()*/;
        databaseParams.domain = mContext.getPackageName();
        databaseParams.version = "N/A";
        Database.AddDatabaseEvent eventParams = new Database.AddDatabaseEvent();
        eventParams.database = databaseParams;

        peer.invokeMethod("Database.addDatabase", eventParams, null /* callback */);
//      }

    }

    public List<String> getDatabaseTableNames(String databaseId) {

        List<String> names = new ArrayList<String>();

        switch (databaseId) {
            case "android-calendars":
                names.add("Calendars");
                names.add("Events");
                names.add("Reminders");
                names.add("Instances");
                names.add("Attendees");
                break;
        }

        return names;
    }

    public boolean contains(String databaseId) {
        // TODO
        return "android-calendars".equals(databaseId);
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
