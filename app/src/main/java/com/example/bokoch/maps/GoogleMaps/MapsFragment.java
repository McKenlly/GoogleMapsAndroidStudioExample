package com.example.bokoch.maps.GoogleMaps;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.Log;

import com.example.bokoch.maps.MarkerLab;
import com.example.bokoch.maps.MarkerMaps;
import com.example.bokoch.maps.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.List;

public class MapsFragment extends FragmentActivity implements OnMapReadyCallback {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Retrieve the content view that renders the map.
        setContentView(R.layout.activity_map);
        // Get the SupportMapFragment and request notification
        // when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    // Include the OnCreate() method here too, as described above.
    @Override
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        MarkerLab lab = MarkerLab.get(this);
        Log.i("Error", "lab");
        List<MarkerMaps> markers = lab.getMarkers();
        //for (MarkerMaps i : markers) {
//            LatLng place = new LatLng(i.getLat(), i.getLng());
//            googleMap.addMarker(new MarkerOptions().position(place)
//                    .title(i.getNamePlaces()));
//            googleMap.moveCamera(CameraUpdateFactory.newLatLng(place));
        //}
    }

}
