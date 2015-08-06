package me.everything.providers.sample.custom;

import android.database.sqlite.SQLiteDatabase;

/**
 * Created by sromku on 8/4/15.
 */
public class PostsTable {

    // table name
    public static final String TABLE_NAME = "posts";

    // columns
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_TITLE = "title";
    public static final String COLUMN_THUMBNAIL = "thumbnail";
    public static final String COLUMN_FROM_ID = "from_id";
    public static final String COLUMN_IS_OWNER = "is_owner";
    public static final String COLUMN_UPDATED_AT = "updated_at";

    // Database creation sql statement
    private static final String DATABASE_CREATE = "create table " + TABLE_NAME + "("
            + COLUMN_ID + " integer primary key autoincrement, "
            + COLUMN_TITLE + " text, "
            + COLUMN_THUMBNAIL + " blob, "
            + COLUMN_FROM_ID + " integer, "
            + COLUMN_IS_OWNER + " integer, "
            + COLUMN_UPDATED_AT + " integer "
            + ");";

    public static void onCreate(SQLiteDatabase database) {
        database.execSQL(DATABASE_CREATE);
    }

    public static void onUpgrade(SQLiteDatabase database, int oldVersion, int newVersion) {
    }

    public static String[] getColumns() {
        String[] columns = new String[] {
                COLUMN_ID,
                COLUMN_TITLE,
                COLUMN_THUMBNAIL,
                COLUMN_FROM_ID,
                COLUMN_IS_OWNER,
                COLUMN_UPDATED_AT
        };
        return columns;
    }

}
