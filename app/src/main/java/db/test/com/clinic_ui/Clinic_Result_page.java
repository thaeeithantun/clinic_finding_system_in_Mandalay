package db.test.com.clinic_ui;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.util.ArrayList;

public class Clinic_Result_page extends AppCompatActivity{
    public int id;
    public String passarg;
    public Clinic c;
    public Clinic clinicInfo=new Clinic();



    public TextView cname,caddress,c_duration;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.clinic_result);
        //String passedArg = getIntent().getExtras().getString("id");
        cname=(TextView)findViewById(R.id.c_name);
        caddress=(TextView)findViewById(R.id.c_address);
        c_duration=(TextView)findViewById(R.id.c_duration);

        Bundle b = getIntent().getExtras();
        id=b.getInt("id");

       // String name=b.getString("cName");
       // String add=b.getString("caddress");
       // String time=b.getString("cduration");
        DatabaseAccess databaseAccess=DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        clinicInfo=databaseAccess.getClinicInfo(id);
        String cName=(clinicInfo.getCname());
       String add=(clinicInfo.getAddress());
        String cduration=(clinicInfo.getMduration() +"\n"+clinicInfo.getEduration()+"\n");


        cname.setText(cName);
        caddress.setText(add);
        c_duration.setText(cduration);



    }
}
