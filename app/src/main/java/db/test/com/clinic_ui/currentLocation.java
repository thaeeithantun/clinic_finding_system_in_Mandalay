package db.test.com.clinic_ui;


import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class currentLocation extends AppCompatActivity implements LocationListener, View.OnClickListener {
    public Double current_lat, current_long;
    Button getLocationBtn;
    TextView locationText;
    LocationManager locationManager;
    TextView tv1, tv2, tv3, latlong;
    SimpleDateFormat simpleDateFormat;
    public int hr, min, sec;
    public String time;
    Calendar calander;
    public int current_day, day;
    public Double lat = 0.0;
    public Double lon = 0.0;
    public Toolbar tb;

    //LocationManager locationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.location_current);
        //setTitle("clinic finder");


        getLocationBtn = (Button) findViewById(R.id.getLocationBtn);
        getLocationBtn.setOnClickListener(this);
        locationText = (TextView) findViewById(R.id.locationText);
        tv1 = (TextView) findViewById(R.id.hr);
        tv2 = (TextView) findViewById(R.id.min);
        tv3 = (TextView) findViewById(R.id.sec);
        latlong = (TextView) findViewById(R.id.latlong);


        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }

        getLocation();


        calander = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        day = calander.get(Calendar.DAY_OF_WEEK);

        time = simpleDateFormat.format(calander.getTime());
        String[] arrayString = time.split(":");

        hr = Integer.parseInt(arrayString[0]);
        min = Integer.parseInt(arrayString[1]);
        sec = Integer.parseInt(arrayString[2]);

    }


    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, this);
        } catch (SecurityException e) {
            e.printStackTrace();
        }

    }


    @Override
    public void onLocationChanged(Location location) {
        lat = location.getLatitude();
        lon = location.getLongitude();

        //locationText.setText("Latitude: " + location.getLatitude() + "\n Longitude: " + location.getLongitude());
        getLocationBtn.setText("CLICK ME!");


        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            locationText.setText(locationText.getText() + "\n"+addresses.get(0).getAddressLine(0)+", "+
            addresses.get(0).getAddressLine(1)+", "+addresses.get(0).getAddressLine(2));
        } catch (Exception e) {

        }

    }


    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(currentLocation.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onClick(View view) {
        getLatLong();
    }

    private void getLatLong() {

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
        Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        if (location != null){
            double latti = location.getLatitude();
            double longi = location.getLongitude();
            String passedArg = getIntent().getExtras().getString("arg");
            Intent i=new Intent(currentLocation.this,justTest.class);
            Bundle b = new Bundle();

            b.putDouble("lat",location.getLatitude());
            b.putDouble("long",location.getLongitude());
            b.putInt("hr",hr);
            b.putInt("min",min);
            b.putInt("currentday",day);
            b.putString("c_type",passedArg);

            i.putExtras(b);
            startActivity(i);


        } else {
            getLatLong();

        }

    }
}

