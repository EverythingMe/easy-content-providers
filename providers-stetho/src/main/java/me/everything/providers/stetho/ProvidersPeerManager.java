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
        databaseParams.id = "providers";
        databaseParams.name = "content-providers";
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
            case "providers":
                names.add("contacts");
                names.add("calendars");
                break;
        }

        return names;
    }

    public boolean contains(String databaseId) {
        // TODO
        return "providers".equals(databaseId);
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
