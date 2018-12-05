package db.test.com.clinic_ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;

public class Result_page extends AppCompatActivity{
    public int id;
    public String drname;
    public ArrayList<doctor_clinic> dc=new ArrayList<>();
    public String result="";

public TextView dname,dclinic,duration;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.result);

        dname=(TextView)findViewById(R.id.d_name);
        dclinic=(TextView)findViewById(R.id.d_clinic);
        duration=(TextView)findViewById(R.id.d_duration);

        Bundle b = getIntent().getExtras();
        id=b.getInt("id");

        drname=b.getString("name");
        DatabaseAccess databaseAccess=DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        dc=databaseAccess.getDoctorClinic(id);
        for(int i=0;i<dc.size();i++){
            result+=dc.get(i).getName()+"\n"+dc.get(i).getDuration()+"\n";
        }
        dname.setText(drname);
        dclinic.setText(result);
        duration.setText("");

    }
}
