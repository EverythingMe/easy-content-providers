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

import me.everything.providers.core.Entity;

@TargetApi(Build.VERSION_CODES.HONEYCOMB)
public class Database implements ChromeDevtoolsDomain {

    private Context mContext;
    private final ProvidersPeerManager mProvidersPeerManager;
    private final ObjectMapper mObjectMapper;
    private final ModuleDatabaseDelegator mModuleDatabaseDelegator;

    public Database(Context context, ProvidersStetho providersStetho) {
        mContext = context;
        mModuleDatabaseDelegator = new ModuleDatabaseDelegator(mContext);
        mObjectMapper = new ObjectMapper();
        mProvidersPeerManager = new ProvidersPeerManager(mContext, providersStetho);
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

            QueryRelover queryRelover = new QueryRelover(query);
            String tableName = queryRelover.tableName;

            String[] columns = mProvidersPeerManager.getColumns(databaseId, tableName);
            List<? extends Entity> entities = mProvidersPeerManager.getEntites(databaseId, tableName, query);

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

    private static class QueryRelover {

        String tableName;

        public QueryRelover(String query) {
            query = query.toLowerCase();
            tableName = query.split("\"")[1];
        }
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

    private static class AddDatabaseEvent {
        @JsonProperty(required = true)
        public DatabaseObject database;
    }

    private static class DatabaseObject {
        @JsonProperty(required = true)
        public String id;

        @JsonProperty(required = true)
        public String domain;

        @JsonProperty(required = true)
        public String name;

        @JsonProperty(required = true)
        public String version;
    }

    private static class Error {
        @JsonProperty(required = true)
        public String message;

        @JsonProperty(required = true)
        public int code;
    }

}
