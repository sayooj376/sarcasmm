package com.example.sarcasm;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
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

public class viewmycart extends AppCompatActivity {
    EditText e1;
    ListView l1;
    Button b1;
    SharedPreferences sh;
    ArrayList<String> product,image,price,quantity,pid;
    String url,pname,url1,oid,amt;
    TextView t1;
    @Override
    public void onBackPressed() {

        Intent ik=new Intent(getApplicationContext(),user_form.class);
        startActivity(ik);
        super.onBackPressed();
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewmycart);

        l1=findViewById(R.id.listproduct);
        b1=findViewById(R.id.button16);
        t1=findViewById(R.id.textView24);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent ik = new Intent(getApplicationContext(),user_form.class);

//                                ik.putExtra("oid",id.get(position));
//                                ik.putExtra("p",d.get(position));
//                                context.startActivity(ik);
//                                Toast.makeText(context, "place order", Toast.LENGTH_SHORT).show();


                ik.putExtra("p",sh.getString("tott",""));

                ik.putExtra("oid",oid);
                Toast.makeText(viewmycart.this, "hhh"+amt, Toast.LENGTH_SHORT).show();
                startActivity(ik);


                
            }
        });
                    url1 = "http://" + sh.getString("ip", "") + ":5000/viewaddtocart";
                    RequestQueue queue = Volley.newRequestQueue(viewmycart.this);

                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url1, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the response string.
                            Log.d("+++++++++++++++++", response);
                            try {

                                JSONObject ar1 = new JSONObject(response);
                                String tp = ar1.getString("total");
                                SharedPreferences.Editor edp = sh.edit();
                                edp.putString("tott", tp);
                                edp.commit();
                                t1.setText(sh.getString("tott", ""));
                                JSONArray ar = new JSONArray(ar1.getString("data"));
//                    Toast.makeText(viewmycart.this, "err"+response, Toast.LENGTH_SHORT).show();

                                    product = new ArrayList<>();
                                    price = new ArrayList<>();
                                    quantity = new ArrayList<>();
                                    image = new ArrayList<>();
                                    pid = new ArrayList<>();


                                    for (int i = 0; i < ar.length(); i++) {
                                        JSONObject jo = ar.getJSONObject(i);
                                        product.add(jo.getString("product"));
                                        price.add(jo.getString("price"));
                                        quantity.add(jo.getString("quantity"));
                                        pid.add(jo.getString("pid"));
                                        image.add(jo.getString("image"));
                                        oid = jo.getString("oid");
                                        amt = jo.getString("amt");


                                    }

                                    // ArrayAdapter<String> ad=new ArrayAdapter<>(Home.this,android.R.layout.simple_list_item_1,name);
                                    //lv.setAdapter(ad);

                                    l1.setAdapter(new custtomviewmycart(viewmycart.this, product, image, price, quantity, pid));

//                    l1.setOnItemClickListener(viewuser.this);

                                } catch(Exception e){
                                    Log.d("=========", e.toString());
                                }


                        }

                    }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {

//                            Toast.makeText(viewmycart.this, "err" + error, Toast.LENGTH_SHORT).show();
                        }
                    }) {
                        @Override
                        protected Map<String, String> getParams() {
                            Map<String, String> params = new HashMap<>();
                            params.put("lid", sh.getString("lid",""));
//                        params.put("sid",sid);


                            return params;
                        }
                    };
                    queue.add(stringRequest);

                }

            }

