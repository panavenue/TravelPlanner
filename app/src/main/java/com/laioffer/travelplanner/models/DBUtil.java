package com.laioffer.travelplanner.models;

import android.database.sqlite.SQLiteOpenHelper;

import java.util.List;

public interface DBUtil {
    void register();
    boolean logIn(String user_id, String password);
    boolean savePlaces(String user_id, List<PlaceInfo> list);
    List<PlaceInfo> loadPlaces(String user_id);
    void createTable();
    void openDataBase();

}
