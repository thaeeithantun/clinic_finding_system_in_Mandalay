package db.test.com.clinic_ui;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseAccess {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase database;
    private static DatabaseAccess instance;
    Cursor cursor = null;

    /**
     * Private constructor to aboid object creation from outside classes.
     *
     * @param context
     */
    private DatabaseAccess(Context context) {
        this.openHelper = new DatabaseOpenHelper(context);
    }

    /**
     * Return a singleton instance of DatabaseAccess.
     *
     * @param context the Context
     * @return the instance of DabaseAccess
     */
    public static DatabaseAccess getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess(context);
        }
        return instance;
    }

    /**
     * Open the database connection.
     */
    public void open() {
        this.database = openHelper.getWritableDatabase();
    }

    /**
     * Close the database connection.
     */
    public void close() {
        if (database != null) {
            this.database.close();
        }
    }

    public ArrayList<Doctor> getDoctorName(String name) {
        Doctor d;
        ArrayList<Doctor> list=new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from doctor_info where d_special='" + name + "'", new String[]{});

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int drid=(cursor.getInt(0));
                String drName = (cursor.getString(1));

                d=new Doctor(drName,drid);
                list.add(d);
                cursor.moveToNext();
            }
        }
        //sortArrayList(list);

        return list;
    }



    public ArrayList<Doctor> getDoctorInfo(int id, String type){
        Doctor d;
        List<Integer> did=new ArrayList<Integer>();
        ArrayList<Doctor> list = new ArrayList<>();
        Cursor cursor=database.rawQuery("select * from doctor_info where d_special="+type + "and d_id="+id+"'",new String[]{});
        if(cursor.getCount()>0){
            cursor.moveToFirst();
            while(!cursor.isAfterLast()){
                did.add(cursor.getInt(0));
            }
        }
        return  list;
    }

    public ArrayList<Clinic> getClinicName(String name) {
        Clinic c;
        ArrayList<Clinic> list = new ArrayList<>();
        Cursor cursor = database.rawQuery("select * from clinic_info where c_type='" + name + "'", new String[]{});

        if (cursor.getCount() > 0) {
            cursor.moveToFirst();
            while (!cursor.isAfterLast()) {
                int cid=(cursor.getInt(0));
                String cname = (cursor.getString(1));
                String address=(cursor.getString(5));
                c=new Clinic(cname,cid,address);
                list.add(c);
                cursor.moveToNext();
            }
        }


        return list;
    }

    public LatLong getclinicInfo(int id){
        LatLong l=new LatLong();
        ArrayList<LatLong> list=new ArrayList<>();
        Cursor c=database.rawQuery("select * from clinic_info where c_id= '"+id +" '",new String[]{});

        if(c.getCount() > 0){
            c.moveToFirst();
            while(!c.isAfterLast()){
                String cname=c.getString(1);
                Double lat=c.getDouble(3);
                Double lon=c.getDouble(4);
                l=new LatLong(lat,lon,cname,id);
                //list.add(l);
            }
        }
        return l;
    }

    public ArrayList<doctor_clinic> getDoctorClinic(int did){
        doctor_clinic dc=new doctor_clinic();
        ArrayList<doctor_clinic> arrdc=new ArrayList<>();
        String cname="";
        String date="";
        Cursor c=database.rawQuery("select * from clinic_doctor where d_id= '"+did+" '",new String[]{});

        if(c.getCount()>0){
            c.moveToNext();
            while(!c.isAfterLast()){
                int clinic_id=c.getInt(2);
                date=c.getString(3);

                Cursor cc=database.rawQuery("select * from clinic_info where c_id= '"+clinic_id+" '",new String[]{});
                if(cc.getCount()>0){
                    cc.moveToFirst();
                    while(!cc.isAfterLast()){
                        cname=cc.getString(1);
                        cc.moveToNext();
                        dc=new doctor_clinic(cname,date);
                    }
                }// if end

                arrdc.add(dc);
                c.moveToNext();
            }
        }
            return arrdc;
    }

    public Clinic getClinicInfo(int id){
        String mduration="";
        String eduration="";
        Clinic clinic=new Clinic();
        Cursor cc=database.rawQuery("select * from clinic_info where c_id= '"+id +" '",new String[]{});

        if(cc.getCount() > 0){
            cc.moveToFirst();
            while(!cc.isAfterLast()){
                String cname=cc.getString(1);
                String cadddress=cc.getString(5);
                double m_o=cc.getDouble(6);
                double m_c=cc.getDouble(7);
                double e_o=cc.getDouble(8);
                double e_c=cc.getDouble(9);
                if(m_o!=0.0 && m_c!=0.0){
                    int o=(int)m_o;
                    m_o= m_o - o;
                    m_o=m_o*30;

                    int c=(int)m_c;
                    m_c=m_c-c;
                    m_c=m_c*30;
                    mduration=o +":00 to "+c+":00 AM";
                }


                if(e_o!=0.0 && e_c!=0.0){
                    int eo=(int)e_o;
                    e_o= e_o - eo;
                    e_o=(int)e_o*30;


                    int ec=(int)e_c;
                    e_c=e_c-ec;
                    e_c=(int)e_c*30;
                    eduration=eo +":00 to "+ec+":00 PM";
                }

                clinic=new Clinic(id,cname,cadddress,mduration,eduration);
                cc.moveToNext();
                //list.add(l);
            }
        }

        return clinic;
    }




    public ArrayList<LatLong> getlatlong(double time , String type , int day) {
        ArrayList<LatLong> arr = new ArrayList<>();
        //LatLong l;

        //weekend open change clinic
        if(day==1 || day==7){
            if (time <= 11.5) {
                LatLong l=new LatLong();
                //String ctype="gp";
                String selectQuery = "SELECT * FROM clinic_info WHERE m_o <= '" +
                        time + " ' and m_c > '" + time+"' and c_type= '" + type +"' and close_day!= '"+day+"'" ;
                Cursor cursor=database.rawQuery(selectQuery, new String[]{});
                if (cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        String id=(cursor.getString(0));
                        //l.setCid(id);
                        String name=(cursor.getString(1));
                        // l.setCname(name);
                        double lat = (cursor.getDouble(3));
                        //l.setLat(lat);
                        double lon=(cursor.getDouble(4));
                        //l.setLon(lon);
                        String add=(cursor.getString(5));
                        double m_o=(cursor.getDouble(6));
                        double m_c=(cursor.getDouble(7));
                        int o=(int)m_o;
                        m_o= m_o - o;
                        m_o=(int)m_o*30;

                        int c=(int)m_c;
                        m_c=m_c-c;
                        m_c=(int)m_c*30;
                        String duration=o +":"+ m_o+"AM to "+c+":"+ m_c+"AM";
                        l=new LatLong(lat,lon,name,Integer.parseInt(id),0,add,duration);
                        arr.add(l);
                        cursor.moveToNext();

                    }
                }

            }
            else {
                LatLong l=new LatLong();
                //String ctype="gp";
                String selectQuery = "SELECT * FROM clinic_info WHERE e_o <= '" +
                        time + " ' and e_c > '" + time+"' and c_type= '" + type +"' and close_day!= '"+day+"' " ;
                Cursor cursor=database.rawQuery(selectQuery, new String[]{});
                if (cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        String id=(cursor.getString(0));
                        // l.setCid(id);
                        String name=(cursor.getString(1));
                        // l.setCname(name);
                        double lat = (cursor.getDouble(3));
                        // l.setLat(lat);
                        double lon=(cursor.getDouble(4));
                        //l.setLon(lon);
                        String add=(cursor.getString(5));
                        double e_o=(cursor.getDouble(8));
                        double e_c=(cursor.getDouble(9));

                        int o=(int)e_o;
                        e_o= e_o - o;
                        e_o=e_o*30;

                        int c=(int)e_c;
                        e_c=e_c-c;
                        e_c=e_c*30;
                        String duration=o +":"+ e_o+"PM to "+c+":"+ e_c+"PM";

                        l=new LatLong(lat,lon,name,Integer.parseInt(id),0,add,duration);
                        arr.add(l);
                        cursor.moveToNext();

                    }
                }
            }



        }

        //all days not change
        else{


            if (time <= 11.5) {
                LatLong l=new LatLong();
                //String ctype="gp";
                String selectQuery = "SELECT * FROM clinic_info WHERE m_o <= '" +
                        time + " ' and m_c > '" + time+"' and c_type= '" + type +"' and close_day!= '"+day+"'" ;
                Cursor cursor=database.rawQuery(selectQuery, new String[]{});
                if (cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        String id=(cursor.getString(0));
                        //l.setCid(id);
                        String name=(cursor.getString(1));
                        // l.setCname(name);
                        double lat = (cursor.getDouble(3));
                        //l.setLat(lat);
                        double lon=(cursor.getDouble(4));
                        //l.setLon(lon);
                        String add=(cursor.getString(5));
                        double m_o=(cursor.getDouble(6));
                        double m_c=(cursor.getDouble(7));
                        int o=(int)m_o;
                        m_o= m_o - o;
                        int mmo=(int)m_o*30;

                        int c=(int)m_c;
                        m_c=m_c-c;
                        int mmc=(int)m_c*30;
                        String duration=o +":00 to "+c+":00 AM";
                        l=new LatLong(lat,lon,name,Integer.parseInt(id),0,add,duration);
                        arr.add(l);
                        cursor.moveToNext();

                    }
                }

            }
            else {
                LatLong l=new LatLong();
                //String ctype="gp";
                String selectQuery = "SELECT * FROM clinic_info WHERE e_o <= '" +
                        time + " ' and e_c > '" + time+"' and c_type= '" + type +"' and close_day!= '"+day+"' " ;
                Cursor cursor=database.rawQuery(selectQuery, new String[]{});
                if (cursor.getCount() > 0) {
                    cursor.moveToFirst();
                    while (!cursor.isAfterLast()) {
                        String id=(cursor.getString(0));
                        // l.setCid(id);
                        String name=(cursor.getString(1));
                        // l.setCname(name);
                        double lat = (cursor.getDouble(3));
                        // l.setLat(lat);
                        double lon=(cursor.getDouble(4));
                        //l.setLon(lon);
                        String add=(cursor.getString(5));
                        double e_o=(cursor.getDouble(8));
                        double e_c=(cursor.getDouble(9));

                        int o=(int)e_o;
                        e_o= e_o - o;
                        int eeo=(int)e_o*30;

                        int c=(int)e_c;
                        e_c=e_c-c;
                        int eec=(int)e_c*30;
                        String duration=o +":00 to "+c+":00 PM";

                        l=new LatLong(lat,lon,name,Integer.parseInt(id),0,add,duration);
                        arr.add(l);
                        cursor.moveToNext();

                    }
                }
            }



        }

        return arr;

    }
}

