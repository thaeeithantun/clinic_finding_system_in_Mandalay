package db.test.com.clinic_ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class Custom_time extends AppCompatActivity implements View.OnClickListener {
    Button hr_p,hr_m,min_p,min_m,day_p,day_m,find;
    private RadioGroup m_e_gp;
    private RadioButton am,pm;
    TextView hr,min,day;
    SimpleDateFormat simpleDateFormat;
    public static int c_hr,c_min,sec;
    public String time;
    Calendar calander;
    public static int c_day;
    public String a=new String();
    public Toolbar tb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.customtime);

        setTitle("Clinic Finder");



        hr=(TextView)findViewById(R.id.hour);
        min=(TextView)findViewById(R.id.minute);
        //day=(TextView)findViewById(R.id.day);
        m_e_gp=(RadioGroup)findViewById(R.id.m_e);

        am=(RadioButton)findViewById(R.id.am);
        pm=(RadioButton)findViewById(R.id.pm);


        // Getting current time and date
        calander = Calendar.getInstance();
        simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
        c_day= calander.get(Calendar.DAY_OF_WEEK);
        //setDateName(c_day);

        time = simpleDateFormat.format(calander.getTime());
        String[] arrayString = time.split(":");

        c_hr = Integer.parseInt(arrayString[0]);
        c_min = Integer.parseInt(arrayString[1]);
        sec = Integer.parseInt(arrayString[2]);

        if(c_hr > 12){
            c_hr=c_hr-12;
            pm.setChecked(true);
        }
        else{
            am.setChecked(true);
        }

        setHr(c_hr);
        setMin(c_min);



        hr_p=(Button)findViewById(R.id.hr_plus);
        hr_m=(Button)findViewById(R.id.hr_minus);
        hr_p.setOnClickListener(this);
        hr_m.setOnClickListener(this);


        min_p=(Button)findViewById(R.id.min_plus);
        min_m=(Button)findViewById(R.id.min_minus);
        min_p.setOnClickListener(this);
        min_m.setOnClickListener(this);

        //day_p=(Button)findViewById(R.id.day_plus);
        //day_m=(Button)findViewById(R.id.day_minus);
        //day_p.setOnClickListener(this);
        //day_m.setOnClickListener(this);

        find=(Button)findViewById(R.id.btn_find);
        find.setOnClickListener(this);









    }

    private void setHr(int h) {
        hr.setText(Integer.toString(h));
    }

    private void setMin(int m)
    {
        if(m<10){
            min.setText("0");
            min.append(Integer.toString(m));
        }
        else{min.setText(Integer.toString(m));};
    }

    private void setDateName(int c_day) {
        if(c_day==1){
            day.setText("Sunday");
        }
        else if(c_day==2){
            day.setText("Monday");
        }
        else if(c_day==3){
            day.setText("Tuesday");
        }
        else if(c_day==4){
            day.setText("Wednesday");
        }
        else if(c_day==5){
            day.setText("Thursday");
        }
        else if(c_day==6){
            day.setText("Friday");
        }
        else if(c_day==7){
            day.setText("Saturday");
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.hr_plus:
                // do your code
                Hr_plus();

                break;

            case R.id.hr_minus:
                // do your code
                Hr_minus();

                break;



            case R.id.min_plus:
                // do your code
                Min_plus();

                break;

            case R.id.min_minus:
                // do your code
                Min_minus();

                break;



            case R.id.btn_find:
                // do your code
                /*int selectedId = m_e_gp.getCheckedRadioButtonId();

                // find the radiobutton by returned id
                am = (RadioButton) findViewById(selectedId);
                String ap= (String) am.getText();
                if(ap=="PM"){
                    c_hr=c_hr+12;
                }*/
                if(pm.isChecked()){
                    c_hr=c_hr+12;
                }

                Toast.makeText(this,c_hr+"hr "+c_min+" min ",Toast.LENGTH_LONG);
                String passedArg = getIntent().getExtras().getString("arg");
                Intent i=new Intent(Custom_time.this,CustomTime_latlong.class);
                Bundle b = new Bundle();

                //b.putDouble("lat",location.getLatitude());
                //b.putDouble("long",location.getLongitude());
                b.putInt("hr",c_hr);
                b.putInt("min",c_min);
                b.putInt("currentday",c_day);
                b.putString("c_type",passedArg);

                i.putExtras(b);
                startActivity(i);
                break;



            default:
                break;
        }
    }

    private void Hr_plus() {

        c_hr=c_hr+1;
        if(c_hr<=12){
            setHr(c_hr);
        }
        if(c_hr>12){
            c_hr=1;
            setHr(c_hr);
        }

    }

    private void Hr_minus() {
        c_hr=c_hr-1;
        if(c_hr>=1){
            setHr(c_hr);
        }
        if(c_hr<1){
            c_hr=12;
            setHr(c_hr);
        }
    }

    private void Min_plus() {
        c_min=c_min+1;
        if(c_min<=59){

            setMin(c_min);
        }
        if(c_min>=60){
            c_min=0;
            setMin(c_min);
        }
    }
    private void Min_minus() {
        c_min=c_min-1;
        if(c_min>=1){
            setMin(c_min);
        }
        if(c_min<=0){
            c_min=59;
            setMin(c_min);
        }

    }

    private void Day_plus() {
        c_day=c_day+1;
        if(c_day<=7){
            setDateName(c_day);
        }
        if(c_day>=8){
            c_day=1;
            setDateName(c_day);
        }

    }

    private void Day_minus() {
        c_day=c_day-1;
        if(c_day>=1){
            setDateName(c_day);
        }
        if(c_day<=0){
            c_day=7;
            setDateName(c_day);
        }
    }




}
