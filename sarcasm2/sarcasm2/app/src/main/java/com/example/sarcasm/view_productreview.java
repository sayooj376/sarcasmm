package com.example.sarcasm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class view_productreview extends AppCompatActivity {
    ListView l1;
    SharedPreferences sh;
    String url;
    ArrayList<String>PRODUCT,image,USER,date,review,rating;

    @Override
    public void onBackPressed() {

        Intent ik=new Intent(getApplicationContext(),user_form.class);
        startActivity(ik);
        super.onBackPressed();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_productreview);
        l1=findViewById(R.id.viewproduct);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

         url = "http://" + sh.getString("ip", "") + ":5000/view_review";
        RequestQueue queue = Volley.newRequestQueue(view_productreview.this);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);

                    PRODUCT= new ArrayList<>();
                    image= new ArrayList<>();
                    USER= new ArrayList<>();
                    date=new ArrayList<>();
                    review=new ArrayList<>();
                    rating=new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        PRODUCT.add(jo.getString("p_name"));
                        image.add(jo.getString("p_image"));
                        USER.add(jo.getString("u_name"));
                        date.add(jo.getString("date"));
                        review.add(jo.getString("review"));
                        rating.add(jo.getString("rating"));


                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    l1.setAdapter(new Custom_viewproduct_review(view_productreview.this ,PRODUCT,image,USER,date,review,rating));

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(view_productreview.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("lid", sh.getString("lid", ""));
                return params;
            }
        };
        queue.add(stringRequest);
    }

}