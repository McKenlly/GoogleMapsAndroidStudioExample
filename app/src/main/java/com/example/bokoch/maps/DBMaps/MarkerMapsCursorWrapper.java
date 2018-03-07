package com.example.bokoch.maps.DBMaps;

import android.database.Cursor;
import android.database.CursorWrapper;
import android.util.Log;

import com.example.bokoch.maps.MarkerMaps;

import java.util.UUID;

public class MarkerMapsCursorWrapper extends CursorWrapper {
    public MarkerMapsCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public MarkerMaps getMarkerMaps() {
        int id = getInt(getColumnIndex(MarkerMapsDbSchema.MarkersTable.Cols.ID));
        String namePlaces = getString(getColumnIndex(MarkerMapsDbSchema.MarkersTable.Cols.NAME_PLACES));
        double lat = getDouble(getColumnIndex(MarkerMapsDbSchema.MarkersTable.Cols.LAT));
        double lng = getDouble(getColumnIndex(MarkerMapsDbSchema.MarkersTable.Cols.LNG));

        Log.i("getMaps", "Before create Marker");
        MarkerMaps marker = new MarkerMaps(id, namePlaces, lat, lng);
        Log.i("getMaps", "After create Marker");

        return marker;
    }
}
