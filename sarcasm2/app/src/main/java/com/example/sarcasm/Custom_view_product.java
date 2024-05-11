package com.example.sarcasm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
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
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

public class Custom_view_product extends BaseAdapter {

    private Context context;
    ArrayList<String> p_name,p_price,p_image,p_quantity,p_description,pid,avg;

    SharedPreferences sh;



    public Custom_view_product(Context applicationContext, ArrayList<String> p_name1, ArrayList<String> p_price1,ArrayList<String> p_image1,
                               ArrayList<String> p_quantity1,ArrayList<String> p_description1,ArrayList<String> pid,ArrayList<String> avg) {
        // TODO Auto-generated constructor stub
        this.context=applicationContext;
        this.p_name=p_name1;
        this.p_price=p_price1;
        this.p_image=p_image1;
        this.p_quantity=p_quantity1;
        this.p_description=p_description1;
        this.pid=pid;
        this.avg=avg;
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
            gridView=inflator.inflate(R.layout.activity_custom_view_product, null);

        }
        else
        {
            gridView=(View)view;

        }
        TextView tvname=(TextView)gridView.findViewById(R.id.tvroom);
        ImageView i1=(ImageView) gridView.findViewById(R.id.imgaprtmnt);
        TextView tvprice=(TextView)gridView.findViewById(R.id.tvhall);
        TextView tvquantity=(TextView)gridView.findViewById(R.id.tvbathroom);
        TextView tvdescription=(TextView)gridView.findViewById(R.id.tvbalcony);
        RatingBar ratebar=(RatingBar) gridView.findViewById(R.id.ratingBar2);
        ratebar.setRating(Float.parseFloat(avg.get(i)));
        ratebar.setIsIndicator(true);

        Button b1 =(Button)gridView.findViewById(R.id.button13);
        Button b2=(Button)gridView.findViewById(R.id.button21);
        Button b3=(Button)gridView.findViewById(R.id.button20);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ik=new Intent(context,review.class);
                ik.putExtra("pid",pid.get(i));
                context.startActivity(ik);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii=new Intent(context,cart.class);
                SharedPreferences.Editor edp = sh.edit();
                edp.putString("pid", pid.get(i));
                edp.commit();

                ii.putExtra("pid",pid.get(i));
                ii.putExtra("productname",p_name.get(i));
                ii.putExtra("image",p_image.get(i));
                ii.putExtra("price",p_price.get(i));
                ii.putExtra("d",p_quantity.get(i));
                context.startActivity(ii);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ii=new Intent(context,order.class);
                SharedPreferences.Editor edp = sh.edit();
                edp.putString("pid", pid.get(i));
                edp.commit();

                ii.putExtra("pid",pid.get(i));
                ii.putExtra("productname",p_name.get(i));
                ii.putExtra("image",p_image.get(i));
                ii.putExtra("price",p_price.get(i));
                ii.putExtra("d",p_quantity.get(i));
                context.startActivity(ii);
            }
        });

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
        tvprice.setText(p_price.get(i));
        tvquantity.setText(p_quantity.get(i));
        tvdescription.setText(p_description.get(i));





        tvname.setTextColor(Color.BLACK);
        tvprice.setTextColor(Color.BLACK);
        tvquantity.setTextColor(Color.BLACK);
        tvdescription.setTextColor(Color.BLACK);













        return gridView;

    }
}