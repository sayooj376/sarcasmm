package com.example.sarcasm;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class custtomviewmycart extends BaseAdapter {
    private Context context;
    ArrayList<String> a;
    ArrayList<String> b;
    ArrayList<String> c;
    ArrayList<String> d;
    ArrayList<String> e;
//    ArrayList<String> f;
//    ArrayList<String> g;
//    ArrayList<String> h;
//    ArrayList<String> pid;
//    ArrayList<String> offer;
//    ArrayList<String> np;

    SharedPreferences sh;



    public custtomviewmycart(Context applicationContext, ArrayList<String> a, ArrayList<String> b, ArrayList<String> c, ArrayList<String> d, ArrayList<String> e) {
        // TODO Auto-generated constructor stub
        this.context=applicationContext;
        this.a=a;
        this.b=b;
        this.c=c;
        this.d=d;
        this.e=e;
//        this.f=f;
//        this.g=g;
//        this.h=h;
//        this.pid=pid;
//        this.offer=offer;
//        this.np=np;

        sh=PreferenceManager.getDefaultSharedPreferences(applicationContext);
    }

    @Override
    public int getCount() {
        // TODO Auto-generated method stub
        return a.size();
    }

    @Override
    public Object getItem(int arg0) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public long getItemId(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }
    @Override
    public int getItemViewType(int arg0) {
        // TODO Auto-generated method stub
        return 0;
    }


    @Override
    public View getView(int position, View convertview, ViewGroup parent) {
        // TODO Auto-generated method stub
        LayoutInflater inflator=(LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View gridView;
        if(convertview==null)
        {
            gridView=new View(context);
            gridView=inflator.inflate(R.layout.activity_custtomviewmycart, null);

        }
        else
        {
            gridView=(View)convertview;

        }
        TextView tv1=(TextView)gridView.findViewById(R.id.name);
        ImageView i1=(ImageView) gridView.findViewById(R.id.images);
//        ImageView i2=(ImageView) gridView.findViewById(R.id.imageView);
        TextView tv2=(TextView)gridView.findViewById(R.id.price);
        TextView tv3=(TextView)gridView.findViewById(R.id.stock);
//        TextView tv4=(TextView)gridView.findViewById(R.id.tvbalcony);
//        TextView tv5=(TextView)gridView.findViewById(R.id.stock1);
//        TextView tv6=(TextView)gridView.findViewById(R.id.quantity1);
//        TextView tv7=(TextView)gridView.findViewById(R.id.unit1);
//        TextView tv8=(TextView)gridView.findViewById(R.id.offer1);
//        TextView tv9=(TextView)gridView.findViewById(R.id.offerp1);

//        TextView tvof=(TextView)gridView.findViewById(R.id.offer);
//        TextView tvnp=(TextView)gridView.findViewById(R.id.offerp);
        Button b1=(Button) gridView.findViewById(R.id.button30);

        float i=Float.parseFloat(d.get(position)) * Float.parseFloat(c.get(position));
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent i=new Intent(context,order.class);
//                i.putExtra("pid",e.get(position));
//                SharedPreferences.Editor edp = sh.edit();
//                edp.putString("pid", e.get(position));
//                edp.commit();
//
//                i.putExtra("productname",a.get(position));
//                i.putExtra("image",b.get(position));
//                i.putExtra("price",c.get(position));
//                i.putExtra("d",d.get(position));
////                i.putExtra("e",e.get(position));
////                i.putExtra("f",f.get(position));
////                i.putExtra("g",g.get(position));
////                i.putExtra("h",h.get(position));
//                context.startActivity(i);

                RequestQueue queue = Volley.newRequestQueue(context);
                String url = "http://" + sh.getString("ip", "") + ":5000/cancel_s_order";

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the response string.
                        Log.d("+++++++++++++++++", response);
                        try {
//                                Toast.makeText(Login.this, response, Toast.LENGTH_SHORT).show();
                            JSONObject json = new JSONObject(response);
                            String res = json.getString("task");
//                            Toast.makeText(Login.this, sh.getString("ip","")+"", Toast.LENGTH_SHORT).show();

                            if (res.equalsIgnoreCase("valid")) {
//                                String type = json.getString("type");
//
//                                String lid = json.getString("id");
//                                SharedPreferences.Editor edp = sh.edit();
//                                edp.putString("lid", lid);
//                                edp.commit();
//                                if (type.equalsIgnoreCase("user")) {
                                Toast.makeText(context, "deleted", Toast.LENGTH_SHORT).show();

                                    Intent ik = new Intent(context.getApplicationContext(),user_form.class);
                                    context.startActivity(ik);
                                }

                            else {
                                Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
                            }
                        }
                        catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        Toast.makeText(context.getApplicationContext(), "Error" + error, Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("oid",e.get(position));
//                        params.put("qty", d.get(position));
//                        params.put("amt", i+"");

                        return params;
                    }
                };
                queue.add(stringRequest);





            }
        });
