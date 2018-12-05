package db.test.com.clinic_ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button gp,eye,ent,dental,child,og,skin,bone,stomach;
    TextView gpt,eyet,entt,dentalt,childt,ogt,skint,bonet,stomacht;
    Toolbar tb;

    public String a=new String();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       // setTitle("Clinic Finder");



        Typeface mm = Typeface.createFromAsset(getAssets(),  "fonts/Zawgyi-One.ttf");

        gpt=(TextView)findViewById(R.id.gpt);
        gpt.setTypeface(mm);

        eyet=(TextView)findViewById(R.id.eyet);
        eyet.setTypeface(mm);

        entt=(TextView)findViewById(R.id.entt);
        entt.setTypeface(mm);

        dentalt=(TextView)findViewById(R.id.dentalt);
        dentalt.setTypeface(mm);

        childt=(TextView)findViewById(R.id.childt);
        childt.setTypeface(mm);

        ogt=(TextView)findViewById(R.id.ogt);
        ogt.setTypeface(mm);

        skint=(TextView)findViewById(R.id.skint);
        skint.setTypeface(mm);

        bonet=(TextView)findViewById(R.id.bonet);
        bonet.setTypeface(mm);

        stomacht=(TextView)findViewById(R.id.stomacht);
        stomacht.setTypeface(mm);


        gp=(Button)findViewById(R.id.gp);
        gp.setOnClickListener(this);

        eye=(Button)findViewById(R.id.optic);
        eye.setOnClickListener(this);

        ent=(Button)findViewById(R.id.ent);
        ent.setOnClickListener(this);

        dental=(Button)findViewById(R.id.dental);
        dental.setOnClickListener(this);

        child=(Button)findViewById(R.id.child);
        child.setOnClickListener(this);

        og=(Button)findViewById(R.id.og);
        og.setOnClickListener(this);

        skin=(Button)findViewById(R.id.skin);
        skin.setOnClickListener(this);

        bone=(Button)findViewById(R.id.bone);
        bone.setOnClickListener(this);

        stomach=(Button)findViewById(R.id.stomach);
        stomach.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.gp:
                // do your code
                a="gp";
                Intent i1 = new Intent(this, Three_button.class);
                i1.putExtra("arg", a);
                startActivity(i1);
                break;

            case R.id.optic:
                // do your code
                a="e";
                Intent i2 = new Intent(this, Three_button.class);
                i2.putExtra("arg", a);
                startActivity(i2);
                break;

            case R.id.ent:
                // do your code
                a="ent";
                Intent i3 = new Intent(this, Three_button.class);
                i3.putExtra("arg", a);
                startActivity(i3);
                break;

            case R.id.dental:
                // do your code
                a="d";
                Intent i4 = new Intent(this, Three_button.class);
                i4.putExtra("arg", a);
                startActivity(i4);
                break;

            case R.id.child:
                // do your code
                a="c";
                Intent i5 = new Intent(this, Three_button.class);
                i5.putExtra("arg", a);
                startActivity(i5);
                break;

            case R.id.skin:
                // do your code
                a="s";
                Intent i6 = new Intent(this, Three_button.class);
                i6.putExtra("arg", a);
                startActivity(i6);
                break;

            case R.id.bone:
                // do your code
                a="b";
                Intent i7 = new Intent(this, Three_button.class);
                i7.putExtra("arg", a);
                startActivity(i7);
                break;

            case R.id.og:
                // do your code
                a="og";
                Intent i8 = new Intent(this, Three_button.class);
                i8.putExtra("arg", a);
                startActivity(i8);
                break;

            case R.id.stomach:
                // do your code
                a="st";
                Intent i9 = new Intent(this, Three_button.class);
                i9.putExtra("arg", a);
                startActivity(i9);
                break;

            default:
                break;
        }
    }
}
