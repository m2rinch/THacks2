package com.example.currentplacedetailsonmap;

import android.support.v7.app.AppCompatActivity;
import com.google.android.gms.maps.OnMapReadyCallback;

import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.places.GeoDataClient;
import com.google.android.gms.location.places.PlaceDetectionClient;
import com.google.android.gms.location.places.PlaceLikelihood;
import com.google.android.gms.location.places.PlaceLikelihoodBufferResponse;
import com.google.android.gms.location.places.Places;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.MalformedURLException;
import java.io.IOException;
import java.io.*;
import java.util.ArrayList;
import java.util.*;

/**
 * Created by saminakhaliq on 2017-10-22.
 */

public class MakeMarker extends AppCompatActivity
        implements OnMapReadyCallback {

    MapsActivityCurrentPlace object = new MapsActivityCurrentPlace();
    List<String> texts = object.getTexts();
    double latitude = object.getLatitude();
    double longitude = object.getLongitude();
    public void onMapReady(GoogleMap googleMap) {
        // Add a marker in Sydney, Australia,
        // and move the map's camera to the same location.
        LatLng Location = new LatLng(latitude, longitude);
        googleMap.addMarker(new MarkerOptions().position(Location)
                .title("Your Location"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(Location));
        for( int i = 0; i<texts.size(); i++) {
            googleMap.addMarker(new MarkerOptions().position(Location).title(texts.get(i)));
        }
    }
}
