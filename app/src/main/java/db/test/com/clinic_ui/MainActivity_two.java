package db.test.com.clinic_ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity_two extends AppCompatActivity implements SearchView.OnQueryTextListener {

    // Declare Variables
    private ListView list;
    private ListViewAdapter adapter;
    private SearchView editsearch;
    public DatabaseAccess db;
    public Toolbar tb;
    public static ArrayList<Doctor> doctorName = new ArrayList<Doctor>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_two);
        setTitle("Clinic Finder");

        // Locate the ListView in listview_main.xml
        DatabaseAccess databaseAccess=DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        list = (ListView) findViewById(R.id.listview);
       // ImageView image=(ImageView)findViewById(R.id.)
        final String passedArg = getIntent().getExtras().getString("arg");
        doctorName=databaseAccess.getDoctorName(passedArg);

        sortDoctorArrayList(doctorName);



        adapter = new ListViewAdapter(this, passedArg);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);

        // Locate the EditText in listview_main.xml
        editsearch = (SearchView) findViewById(R.id.search);
        editsearch.setOnQueryTextListener(this);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

               // Toast.makeText(MainActivity_two.this, doctorName.get(position).getName(), Toast.LENGTH_SHORT).show();
                Intent i=new Intent(MainActivity_two.this,Result_page.class);
                Bundle b = new Bundle();
                b.putInt("id",doctorName.get(position).getId() );
                b.putString("name",doctorName.get(position).getName());
               // b.putString("arg",passedArg);
                i.putExtras(b);
                startActivity(i);

                //startActivity(i);

            }
        });
    }

    private void sortDoctorArrayList(ArrayList<Doctor> list) {
        Collections.sort(list, new Comparator<Doctor>() {
            @Override
            public int compare(Doctor doctor, Doctor d) {

                return doctor.getName().compareTo(d.getName());
            }
        });

    }

    @Override
    public boolean onQueryTextSubmit(String query) {

        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        String text = newText;
        adapter.filter(text);
        return false;
    }
}

