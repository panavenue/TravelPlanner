package com.laioffer.travelplanner.models;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.google.android.gms.location.places.Place;

import java.util.List;

public class DBManager{

    private static final String TAG_DBManager = "DBManager";
    private static final String TAG_DBManagerHelper= "DBManagerHelper";
    private static final int DATABASE_VERSION = 2;
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

    DBManager(Context context) {
        Log.i(TAG_DBManager, "Creating DBManager");
        Log.i(TAG_DBManager, "DBManager creating new DBMHelper");
        mDatabaseHelper = new DatabaseHelper(context);
    }

    void openDataBase() throws SQLException {
        mSQLiteDatabase = mDatabaseHelper.getWritableDatabase();
    }

    void closeDataBase() throws SQLException {
        mDatabaseHelper.close();
    }

    boolean savePlaces(String user_id, List<PlaceInfo> list) {
        return false;
    }

    void insertPlace(Place p) {

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
