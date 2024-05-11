package com.example.sarcasm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class Custom_cart extends BaseAdapter {

    private Context context;
    ArrayList<String> p_name,p_image,quantity,status,date;

    SharedPreferences sh;



    public Custom_cart(Context applicationContext, ArrayList<String> p_name1, ArrayList<String> p_image1, ArrayList<String> quantity1
                              ,ArrayList<String> status1,ArrayList<String> date1) {
        // TODO Auto-generated constructor stub
        this.context=applicationContext;
        this.p_name=p_name1;
        this.p_image=p_image1;
        this.quantity=quantity1;
        this.status=status1;
        this.date=date1;
        sh= PreferenceManager.getDefaultSharedPreferences(applicationContext);
    }

    @Override
    public int getCount() {
        return p_name.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        // TODO Auto-generated method stub
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(view==null)
        {
            gridView=new View(context);
            gridView=inflator.inflate(R.layout.activity_custom_cart, null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tvname=(TextView)gridView.findViewById(R.id.tvroom);
        ImageView i1=(ImageView) gridView.findViewById(R.id.imgaprtmnt);
        TextView tvdate=(TextView)gridView.findViewById(R.id.tvhall);
        TextView tvquantity=(TextView)gridView.findViewById(R.id.textView37);
        TextView tvstatus=(TextView)gridView.findViewById(R.id.textView14);

        if (android.os.Build.VERSION.SDK_INT > 9) {
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        java.net.URL thumb_u;
        try {

            //thumb_u = new java.net.URL("http://192.168.43.57:5000/static/photo/flyer.jpg");

            thumb_u = new java.net.URL("http://"+sh.getString("ip","")+":5000"+p_image.get(i));
            Drawable thumb_d = Drawable.createFromStream(thumb_u.openStream(), "src");
            i1.setImageDrawable(thumb_d);

        }
        catch (Exception e)
        {
            Log.d("errsssssssssssss",""+e);
        }


        tvname.setText(p_name.get(i));
        tvstatus.setText(status.get(i));
        tvquantity.setText(quantity.get(i));
        tvdate.setText(date.get(i));

        tvname.setTextColor(Color.BLACK);
        tvstatus.setTextColor(Color.BLACK);
        tvquantity.setTextColor(Color.BLACK);
        tvdate.setTextColor(Color.BLACK);


        return gridView;

    }
}