//        b2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent i=new Intent(context,cart.class);
//                i.putExtra("pid",pid.get(position));
//                SharedPreferences.Editor edp = sh.edit();
//                edp.putString("pid", pid.get(position));
//                edp.commit();
//
//                i.putExtra("name",a.get(position));
//                i.putExtra("image",b.get(position));
//                i.putExtra("price",c.get(position));
//                i.putExtra("d",d.get(position));
////                i.putExtra("e",e.get(position));
////                i.putExtra("f",f.get(position));
////                i.putExtra("g",g.get(position));
////                i.putExtra("h",h.get(position));
//                context.startActivity(i);
//            }
//        });


//        b2.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//
//                RequestQueue queue = Volley.newRequestQueue(context);
//                url = "http://" + sh.getString("ip", "") + ":5000/add_to_cart";
//
//                // Request a string response from the provided URL.
//                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        // Display the response string.
//                        Log.d("+++++++++++++++++", response);
//                        try {
////                                Toast.makeText(Login.this, response, Toast.LENGTH_SHORT).show();
//                            JSONObject json = new JSONObject(response);
//                            String res = json.getString("task");
////                            Toast.makeText(Login.this, sh.getString("ip","")+"", Toast.LENGTH_SHORT).show();
//
//
//                            if (res.equalsIgnoreCase("valid")) {
//
////
//
//                                Intent ik = new Intent(context, userhome.class);
//                                context.startActivity(ik);
//                                Toast.makeText(context, "Add to cart", Toast.LENGTH_SHORT).show();
//
////
//
//                            } else {
//
//                                Toast.makeText(context, "Not Added ", Toast.LENGTH_SHORT).show();
//
//                            }
//                        } catch (JSONException e) {
//                            e.printStackTrace();
//                        }
//
//
//                    }
//                }, new Response.ErrorListener() {
//                    @Override
//                    public void onErrorResponse(VolleyError error) {
//
//
//                        Toast.makeText(context, "Error" + error, Toast.LENGTH_LONG).show();
//                    }
//                }) {
//                    @Override
//                    protected Map<String, String> getParams() {
//                        Map<String, String> params = new HashMap<String, String>();
////                        params.put("shid",e.get(position));
//                        params.put("lid", sh.getString("lid", ""));
//
//                        return params;
//                    }
//                };
//                queue.add(stringRequest);
//
//            }
//
//        });


        if(Build.VERSION.SDK_INT>9)
        {
            StrictMode.ThreadPolicy policy=new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
        }

        java.net.URL thumb_u;
        try {

            //thumb_u = new java.net.URL("http://192.168.43.57:5000/static/photo/flyer.jpg");

            thumb_u = new java.net.URL("http://"+sh.getString("ip","")+":5000/media/"+b.get(position));
//            Toast.makeText(context, b.get(position)+"err", Toast.LENGTH_SHORT).show();

            Drawable thumb_d = Drawable.createFromStream(thumb_u.openStream(), "");
            i1.setImageDrawable(thumb_d);

//            thumb_u = new java.net.URL("http://"+sh.getString("ip","")+":5000/media"+b.get(position));
//            Drawable thumb_d1 = Drawable.createFromStream(thumb_u.openStream(), "");
//            i1.setImageDrawable(thumb_d1);
////
        }
        catch (Exception e)
        {
            Log.d("errsssssssssssss",""+e);
        }


        tv1.setText(a.get(position));
        tv2.setText(c.get(position)+"");
        tv3.setText(d.get(position));
//        tv4.setText(c.get(position));
//        tv5.setText(d.get(position));
//        tv6.setText(e.get(position));
//        tv7.setText(f.get(position));
//        if(offer.get(position).equalsIgnoreCase("0"))
//        {
//            tv8.setVisibility(View.GONE);
//            tv9.setVisibility(View.GONE);
//            tvof.setVisibility(View.GONE);
//            tvnp.setVisibility(View.GONE);
//            i2.setVisibility(View.GONE);
//            tv4.setTextColor(Color.BLACK);
//        }
//        else {
//            tv8.setVisibility(View.VISIBLE);
//            tv9.setVisibility(View.VISIBLE);
//            tvof.setVisibility(View.VISIBLE);
//            tvnp.setVisibility(View.VISIBLE);
//            i2.setVisibility(View.VISIBLE);
//            tv8.setText(offer.get(position));
//            tv9.setText(np.get(position));
//            tv4.setTextColor(Color.RED);
//        }




        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);

//        tv5.setTextColor(Color.BLACK);
//        tv6.setTextColor(Color.BLACK);
//        tv7.setTextColor(Color.BLACK);
//        tv8.setTextColor(Color.BLACK);
//        tv9.setTextColor(Color.BLACK);











        return gridView;

    }

}





