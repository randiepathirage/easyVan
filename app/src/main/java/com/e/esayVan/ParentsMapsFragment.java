package com.e.esayVan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class ParentsMapsFragment extends Fragment {
    String name;
    ArrayList<String> childName=new ArrayList<>();
    ArrayList<String> longitude=new ArrayList<>();
    ArrayList<String> latitude=new ArrayList<>();

    private OnMapReadyCallback callback = new OnMapReadyCallback() {



        /**
         * Manipulates the map once available.
         * This callback is triggered when the map is ready to be used.
         * This is where we can add markers or lines, add listeners or move the camera.
         * In this case, we just add a marker near Sydney, Australia.
         * If Google Play services is not installed on the device, the user will be prompted to
         * install it inside the SupportMapFragment. This method will only be triggered once the
         * user has installed Google Play services and returned to the app.
         */
        @Override
        public void onMapReady(GoogleMap googleMap) {

            LatLng childLocation = new LatLng(Double.valueOf(latitude.get(0)),Double.valueOf(longitude.get(0)));
            googleMap.addMarker(new MarkerOptions().position(childLocation).title(childName.get(0)));
            //googleMap.moveCamera(CameraUpdateFactory.newLatLng(childLocation,10));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(childLocation,10));


   /*          LatLng childLocation = new LatLng(6.8457126,80.0347063999);
            googleMap.addMarker(new MarkerOptions().position(childLocation).title("child"));
            //googleMap.moveCamera(CameraUpdateFactory.newLatLng(childLocation,10));
            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(childLocation,10));*/

           for(int i=1;i<childName.size();i++){

                childLocation = new LatLng(Double.valueOf(latitude.get(i)), Double.valueOf(longitude.get(i)));
                googleMap.addMarker(new MarkerOptions().position(childLocation).title(childName.get(i)));
                //googleMap.moveCamera(CameraUpdateFactory.newLatLng(childLocation2));
                googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(childLocation,10));

            }
        }
    };


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        Bundle b2=getArguments();
       // name=b2.getString("username");
        childName=b2.getStringArrayList("children");
        longitude=b2.getStringArrayList("longitude");
        latitude=b2.getStringArrayList("latitude");

        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment mapFragment =
                (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(callback);
        }
    }
}