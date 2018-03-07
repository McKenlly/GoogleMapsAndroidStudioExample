package com.example.bokoch.maps;


import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.example.bokoch.maps.DBMaps.MarkerMapsBaseHelper;
import com.example.bokoch.maps.DBMaps.MarkerMapsCursorWrapper;
import com.example.bokoch.maps.DBMaps.MarkerMapsDbSchema;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MarkerLab {

    private static MarkerLab sMarkersLab;
    private Context mContext;
    private SQLiteDatabase mDatabase;
    private MarkerMapsBaseHelper mBaseHelper;

    public void addMarkers(MarkerMaps c) {
        ContentValues values = getContentValues(c);
    }

    public void updateMarkers(MarkerMaps marker) {
        String uuidString = String.valueOf(marker.getId());
        ContentValues values = getContentValues(marker);

        mDatabase.update(MarkerMapsDbSchema.MarkersTable.NAME, values,
                MarkerMapsDbSchema.MarkersTable.Cols.ID + " = ?",
                new String[] {uuidString});

    }

    private MarkerMapsCursorWrapper queryMarkers(String whereClause, String[] whereArgs) {
//        if (MarkerMapsDbSchema.MarkersTable.NAME)
        Cursor cursor = mDatabase.query(
                MarkerMapsDbSchema.MarkersTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );
        return new MarkerMapsCursorWrapper(cursor);
    }

    public static MarkerLab get(Context context) {
        if (sMarkersLab == null) {
            Log.i("get", "Context");
            sMarkersLab = new MarkerLab(context);
        }
        return sMarkersLab;
    }
    private MarkerLab(Context context) {
        mContext = context.getApplicationContext();
        mBaseHelper = new MarkerMapsBaseHelper(context);
        try {
            mBaseHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        try {
            mDatabase = mBaseHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }
    }
    //all exercise
    public List<MarkerMaps> getMarkers() {
        List<MarkerMaps> markers = new ArrayList<>();
        MarkerMapsCursorWrapper cursor = queryMarkers(null, null);

        try {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                markers.add(cursor.getMarkerMaps());
                cursor.moveToNext();
            }
        } finally {
            cursor.close();
        }
        return markers;
    }
    private static ContentValues getContentValues(MarkerMaps marker) {
        ContentValues values = new ContentValues();
        values.put(MarkerMapsDbSchema.MarkersTable.Cols.ID, marker.getId());
        values.put(MarkerMapsDbSchema.MarkersTable.Cols.NAME_PLACES, marker.getNamePlaces());
        values.put(MarkerMapsDbSchema.MarkersTable.Cols.LAT, marker.getLat());
        values.put(MarkerMapsDbSchema.MarkersTable.Cols.LNG, marker.getLng());
        return values;
    }
    public MarkerMaps getMarker(int id) {
        Log.i("getMarkerMaps", String.valueOf(id));
        MarkerMapsCursorWrapper cursor = queryMarkers(MarkerMapsDbSchema.MarkersTable.Cols.ID + " = ?",
                new String[] { String.valueOf(id)});
        try {
            if (cursor.getCount() == 0) {
                Log.i("getMarkerMaps", "getCount == 0");
                return null;
            }
            cursor.moveToFirst();
            return cursor.getMarkerMaps();
        } finally {
            cursor.close();
        }
    }

}
