package db.test.com.clinic_ui;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class MainActivity_three extends AppCompatActivity implements SearchView.OnQueryTextListener {

    // Declare Variables
    private ListView list;
    private ListViewAdapter_c adapter;
    private SearchView editsearch;
    public DatabaseAccess d;
    public Toolbar tb;
    public static ArrayList<Clinic> clinicName = new ArrayList<Clinic>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_three);


        DatabaseAccess databaseAccess=DatabaseAccess.getInstance(getApplicationContext());
        databaseAccess.open();
        list = (ListView) findViewById(R.id.listview3);
        final String passedArg = getIntent().getExtras().getString("arg");
        clinicName = databaseAccess.getClinicName(passedArg);
        sortClinicArrayList(clinicName);

        // Pass results to ListViewAdapter Class
        adapter = new ListViewAdapter_c(this);

        // Binds the Adapter to the ListView
        list.setAdapter(adapter);

        // Locate the EditText in listview_main.xml
        editsearch = (SearchView) findViewById(R.id.search3);
        editsearch.setOnQueryTextListener(this);

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Intent i=new Intent(MainActivity_three.this,Clinic_Result_page.class);
                Bundle b = new Bundle();
                b.putInt("id",clinicName.get(position).getCid() );
                //b.putString("arg",passedArg);
               // b.putString("cName",cName);
               // b.putString("caddress",caddress);
                //b.putString("cduration",cduration);
                i.putExtras(b);
                startActivity(i);
                //Toast.makeText(MainActivity_three.this, clinicName.get(position).getCname(), Toast.LENGTH_SHORT).show();


            }
        });
    }

    private void sortClinicArrayList(ArrayList<Clinic> list) {
        Collections.sort(list, new Comparator<Clinic>() {
            @Override
            public int compare(Clinic c1, Clinic c2) {

                return c1.getCname().compareTo(c2.getCname());
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



