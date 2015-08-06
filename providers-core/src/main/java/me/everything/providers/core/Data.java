package me.everything.providers.core;

import android.database.Cursor;

import java.util.ArrayList;
import java.util.List;

public class Data<T extends Entity> {

    private final Class<T> mCls;
    private Cursor mCursor;

    public Data(Cursor cursor, Class<T> cls) {
        mCursor = cursor;
        mCls = cls;
    }

    /**
     * This may take time if there is a lot of rows in database
     * Returns list with all content
     */
    public List<T> getList() {
        List<T> data = new ArrayList<T>();
        if (mCursor == null) {
            return data;
        }
        try {
            while (mCursor.moveToNext()) {
                T t = Entity.create(mCursor, mCls);
                data.add(t);
            }
        } finally {
            mCursor.close();
        }
        return data;
    }

    public Cursor getCursor() {
        return mCursor;
    }

    public T fromCursor(Cursor cursor) {
        return Entity.create(mCursor, mCls);
    }

    public T fromCursor(Cursor cursor, String... projection) {
        return Entity.create(mCursor, mCls, projection);
    }
}
