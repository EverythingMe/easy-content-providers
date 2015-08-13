package me.everything.providers.stetho;

import android.content.Context;

import com.facebook.stetho.inspector.jsonrpc.JsonRpcException;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcPeer;
import com.facebook.stetho.inspector.jsonrpc.JsonRpcResult;
import com.facebook.stetho.inspector.protocol.ChromeDevtoolsMethod;
import com.facebook.stetho.inspector.protocol.module.Database;

import org.json.JSONObject;

/**
 * Since the implementation made by Stetho is really coupled to protocol, I had to make some
 * hacky override of Database class and methods, but also to keep the basic Stetho Database class working
 * as expected. This class will be delegator of all events to the original Stetho Database class.
 */
public class ModuleDatabaseDelegator {

    private com.facebook.stetho.inspector.protocol.module.Database mDatabase;

    public ModuleDatabaseDelegator(Context context) {
        mDatabase = new Database(context);
    }

    @ChromeDevtoolsMethod
    public void enable(JsonRpcPeer peer, JSONObject params) {
        mDatabase.enable(peer, params);
    }

    @ChromeDevtoolsMethod
    public void disable(JsonRpcPeer peer, JSONObject params) {
        mDatabase.disable(peer, params);
    }

    @ChromeDevtoolsMethod
    public JsonRpcResult getDatabaseTableNames(JsonRpcPeer peer, JSONObject params)
            throws JsonRpcException {
        return mDatabase.getDatabaseTableNames(peer, params);
    }

    @ChromeDevtoolsMethod
    public JsonRpcResult executeSQL(JsonRpcPeer peer, JSONObject params) {
        return mDatabase.executeSQL(peer, params);
    }
}
