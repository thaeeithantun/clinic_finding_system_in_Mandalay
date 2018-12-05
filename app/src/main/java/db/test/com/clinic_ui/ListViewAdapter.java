package db.test.com.clinic_ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Locale;

public class ListViewAdapter extends BaseAdapter {

    // Declare Variables

    Context mContext;
    LayoutInflater inflater;
    private ArrayList<Doctor> arraylist;
    public static String type="";

    public ListViewAdapter(Context context ) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<Doctor>();
        this.arraylist.addAll(MainActivity_two.doctorName);
    }

    public ListViewAdapter(Context context, String type){
        mContext = context;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<Doctor>();
        this.arraylist.addAll(MainActivity_two.doctorName);
        this.type=type;
    }

    public class ViewHolder {
        TextView name;
        TextView price;
        TextView date;
        ImageView img;
    }

    @Override
    public int getCount() {
        return MainActivity_two.doctorName.size();
    }

    @Override
    public Doctor getItem(int position) {
        return MainActivity_two.doctorName.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listview2, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.name);
            holder.img=(ImageView)view.findViewById(R.id.image);
            // holder.date=(TextView)view.findViewById(R.id.date);
            // holder.price=(TextView)view.findViewById(R.id.price);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(MainActivity_two.doctorName.get(position).getName());
        //holder.img.setImageResource(R.mipmap.ic_child);

        if(type.equals("c")){
            holder.img.setImageResource(R.mipmap.ic_child);
        }
        else if(type.equals("gp")){
            holder.img.setImageResource(R.mipmap.ic_gp);
        }
        else if(type.equals("e")){
            holder.img.setImageResource(R.mipmap.ic_eye);
        }

        else if(type.equals("ent")){
            holder.img.setImageResource(R.mipmap.ic_ent);
        }

        else if(type.equals("d")){
            holder.img.setImageResource(R.mipmap.ic_dental);
        }

        else if(type.equals("og")){
            holder.img.setImageResource(R.mipmap.ic_og);
        }

        else if(type.equals("s")){
            holder.img.setImageResource(R.mipmap.ic_skin);
        }

        else if(type.equals("st")){
            holder.img.setImageResource(R.mipmap.ic_stomach);
        }

        else if(type.equals("b")){
            holder.img.setImageResource(R.mipmap.ic_bone);
        }


        //holder.date.setText(MainActivity.movieNamesArrayList.get(position).getDate());
        // holder.price.setText(MainActivity.movieNamesArrayList.get(position).getPrice());
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        MainActivity_two.doctorName.clear();
        if (charText.length() == 0) {
            MainActivity_two.doctorName.addAll(arraylist);
        } else {
            for (Doctor wp : arraylist) {
                if (wp.getName().toLowerCase(Locale.getDefault()).contains(charText)) {
                    MainActivity_two.doctorName.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}
