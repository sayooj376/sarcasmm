package com.example.sarcasm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class complaint extends AppCompatActivity {
    EditText e1;
    Button b1;
    ListView l1;
    SharedPreferences sh;
    String comp;
    String url;
    ArrayList<String>COMPLAINT,reply,date,id;

    @Override
    public void onBackPressed() {

        Intent ik=new Intent(getApplicationContext(),user_form.class);
        startActivity(ik);
        super.onBackPressed();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_complaint);
        e1=findViewById(R.id.editTextTextPersonName12);
        b1=findViewById(R.id.button3);
        l1=findViewById(R.id.L1);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());


        String url1 ="http://"+sh.getString("ip", "") + ":5000/viewcomplaint";

        RequestQueue queue1 = Volley.newRequestQueue(complaint.this);

        StringRequest stringRequest1 = new StringRequest(Request.Method.POST, url1,new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                Toast.makeText(complaint.this, "err"+response, Toast.LENGTH_SHORT).show();

                // Display the response string.
                Log.d("+++++++++++++++++",response);
                try {

                    JSONArray ar=new JSONArray(response);
                    COMPLAINT= new ArrayList<>();
                    reply= new ArrayList<>();
                    date= new ArrayList<>();
                    id= new ArrayList<>();

                    for(int i=0;i<ar.length();i++)
                    {
                        JSONObject jo=ar.getJSONObject(i);
                        COMPLAINT.add(jo.getString("COMPLAINT"));
                        reply.add(jo.getString("reply"));
                        date.add(jo.getString("date"));
                        id.add(jo.getString("id"));



                    }

                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                    //lv.setAdapter(ad);

                    l1.setAdapter(new Custom_complaint_reply(complaint.this,COMPLAINT,reply,date));

                } catch (Exception e) {
                    Log.d("=========", e.toString());
                }


            }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                Toast.makeText(complaint.this, "err"+error, Toast.LENGTH_SHORT).show();
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                Map<String, String> params = new HashMap<>();
                params.put("lid", sh.getString("lid", ""));
                return params;
            }
        };
        queue1.add(stringRequest1);



        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                comp=e1.getText().toString();
                if(comp.equalsIgnoreCase(""))
                {
                    e1.setError("Missing");
                }
                else {

                    RequestQueue queue = Volley.newRequestQueue(complaint.this);
                    String url = "http://" + sh.getString("ip", "") + ":5000/complaint1";
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the response string.
                            Log.d("+++++++++++++++++", response);
                            try {
                                JSONObject json = new JSONObject(response);
                                String res = json.getString("task");

                                if (res.equalsIgnoreCase("success")) {
                                    Toast.makeText(getApplicationContext(), "complaint registered successfully", Toast.LENGTH_LONG).show();
                                    Intent in = new Intent(getApplicationContext(), user_form.class);

                                    startActivity(in);

                                } else {
                                    Toast.makeText(getApplicationContext(), "complaint registration failed", Toast.LENGTH_LONG).show();

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

                            params.put("lid", sh.getString("lid", ""));
                            params.put("complaint", comp);


                            return params;
                        }
                    };
                    // Add the request to the RequestQueue.
                    queue.add(stringRequest);
                }
            }
        });


    }

}