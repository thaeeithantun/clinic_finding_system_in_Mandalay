package db.test.com.clinic_ui;

import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toolbar;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class justTest extends AppCompatActivity implements View.OnClickListener {
    public ArrayList<Integer> clinic_on_map;
    LocationManager locationManager;
    TextView fname,sname,tname,fadd,sadd,tadd,ftime,stime,ttime,fdistance,sdistance,tdistance,tv5;
    SimpleDateFormat simpleDateFormat;
    public String hr,min,sec,lat,lon,c_type;
    public String name2="";
    public String name3="";
    public String name4="";
    public String add1,add2,add3=" ";
    //Calendar calander;
    public Toolbar tb;
    Button find;
    public String firstclinic,secondclinic,thirdclinic="";
    public String firstadd,secondadd,thirdadd="";
    public String firsttime,secondtime,thirdtime="";
    public int firstclinicId,secondclinicId,thirdclinicId=0;
    public double firstLat,firstLong,secondLat,secondLong,thirdLat,thirdLong=0.0;
    public double firstd,secondd,thirdd,lat1,long1=0.0;
    public double lat2,long2,lat3,long3,lat4,long4=0.0;
    public static LatLong c_one=new LatLong();
    public static LatLong c_two=new LatLong();
    public static LatLong c_three=new LatLong();



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.justfortest);


        fname=(TextView)findViewById(R.id.fname);
        fadd=(TextView)findViewById(R.id.fadd);
        fdistance=(TextView)findViewById(R.id.fdistance);
        ftime=(TextView)findViewById(R.id.ftime);

        sname=(TextView)findViewById(R.id.sname);
        sadd=(TextView)findViewById(R.id.sadd);
        sdistance=(TextView)findViewById(R.id.sdistance);
        stime=(TextView)findViewById(R.id.stime);

        tname=(TextView)findViewById(R.id.tname);
        tadd=(TextView)findViewById(R.id.tadd);
        tdistance=(TextView)findViewById(R.id.tdistance);
        ttime=(TextView)findViewById(R.id.ttime);

        find=(Button)findViewById(R.id.find);
        find.setOnClickListener(this);
        Bundle b = getIntent().getExtras();
        lat1 = b.getDouble("lat");
        long1=b.getDouble("long");
        int hr1=b.getInt("hr");
        int min1=b.getInt("min");
        c_type=b.getString("c_type");
        int current_day=b.getInt("currentday");

        String stringlat= Double.toString(lat1);
        String stringlong=Double.toString(long1);

        int h=hr1;
        int m=min1;

       double time = h+ m/60 ;

        //tv3.setText(stringlat);
        //tv4.setText(stringlong);
        tv5=(TextView)findViewById(R.id.n_clinic);

        DatabaseAccess databaseAccess=DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        ArrayList<LatLong> clinicList = databaseAccess.getlatlong(time,c_type,current_day);
        LatLong l=new LatLong();
        LatLong c=new LatLong();



        tv5.setText(tv5.getText()+ " " +clinicList.size()+" nearest clinics found");
        int size=clinicList.size();

        for(int i=0;i<size;i++){
            double km=getDistanceFromLatLonInKm(lat1,long1,clinicList.get(i).getLat(),clinicList.get(i).getLon());
            clinicList.get(i).setKm(km);
        }


            clinic_on_map=getThreeNearestClinic(clinicList, clinicList.size());


    }

    private ArrayList<Integer> getThreeNearestClinic(ArrayList<LatLong> clinicList, int size) {
        ArrayList<Integer> arrlist=new ArrayList<>();

        if (size < 3){

            if(size==1){
                firstd=clinicList.get(0).getKm();
                firstclinic=clinicList.get(0).getCname();
                firsttime=clinicList.get(0).getDuration();
                firstadd=clinicList.get(0).getAddress();




                c_one.setCid(clinicList.get(0).getCid());
                c_one.setCname(clinicList.get(0).getCname());
                c_one.setLat(clinicList.get(0).getLat());
                c_one.setLon(clinicList.get(0).getLon());
                c_one.setKm(clinicList.get(0).getKm());
                c_one.setAddress(clinicList.get(0).getAddress());
                c_one.setDuration(clinicList.get(0).getDuration());

                arrlist.add(c_one.getCid());

                secondclinic=" no second";
                secondd=0.0;

                thirdd=0.0;
                thirdclinic=" no third";

                //first.setText(firstclinic+"\n"+firstd+"\n"+firstadd+"\n"+firsttime);
                //second.setText(secondclinic+"\n"+secondd+"\n"+secondadd+"\n"+secondtime);
                //third.setText(thirdclinic+"\n"+thirdd+"\n"+thirdadd+"\n"+thirdtime);
                fname.setText(firstclinic);
                fadd.setText(firstadd);
                ftime.setText(firsttime);
                fdistance.setText(String.format("%.2f  km", firstd));

                sname.setText(secondclinic);
                sadd.setText(secondadd);
                stime.setText(secondtime);
                sdistance.setText(String.format("%.2f  km", secondd));

                tname.setText(thirdclinic);
                tadd.setText(thirdadd);
                ttime.setText(thirdtime);
                tdistance.setText(String.format("%.2f  km", thirdd));

                lat2=c_one.getLat();
                long2=c_one.getLon();
                name2=c_one.getCname();


            }
            else if(size==2){
                firstd=clinicList.get(0).getKm();
                firstclinic=clinicList.get(0).getCname();
                firsttime=clinicList.get(0).getDuration();
                firstadd=clinicList.get(0).getAddress();



                c_one.setCid(clinicList.get(0).getCid());
                c_one.setCname(clinicList.get(0).getCname());
                c_one.setLat(clinicList.get(0).getLat());
                c_one.setLon(clinicList.get(0).getLon());
                c_one.setKm(clinicList.get(0).getKm());
                c_one.setAddress(clinicList.get(0).getAddress());
                c_one.setDuration(clinicList.get(0).getDuration());
                arrlist.add(c_one.getCid());
                lat2=c_one.getLat();
                long2=c_one.getLon();
                name2=c_one.getCname();

                secondd=clinicList.get(1).getKm();
                secondclinic=clinicList.get(1).getCname();
                secondtime=clinicList.get(1).getDuration();
                secondadd=clinicList.get(1).getAddress();


                c_two.setCid(clinicList.get(1).getCid());
                c_two.setCname(clinicList.get(1).getCname());
                c_two.setLat(clinicList.get(1).getLat());
                c_two.setLon(clinicList.get(1).getLon());
                c_two.setKm(clinicList.get(1).getKm());
                c_two.setAddress(clinicList.get(1).getAddress());
                c_two.setDuration(clinicList.get(1).getDuration());
                arrlist.add(c_two.getCid());

                lat3=c_two.getLat();
                long3=c_two.getLon();
                name3=c_two.getCname();

                thirdd=0.0;
                thirdclinic=" no third";

                if(secondd < firstd){
                    String temp= firstclinic;
                    double tempd=firstd;

                    firstd=secondd;
                    firstclinic=secondclinic;

                    secondd=tempd;
                    secondclinic=temp;


                }

                fname.setText(firstclinic);
                fadd.setText(firstadd);
                ftime.setText(firsttime);
                fdistance.setText(String.format("%.2f  km", firstd));

                sname.setText(secondclinic);
                sadd.setText(secondadd);
                stime.setText(secondtime);
                sdistance.setText(String.format("%.2f  km", secondd));

                tname.setText(thirdclinic);
                tadd.setText(thirdadd);
                ttime.setText(thirdtime);
                tdistance.setText(String.format("%.2f  km", thirdd));

            }

        }

        //greater than or equal three
        else {

            firstd=10000000000000000000000000000000000000000.0;
            //firstclinic=clinicList.get(0).getCname();

            secondd=1000000000000000000000000000000000000000.0;
            //secondclinic=clinicList.get(0).getCname();

            thirdd=10000000000000000000000000000000000000000.0;
            //thirdclinic=clinicList.get(0).getCname();


            for(int i=0;i<clinicList.size();i++){
                if(clinicList.get(i).getKm()<firstd){
                    thirdd=secondd;
                    thirdclinic=secondclinic;
                    thirdclinicId=secondclinicId;
                    thirdLat=secondLat;
                    thirdLong=secondLong;
                    thirdadd=secondadd;
                    thirdtime=secondtime;


                    secondd=firstd;
                    secondclinic=firstclinic;
                    secondclinicId=firstclinicId;
                    secondLat=firstLat;
                    secondLong=firstLong;
                    secondadd=firstadd;
                    secondtime=firsttime;


                    firstd=clinicList.get(i).getKm();
                    firstclinic=clinicList.get(i).getCname();
                    firstclinicId=clinicList.get(i).getCid();
                    firstLat=(clinicList.get(i).getLat());
                    firstLong=clinicList.get(i).getLon();
                    firstadd=clinicList.get(i).getAddress();
                    firsttime=clinicList.get(i).getDuration();

                }
                else if(clinicList.get(i).getKm()<secondd){

                    thirdd=secondd;
                    thirdclinic=secondclinic;
                    thirdclinicId=secondclinicId;
                    thirdLat=secondLat;
                    thirdLong=secondLong;
                    thirdadd=secondadd;
                    thirdtime=secondtime;

                    secondd=clinicList.get(i).getKm();
                    secondclinic=clinicList.get(i).getCname();
                    secondclinicId=clinicList.get(i).getCid();
                    secondLat=(clinicList.get(i).getLat());
                    secondLong=clinicList.get(i).getLon();
                    secondadd=clinicList.get(i).getAddress();
                    secondtime=clinicList.get(i).getDuration();


                }
                else if(clinicList.get(i).getKm()<thirdd){

                    thirdd=clinicList.get(i).getKm();
                    thirdclinic=clinicList.get(i).getCname();
                    thirdclinicId=clinicList.get(i).getCid();
                    thirdLat=(clinicList.get(i).getLat());
                    thirdLong=clinicList.get(i).getLon();
                    thirdadd=clinicList.get(i).getAddress();
                    thirdtime=clinicList.get(i).getDuration();
                }
            }

            fname.setText(firstclinic);
            fadd.setText(firstadd);
            ftime.setText(firsttime);
            fdistance.setText(String.format("%.2f  km", firstd));

            sname.setText(secondclinic);
            sadd.setText(secondadd);
            stime.setText(secondtime);
            sdistance.setText(String.format("%.2f  km", secondd));

            tname.setText(thirdclinic);
            tadd.setText(thirdadd);
            ttime.setText(thirdtime);
            tdistance.setText(String.format("%.2f  km", thirdd));

            c_one=new LatLong(firstLat,firstLong,firstclinic,firstclinicId,firstd,firstadd,firsttime);
            c_two=new LatLong(secondLat,secondLong,secondclinic,secondclinicId,secondd,secondadd,secondtime);
            c_three=new LatLong(thirdLat,thirdLong,thirdclinic,thirdclinicId,thirdd,thirdadd,thirdtime);

            arrlist.add(c_one.getCid());
            arrlist.add(c_two.getCid());
            arrlist.add(c_three.getCid());

            lat2=c_one.getLat();
            long2=c_one.getLon();
            name2=c_one.getCname();
            add1=c_one.getAddress();

            lat3=c_two.getLat();
            long3=c_two.getLon();
            name3=c_two.getCname();
            add2=c_two.getAddress();

            lat4=c_three.getLat();
            long4=c_three.getLon();
            name4=c_three.getCname();
            add3=c_three.getAddress();

             }
            return arrlist;

    }

    //Haversine formula calculation
    private double getDistanceFromLatLonInKm(double lat_One, double lon_One,
                                             double lat_Two, double lon_Two) {

        lat_One=21.9657097;
        lon_One=96.0951433;

        int R = 6371; // Radius of the earth in km
        double dLat = deg2rad(lat_Two-lat_One);  // deg2rad below
        double dLon = deg2rad(lon_Two-lon_One);
        //dlat.setText(Double.toString(dLat));
        //dlong.setText(Double.toString(dLon));



        double a =
                Math.sin(dLat/2) * Math.sin(dLat/2) +
                        Math.cos(deg2rad(lat_One)) * Math.cos(deg2rad(lat_Two)) *
                                Math.sin(dLon/2) * Math.sin(dLon/2)
                ;
        //aa.setText(Double.toString(a));


        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
        //cc.setText(Double.toString(c));
        double d = R * c; // Distance in km
        return d;
    }

    private double deg2rad(double d) {
        return d * (Math.PI/180);

    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(getApplicationContext(),Three_button.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        i.putExtra("arg",c_type);

        startActivity(i);
    }

    @Override
    public void onClick(View view) {
        Intent i=new Intent(justTest.this,MapsActivity.class);
        Bundle b=new Bundle();
        b.putDouble("lat",lat1);
        b.putDouble("long",long1);
        b.putDouble("lat1",lat2);
        b.putDouble("long1",long2);
        b.putString("name1",name2);
        b.putString("add1",add1);
        b.putDouble("lat2",lat3);
        b.putDouble("long2",long3);
        b.putString("name2",name3);
        b.putString("add2",add2);
        b.putDouble("lat3",lat4);
        b.putDouble("long3",long4);
        b.putString("name3",name4);
        b.putString("add3",add3);

        i.putExtras(b);
        startActivity(i);
    }
}
