package com.laioffer.travelplanner.models;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;
import android.util.Log;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;
import java.util.List;
/*
0. For a given user with user id -> [String user_id] and a list of place -> [List<PlaceInfo> p_list]
1. Create DBManager instance:
    DBManager dbm = new DBManager(this);
2. To save the list:
    dbm.savePlaces(user_id, p_list);
3. To retrive the saved place list for the user:
    dbm.loadPlaces(user_io);
 */
public class DBManager{

    private static final String TAG_DBManager = "DBManager";
    private static final String TAG_DBManagerHelper= "DBManagerHelper";
    private static final int DATABASE_VERSION = 2;
    private static final String nullColumnHack = "_col";
    //private Context mContext;
    private DatabaseHelper mDatabaseHelper;
    private SQLiteDatabase mSQLiteDatabase;

    private static final String DB_NAME = "flagcamp_db";
    private static final String USER_TABLE_NAME = "user_table";
    private static final String USER_TABLE_COLUMN_1 = "user_id";
    private static final String USER_TABLE_COLUMN_2 = "password";

    private static final String CREATE_USER_TABLE = "CREATE TABLE " + USER_TABLE_NAME + " ("
            + USER_TABLE_COLUMN_1 + " TEXT PRIMARY KEY,"
            + USER_TABLE_COLUMN_2 + " TEXT NOT NULL "
            + ");";

    private static final String PLACE_TABLE_NAME = "place_table";
    private static final String PLACE_TABLE_COLUMN_0 = "place_table_default_id";
    private static final String PLACE_TABLE_COLUMN_1 = "user_id";
    private static final String PLACE_TABLE_COLUMN_2 = "name";
    private static final String PLACE_TABLE_COLUMN_3 = "address";
    private static final String PLACE_TABLE_COLUMN_4 = "phoneNumber";
    private static final String PLACE_TABLE_COLUMN_5 = "id";
    private static final String PLACE_TABLE_COLUMN_6 = "websiteUri";
    private static final String PLACE_TABLE_COLUMN_7 = "lat";
    private static final String PLACE_TABLE_COLUMN_8 = "lng";
    private static final String PLACE_TABLE_COLUMN_9 = "rating";
    private static final String PLACE_TABLE_COLUMN_10 = "attributions";

    private static final String CREATE_PLACE_TABLE = "CREATE TABLE " + PLACE_TABLE_NAME + " ("
            + PLACE_TABLE_COLUMN_0 + " INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT UNIQUE,"
            + PLACE_TABLE_COLUMN_1 + " TEXT NOT NULL ,"
            + PLACE_TABLE_COLUMN_2 + " TEXT,"
            + PLACE_TABLE_COLUMN_3 + " TEXT,"
            + PLACE_TABLE_COLUMN_4 + " TEXT,"
            + PLACE_TABLE_COLUMN_5 + " TEXT NOT NULL ,"
            + PLACE_TABLE_COLUMN_6 + " TEXT,"
            + PLACE_TABLE_COLUMN_7 + " TEXT NOT NULL ,"
            + PLACE_TABLE_COLUMN_8 + " TEXT NOT NULL ,"
            + PLACE_TABLE_COLUMN_9 + " TEXT NOT NULL ,"
            + PLACE_TABLE_COLUMN_10 + " TEXT "
            + ");";

    private static class DatabaseHelper extends SQLiteOpenHelper {

        DatabaseHelper(Context context) {
            super(context, DB_NAME, null, DATABASE_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            Log.i(TAG_DBManagerHelper,"db.getVersion()="+db.getVersion());
            db.execSQL("DROP TABLE IF EXISTS " + USER_TABLE_NAME + ";");
            db.execSQL(CREATE_USER_TABLE);
            Log.i(TAG_DBManagerHelper, "db.execSQL(CREATE_USER_TABLE)");
            Log.e(TAG_DBManagerHelper, CREATE_USER_TABLE);
            db.execSQL("DROP TABLE IF EXISTS " + PLACE_TABLE_NAME + ";");
            db.execSQL(CREATE_PLACE_TABLE);
            Log.i(TAG_DBManagerHelper, "db.execSQL(CREATE_PLACE_TABLE)");
            Log.e(TAG_DBManagerHelper, CREATE_PLACE_TABLE);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            Log.i(TAG_DBManagerHelper, "DataBaseManagementHelper onUpgrade");
            onCreate(db);
        }
    }

    public DBManager(Context context) {
        Log.i(TAG_DBManager, "Creating DBManager");
        Log.i(TAG_DBManager, "DBManager creating new DBMHelper");
        mDatabaseHelper = new DatabaseHelper(context);
        openDataBase();

    }
    public void savePlaces(String user_id, List<PlaceInfo> list) {
        //openDataBase();
        mSQLiteDatabase.delete(PLACE_TABLE_NAME,
                PLACE_TABLE_COLUMN_1+"=?",
                new String[]{user_id});
        for (PlaceInfo p : list) {
            insertPlace(user_id, p);
        }
        //closeDataBase();
    }

    private void openDataBase() throws SQLException {
        mSQLiteDatabase = mDatabaseHelper.getWritableDatabase();
    }

    private void closeDataBase() throws SQLException {
        mDatabaseHelper.close();
    }

    private void insertPlace(String user_id, PlaceInfo p) {
        ContentValues values = new ContentValues();
        values.put(PLACE_TABLE_COLUMN_1, user_id);
        values.put(PLACE_TABLE_COLUMN_2, p.getName());
        values.put(PLACE_TABLE_COLUMN_3, p.getAddress());
        values.put(PLACE_TABLE_COLUMN_4, p.getPhoneNumber());
        values.put(PLACE_TABLE_COLUMN_5, p.getId());
        values.put(PLACE_TABLE_COLUMN_6, p.getWebsiteUri().toString());
        values.put(PLACE_TABLE_COLUMN_7, p.getLatlng().latitude);
        values.put(PLACE_TABLE_COLUMN_8, p.getLatlng().longitude);
        values.put(PLACE_TABLE_COLUMN_9, p.getRating());
        values.put(PLACE_TABLE_COLUMN_10, p.getAttributions());
        mSQLiteDatabase.insert(PLACE_TABLE_NAME, nullColumnHack, values);
    }

    public List<PlaceInfo> loadPlaces(String user_id) {
        return getPlacesById(user_id);
    }

    private List<PlaceInfo> getPlacesById(String user_id) {
        Cursor c = mSQLiteDatabase.query(PLACE_TABLE_NAME,
                null,
                PLACE_TABLE_COLUMN_1+"=?",
                new String[]{user_id},
                null,
                null,
                null);
        if (c == null) {
            return null;
        }

        List<PlaceInfo> list = new ArrayList<>();
        while (c.moveToNext()) {
            String name = c.getString(2);
            String address = c.getString(3);
            String phoneNumber = c.getString(4);
            String id = c.getString(5);
            Uri websiteUri = Uri.parse(c.getString(6));
            LatLng latLng = new LatLng(c.getDouble(7),c.getDouble(8));
            float rating = c.getFloat(9);
            String attributions = c.getString(10);
            PlaceInfo p = new PlaceInfo(name, address, phoneNumber, id, websiteUri, latLng, rating, attributions);
            list.add(p);
        }
        return list;
    }

    void makeChangeToDB(SQLiteDatabase db) {
        try {
            db.beginTransaction();
            // your sql stuff
            db.setTransactionSuccessful();
        } catch(SQLException e) {
            // do some error handling
        } finally {
            db.endTransaction();
        }
    }



}
