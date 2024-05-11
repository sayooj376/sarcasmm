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

public class Custom_viewproduct_review extends BaseAdapter {

    private Context context;
    ArrayList<String> p_name,p_image,p_user,p_date,p_review,p_rating;

    SharedPreferences sh;



    public Custom_viewproduct_review(Context applicationContext, ArrayList<String> p_name1, ArrayList<String> p_image1,
                               ArrayList<String> user,ArrayList<String> date,ArrayList<String> review,ArrayList<String> rating) {
        // TODO Auto-generated constructor stub
        this.context=applicationContext;
        this.p_name=p_name1;
        this.p_image=p_image1;
        this.p_user=user;
        this.p_date=date;
        this.p_review=review;
        this.p_rating=rating;
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
            gridView=inflator.inflate(R.layout.activity_custom_viewproduct_review, null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tvname=(TextView)gridView.findViewById(R.id.tvroom);
        ImageView i1=(ImageView) gridView.findViewById(R.id.imgaprtmnt);
        TextView tvuser=(TextView)gridView.findViewById(R.id.tvhall);
        TextView tvdate=(TextView)gridView.findViewById(R.id.tvbathroom);
        TextView tvrating=(TextView)gridView.findViewById(R.id.tvbalcony);
        TextView tvreview=(TextView)gridView.findViewById(R.id.tvbalcony);

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
        tvuser.setText(p_user.get(i));
        tvdate.setText(p_date.get(i));
        tvrating.setText(p_rating.get(i));
        tvreview.setText(p_review.get(i));

        tvname.setTextColor(Color.BLACK);
        tvuser.setTextColor(Color.BLACK);
        tvdate.setTextColor(Color.BLACK);
        tvrating.setTextColor(Color.BLACK);
        tvreview.setTextColor(Color.BLACK);

        return gridView;

    }
}