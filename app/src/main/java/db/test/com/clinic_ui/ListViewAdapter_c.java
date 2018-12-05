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

public class ListViewAdapter_c extends BaseAdapter {

    // Declare Variables

    Context mContext;
    LayoutInflater inflater;
    private ArrayList<Clinic> arraylist;

    public ListViewAdapter_c(Context context ) {
        mContext = context;
        inflater = LayoutInflater.from(mContext);
        this.arraylist = new ArrayList<Clinic>();
        this.arraylist.addAll(MainActivity_three.clinicName);
    }

    public class ViewHolder {
        TextView name;
        TextView price;
        TextView date;
        ImageView img;
    }

    @Override
    public int getCount() {
        return MainActivity_three.clinicName.size();
    }

    @Override
    public Clinic getItem(int position) {
        return MainActivity_three.clinicName.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public View getView(final int position, View view, ViewGroup parent) {
        final ViewHolder holder;
        if (view == null) {
            holder = new ViewHolder();
            view = inflater.inflate(R.layout.listview_item, null);
            // Locate the TextViews in listview_item.xml
            holder.name = (TextView) view.findViewById(R.id.name);
            holder.img=(ImageView)view.findViewById(R.id.img);
            // holder.date=(TextView)view.findViewById(R.id.date);
            // holder.price=(TextView)view.findViewById(R.id.price);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        // Set the results into TextViews
        holder.name.setText(MainActivity_three.clinicName.get(position).getCname());
        //holder.img.setImageResource(R.mipmap.ic_gp);
        //holder.date.setText(MainActivity.movieNamesArrayList.get(position).getDate());
        // holder.price.setText(MainActivity.movieNamesArrayList.get(position).getPrice());
        return view;
    }

    // Filter Class
    public void filter(String charText) {
        charText = charText.toLowerCase(Locale.getDefault());
        MainActivity_three.clinicName.clear();
        if (charText.length() == 0) {
            MainActivity_three.clinicName.addAll(arraylist);
        } else {
            for (Clinic wp : arraylist) {
                if (wp.getCname().toLowerCase(Locale.getDefault()).contains(charText)) {
                    MainActivity_three.clinicName.add(wp);
                }
            }
        }
        notifyDataSetChanged();
    }

}
