package com.example.sarcasm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class order extends AppCompatActivity {
    EditText e1;
    Button b1;
    SharedPreferences sh;
    String name,image,price,pid,d;
    String url,quantity;


    @Override
    public void onBackPressed() {

        Intent ik=new Intent(getApplicationContext(),user_form.class);
        startActivity(ik);
        super.onBackPressed();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.order);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        name=getIntent().getStringExtra("productname");
        image=getIntent().getStringExtra("image");
        price=getIntent().getStringExtra("price");
        pid=getIntent().getStringExtra("pid");
        d=getIntent().getStringExtra("d");

        e1=findViewById(R.id.editTextTextPersonName7);
        b1=findViewById(R.id.button29);
        TextView tv1=(TextView)findViewById(R.id.names);
        ImageView i1=(ImageView) findViewById(R.id.imgaprtmnt);
        TextView tv2=(TextView)findViewById(R.id.prices);
        TextView tv3=(TextView)findViewById(R.id.stocks);
        java.net.URL thumb_u;
        try {

            //thumb_u = new java.net.URL("http://192.168.43.57:5000/static/photo/flyer.jpg");

            thumb_u = new java.net.URL("http://"+sh.getString("ip","")+":5000"+image);
            Drawable thumb_d = Drawable.createFromStream(thumb_u.openStream(), "");
            i1.setImageDrawable(thumb_d);

        }
        catch (Exception e)
        {
            Log.d("errsssssssssssss",""+e);
        }


        tv1.setText(name);
        tv2.setText(price);
        tv3.setText(d);
//        tv4.setText(price);
//        tv5.setText(d);
//        tv6.setText(e);
//        tv7.setText(f);


        tv1.setTextColor(Color.BLACK);
        tv2.setTextColor(Color.BLACK);
        tv3.setTextColor(Color.BLACK);
//        tv4.setTextColor(Color.BLACK);
//        tv5.setTextColor(Color.BLACK);
//        tv6.setTextColor(Color.BLACK);
//        tv7.setTextColor(Color.BLACK);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                quantity=e1.getText().toString();

//                Intent i=new Intent(getApplicationContext(),home.class);
//                startActivity(i);

                RequestQueue queue = Volley.newRequestQueue(order.this);
                url = "http://" + sh.getString("ip","") + ":5000/orders";

                // Request a string response from the provided URL.
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        // Display the response string.
                        Log.d("+++++++++++++++++", response);
                        try {
                            JSONObject json = new JSONObject(response);
                            String res = json.getString("task");

                            if (res.equalsIgnoreCase("valid")) {
                                Intent ik = new Intent(getApplicationContext(), user_form.class);
                                startActivity(ik);

                                Toast.makeText(order.this, "Ordered!!", Toast.LENGTH_SHORT).show();
                                String oid = json.getString("oid");
                                SharedPreferences.Editor edp = sh.edit();
                                edp.putString("oid", oid);
                                edp.commit();
//                                /////////////////////////////////////

//                                /////////////////////////////////////
//                                Intent ik = new Intent(getApplicationContext(), addaddress.class);
//                                ik.putExtra("oid",oid);
//                                ik.putExtra("p",price);
//
//                                startActivity(ik);



                            } else {

                                Toast.makeText(order.this, "Not ordered!!", Toast.LENGTH_SHORT).show();

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {


                        Toast.makeText(getApplicationContext(), "Error" + error, Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Override
                    protected Map<String, String> getParams() {
                        Map<String, String> params = new HashMap<String, String>();
                        params.put("lid", sh.getString("lid",""));
                        params.put("pid", sh.getString("pid",""));
//                        params.put("offer", sh.getString("pid",""));
                        params.put("quantity", quantity);


                        return params;
                    }
                };
                queue.add(stringRequest);

            }
        });





    }

}