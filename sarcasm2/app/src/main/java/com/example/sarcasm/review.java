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
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.android.volley.DefaultRetryPolicy;
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

public class review extends AppCompatActivity {
    EditText e1;
    RatingBar R1;
    Button b1;

    SharedPreferences sh;
    String review;
    String rating;

    @Override
    public void onBackPressed() {

        Intent ik=new Intent(getApplicationContext(),user_form.class);
        startActivity(ik);
        super.onBackPressed();
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        e1=findViewById(R.id.editTextTextPersonName11);
//        R1=findViewById(R.id.ratingBar);
        b1=findViewById(R.id.button4);
        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                review = e1.getText().toString();
//                rating = R1.getRating()+"";
                if(review.equalsIgnoreCase(  ""))
                {
                    e1.setError("Missing");
                }
//                else if(rating.equalsIgnoreCase(""))
//                {
//                    e1.setError("set Rating");
//                }
                else {

                    RequestQueue queue = Volley.newRequestQueue(review.this);
                    String url = "http://" + sh.getString("ip", "") + ":5000/review";
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the response string.
                            Log.d("+++++++++++++++++", response);
                            try {
                                JSONObject json = new JSONObject(response);
                                String res = json.getString("task");

                                if (res.equalsIgnoreCase("ok")) {
                                    Toast.makeText(getApplicationContext(), "Rating sent", Toast.LENGTH_LONG).show();
                                    Intent in = new Intent(getApplicationContext(), view_product.class);

                                    startActivity(in);

                                } else {
                                    Toast.makeText(getApplicationContext(), "Not Sent", Toast.LENGTH_LONG).show();

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
                            params.put("review", review);
//                            params.put("rating", rating);
                            params.put("pid", getIntent().getStringExtra("pid"));


                            return params;
                        }
                    };
                    // Add the request to the RequestQueue.
                    stringRequest.setRetryPolicy(new DefaultRetryPolicy(
                            20000,
                            DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
                            DefaultRetryPolicy.DEFAULT_BACKOFF_MULT)
                    );
                    queue.add(stringRequest);
                }


            }
        });


    }
}