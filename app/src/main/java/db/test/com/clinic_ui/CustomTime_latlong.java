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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CustomTime_latlong extends AppCompatActivity implements LocationListener, View.OnClickListener {
    //public Double current_lat,current_long;
    Button getLocationBtn;
    TextView locationText;
    LocationManager locationManager;
    //TextView tv1,tv2,tv3,latlong;
    //SimpleDateFormat simpleDateFormat;
    public String time,hr,min,sec,c_type;
    public int hr1,min1,current_day;
    public Toolbar tb;
    public Double lat=0.0;
    public Double lon=0.0;

    //LocationManager locationManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.location_current);


        getLocationBtn = (Button)findViewById(R.id.getLocationBtn);
        locationText = (TextView)findViewById(R.id.locationText);
        //tv1=(TextView)findViewById(R.id.hr);
        //tv2=(TextView)findViewById(R.id.min);
        // tv3=(TextView)findViewById(R.id.sec);
        //latlong=(TextView)findViewById(R.id.latlong);
        Bundle b = getIntent().getExtras();
        hr1=b.getInt("hr");
         min1=b.getInt("min");
         c_type=b.getString("c_type");
        current_day=b.getInt("currentday");

        getLocationBtn.setOnClickListener(this);


        if (ContextCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getApplicationContext(), android.Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            ActivityCompat.requestPermissions(this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION}, 101);

        }

        getLocation();


    }



    void getLocation() {
        try {
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 5000, 5, this);
        }
        catch(SecurityException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onLocationChanged(Location location) {
        // locationText.setText("Latitude: " + location.getLatitude() + "\n Longitude: " + location.getLongitude());
        //current_lat=location.getLatitude();
        //current_long=location.getLongitude();
        // latlong.setText(current_lat+" "+current_long);
        lat=location.getLatitude();
        lon=location.getLongitude();

        //locationText.setText("Latitude: " + location.getLatitude() + "\n Longitude: " + location.getLongitude());
        getLocationBtn.setText("CLICK ME!");
        //String passedArg = getIntent().getExtras().getString("arg");




        try {
            Geocoder geocoder = new Geocoder(this, Locale.getDefault());
            List<Address> addresses = geocoder.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
            locationText.setText(locationText.getText() + "\n"+addresses.get(0).getAddressLine(0)+", "+
            addresses.get(0).getAddressLine(1)+", "+addresses.get(0).getAddressLine(2));
        }catch(Exception e)
        {

        }

    }


    @Override
    public void onProviderDisabled(String provider) {
        Toast.makeText(CustomTime_latlong.this, "Please Enable GPS and Internet", Toast.LENGTH_SHORT).show();
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
        Log.w("HR",hr1+" SS");
        Log.w("MIN",min1+" SS");
        Toast.makeText(this,hr1+"hr"+min1+"min"+current_day,Toast.LENGTH_LONG);
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
            Intent i=new Intent(CustomTime_latlong.this,justTest.class);
            Bundle b1 = new Bundle();

            b1.putDouble("lat",location.getLatitude());
            b1.putDouble("long",location.getLongitude());
            b1.putInt("hr",hr1);
            b1.putInt("min",min1);
            b1.putInt("currentday",current_day);
            b1.putString("c_type",c_type);

            i.putExtras(b1);
            startActivity(i);


        } else {
            getLatLong();

        }
    }
}

