package me.everything.providers.core;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by sromku
 */
public abstract class Entity {

    /**
     * Get content provider columns.
     *
     * @return Array of column names.
     */
    public static <T> String[] getColumns(Class<T> cls) {
        List<String> columns = new ArrayList<String>();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(IgnoreMapping.class)) {
                FieldMapping contentField = field.getAnnotation(FieldMapping.class);
                if (contentField == null) continue;
                String columnName = contentField.columnName();
                columns.add(columnName);
            }
        }
        return columns.toArray(new String[columns.size()]);
    }

    /**
     * Get content provider columns that can be updated.
     *
     * @return Array of column names.
     */
    public static <T> String[] getWriteColumns(Class<T> cls) {
        List<String> columns = new ArrayList<String>();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(IgnoreMapping.class)) {
                FieldMapping contentField = field.getAnnotation(FieldMapping.class);
                if (contentField.canUpdate()) {
                    String columnName = contentField.columnName();
                    columns.add(columnName);
                }
            }
        }
        return columns.toArray(new String[columns.size()]);
    }

    public static Long getId(Entity entity) {
        return Long.valueOf(String.valueOf(getColumnValue("_id", entity)));
    }

    /**
     * Get value from the field of java Entity based on column name from
     * database.
     *
     * @param columnName
     *            The physical name of column in the database.
     * @param entity
     *            The logical java entity.
     * @return The value
     */
    public static Object getColumnValue(String columnName, Entity entity) {
        try {
            Field field = getColumnField(columnName, entity);
            if (field != null) {
                return field.get(entity);
            }
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Object getColumnValue(Field field, Entity entity) {
        try {
            return field.get(entity);
        }
        catch (IllegalArgumentException e) {
            e.printStackTrace();
        }
        catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Field getColumnField(String columnName, Entity entity) {
        Field[] fields = entity.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (!field.isAnnotationPresent(IgnoreMapping.class)) {
                FieldMapping contentField = field.getAnnotation(FieldMapping.class);
                if (contentField.columnName().equals(columnName)) {
                    return field;
                }
            }
        }
        return null;
    }

    public static ContentValues getContentValues(String[] columns, Entity entity) {
        ContentValues contentValues = new ContentValues();
        for (String column : columns) {
            Field field = getColumnField(column, entity);
            FieldMapping fieldMapping = field.getAnnotation(FieldMapping.class);
            Object value = getColumnValue(field, entity);
            if (fieldMapping != null) {
                FieldMapping.PhysicalType physicalType = fieldMapping.physicalType();
				/*
				 * This can fail in case of logical type != physical type. But,
				 * since we use this method for fields that logical & physical
				 * types are the same, then we can assume this strict approach.
				 */
                switch (physicalType) {
                    case Int:
                        contentValues.put(column, Integer.valueOf(String.valueOf(value)));
                        break;
                    case String:
                        contentValues.put(column, String.valueOf(value));
                        break;
                    case Long:
                        contentValues.put(column, Long.valueOf(String.valueOf(value)));
                        break;
                    case Double:
                        contentValues.put(column, Double.valueOf(String.valueOf(value)));
                        break;
                    default:
                        break;
                }
            }
        }
        return contentValues;
    }

    public static List<Object> getFlattenedValues(String[] columns, Entity entity) {
        List<Object> values = new ArrayList<Object>();
        for (String column : columns) {
            Field field = getColumnField(column, entity);
            Object value = getColumnValue(field, entity);
            if (value instanceof EnumInt) {
                value = String.valueOf(value);
            }
            values.add(value);
        }
        return values;
    }

    /**
     * Create new entity instance from database raw instance.
     *
     * @param <T>
     *
     * @param cursor
     */
    public static <T extends Entity> T create(Cursor cursor, Class<T> cls) {
        try {
            T entity = cls.newInstance();
            Field[] fields = cls.getDeclaredFields();
            return create(entity, cursor, cls, fields);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * Create new entity instance from database raw instance.
     *
     * @param <T>
     *
     * @param cursor
     */
    public static <T extends Entity> T create(Cursor cursor, Class<T> cls, String... projection) {
        try {
            T entity = cls.newInstance();
            Field[] fields = new Field[projection.length];
            for (int i = 0, size = projection.length; i < size; i++) {
                fields[i] = getColumnField(projection[i], entity);
            }
            return create(entity, cursor, cls, fields);
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static <T extends Entity> T create(T entity, Cursor cursor, Class<T> cls, Field[] fields) {
        for (Field field : fields) {
            try {
                FieldMapping contentField = field.getAnnotation(FieldMapping.class);
                IgnoreMapping ignoreMapping = field.getAnnotation(IgnoreMapping.class);
                if (contentField != null && ignoreMapping == null) {
                    String columnName = contentField.columnName();
                    String methodName = "get" + contentField.physicalType().name();

                    Method method = Cursor.class.getDeclaredMethod(methodName, int.class);
                    int columnIndex = cursor.getColumnIndexOrThrow(columnName);
                    Object object = method.invoke(cursor, columnIndex);
                    switch (contentField.logicalType()) {
                        case Boolean:
                            boolean value = Integer.valueOf(String.valueOf(object)) == 0 ? false : true;
                            field.setAccessible(true);
                            field.setBoolean(entity, value);
                            break;
                        case EnumInt:
                            @SuppressWarnings("unchecked")
                            Class<? extends EnumInt> enumType = (Class<? extends EnumInt>) field.getType();
                            Method enumStaticMethod = enumType.getMethod("fromVal", int.class);
                            Object enumInstance = enumStaticMethod.invoke(null, object);
                            field.setAccessible(true);
                            field.set(entity, enumInstance);
                            break;
                        case Array:
                            String[] strings = String.valueOf(object).split(contentField.splitRegex());
                            field.setAccessible(true);
                            field.set(entity, strings);
                            break;
                        default:
                            field.setAccessible(true);
                            field.set(entity, object);
                            break;
                    }
                }
            }
            catch (Exception e) {
                Log.e(Entity.class.getName(), "field=" + field.getName(), e);
            }
        }
        return entity;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("{");
        Field[] fields = getClass().getDeclaredFields();
        boolean firstTime = true;
        for (Field field : fields) {
            try {
                if (!firstTime) {
                    stringBuilder.append(", ");
                }
                field.setAccessible(true);
                stringBuilder.append(field.getName());
                stringBuilder.append("=");
                stringBuilder.append(field.get(this));
                firstTime = false;
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        stringBuilder.append("}");
        return stringBuilder.toString();
    }

}
