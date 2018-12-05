package db.test.com.clinic_ui;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class Three_button extends AppCompatActivity implements View.OnClickListener {
    Button doctor_btn,clinic_btn,btn_clinic_search,btn_custom_time;
    Toolbar tb;
    public String a=new String();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.two_btn);
        //setTitle("Clinic Finder");


        doctor_btn=(Button)findViewById(R.id.doctor_search);
        clinic_btn=(Button)findViewById(R.id.clinic_search);
        btn_clinic_search=(Button)findViewById(R.id.searchByClinicName);
        btn_custom_time=(Button)findViewById(R.id.clinic_search_custom_time);
        doctor_btn.setOnClickListener(this);
        clinic_btn.setOnClickListener(this);
        btn_clinic_search.setOnClickListener(this);
        btn_custom_time.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {

        switch (view.getId()) {

            case R.id.doctor_search:
                // do your code
                String passedArg = getIntent().getExtras().getString("arg");
                a=passedArg;
                Intent i1 = new Intent(this, MainActivity_two.class);
                i1.putExtra("arg",a );
                startActivity(i1);
                break;

            case R.id.searchByClinicName:
                // do your code
                String passedArg1 = getIntent().getExtras().getString("arg");
                a=passedArg1;
                Intent i2 = new Intent(this, MainActivity_three.class);
                i2.putExtra("arg",a );
                startActivity(i2);
                break;



            case R.id.clinic_search:
                // do your code
                String passedArg2 = getIntent().getExtras().getString("arg");
                a=passedArg2;
                Intent i3 = new Intent(this, currentLocation.class);
                i3.putExtra("arg", a);
                startActivity(i3);
                break;

            case R.id.clinic_search_custom_time:
                // do your code
                String passedArg3 = getIntent().getExtras().getString("arg");
                a=passedArg3;
                Intent i4 = new Intent(this, Custom_time.class);
                i4.putExtra("arg", a);
                startActivity(i4);
                break;



            default:
                break;
        }

    }

    @Override
    public void onBackPressed() {
        Intent i=new Intent(getApplicationContext(),MainActivity.class);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(i);
    }
}
