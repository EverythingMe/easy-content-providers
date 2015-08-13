package me.everything.providers.stetho;

import android.content.Context;

import com.facebook.stetho.inspector.helper.ChromePeerManager;
import com.facebook.stetho.inspector.helper.PeerRegistrationListener;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;
import com.facebook.stetho.inspector.protocol.module.Database;

import java.util.List;

import me.everything.providers.core.Entity;

/**
 * Created by sromku
 */
public class ProvidersPeerManager extends ChromePeerManager {

    private final Context mContext;
    private final ProvidersStetho mProvidersStetho;

    public ProvidersPeerManager(Context context, ProvidersStetho providersStetho) {
        mContext = context;
        mProvidersStetho = providersStetho;
        setListener(mPeerRegistrationListener);
    }

    private void bootstrapNewPeer(JsonRpcPeer peer) {

        List<String> categories = mProvidersStetho.getCategories();
        for (String category : categories) {
            String databaseName = category;
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
        List<String> names = mProvidersStetho.getProviders(databaseId);
        return names;
    }

    public boolean contains(String databaseId) {
        return mProvidersStetho.hasCategory(databaseId);
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

    public String[] getColumns(String databaseId, String tableName) {
        return mProvidersStetho.getColumns(databaseId, tableName);
    }

    public List<? extends Entity> getEntites(String databaseId, String tableName, String query) {
        return mProvidersStetho.getEntites(databaseId, tableName, query);
    }
}
