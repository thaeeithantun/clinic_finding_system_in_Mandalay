package db.test.com.clinic_ui;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    public Double lat, lon, lat1, lon1, lat2, lon2, lat3, lon3;
    public String name1, name2, name3;
    public ArrayList<Integer> arrlist = new ArrayList<>();
    Location mLastLocation;
    Marker mCurrLocationMarker;
    private Circle mCircle;

    double radiusInMeters = 100.0;
    int strokeColor = 0xffff0000; //Color Code you want
    int shadeColor = 0x44ff0000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        Bundle b = getIntent().getExtras();
        lat = b.getDouble("lat");
        lon = b.getDouble("long");
        lat1 = b.getDouble("lat1");
        lon1 = b.getDouble("long1");
        lat2 = b.getDouble("lat2");
        lon2 = b.getDouble("long2");
        lat3 = b.getDouble("lat3");
        lon3 = b.getDouble("long3");
        name1 = b.getString("name1");
        name2 = b.getString("name2");
        name3 = b.getString("name3");


        lat=21.9657097;
        lon=96.0951433;

       // Toast.makeText(MapsActivity.this, "Clinic " + arrlist.size(), Toast.LENGTH_LONG).show();

        LatLng myloc = new LatLng(lat, lon);
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
       // mMap.setMyLocationEnabled(true);
        //CircleOptions circleOptions = new CircleOptions();
        //circleOptions.center(new LatLng(lat,lon));

       // circleOptions.radius(200);
       // circleOptions.fillColor(Color.BLUE);
       // circleOptions.strokeWidth(6);

       // mMap.addCircle(circleOptions);

        //mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(Latlng,18),5000,null);
        mMap.addMarker(new MarkerOptions().position(myloc).title("You are Here!"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(myloc, 18), 5000, null);

        // Add a marker in Sydney and move the camera
        LatLng c1 = new LatLng(lat1, lon1);
        mMap.addMarker(new MarkerOptions().position(c1).title(name1));
       // mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(sydney, 18), 5000, null);

        LatLng c2=new LatLng(lat2,lon2);
        mMap.addMarker(new MarkerOptions().position(c2).title(name2));
        //mMap.moveCamera(CameraUpdateFactory.newLatLng(myloc));

        LatLng c3 = new LatLng(lat3, lon3);
        mMap.addMarker(new MarkerOptions().position(c3).title(name3));
        // mMap.moveCamera(CameraUpdateFactory.newLatLng(kgmon));


    }



}
