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
import android.widget.Toast;

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

public class sign_up extends AppCompatActivity {
    EditText e1,e2,e3,e4,e5,e6,e7,e8;
    String name,place,post,pin,phone,email,username,password;
    Button b1;
    SharedPreferences sh;
    @Override
    public void onBackPressed() {

        Intent ik=new Intent(getApplicationContext(),login.class);
        startActivity(ik);
        super.onBackPressed();
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        e1=findViewById(R.id.atvUsernameReg);
        e2=findViewById(R.id.place);
        e3=findViewById(R.id.post);
        e4=findViewById(R.id.pin);
        e5=findViewById(R.id.phone);
        e6=findViewById(R.id.email);
        e7=findViewById(R.id.user);
        e8=findViewById(R.id.atvPasswordReg);
        b1=findViewById(R.id.btnSignUp);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name= e1.getText().toString();
                place=e2.getText().toString();
                post=e3.getText().toString();
                pin=e4.getText().toString();
                phone=e5.getText().toString();
                email=e6.getText().toString();
                username=e7.getText().toString();
                password=e8.getText().toString();

                if (name.equalsIgnoreCase(""))
                {
                    e1.setError("Enter first name");
                }
                else if(place.equalsIgnoreCase(""))
                {
                    e2.setError("Enter place");
                }
                 else if(post.equalsIgnoreCase(""))
                {
                    e3.setError("Enter  post");
                }
                 else if(pin.equalsIgnoreCase(""))
                {
                    e4.setError("Enter pin ");
                }
                 else if(phone.equalsIgnoreCase(""))
                {
                    e5.setError("Enter phone ");
                }
                else if(email.equalsIgnoreCase(""))
                {
                    e6.setError("Enter email ");
                }
                else if(username.equalsIgnoreCase(""))
                {
                    e7.setError("Enter username ");
                }
                else if(password.equalsIgnoreCase(""))
                {
                    e8.setError("Enter password ");
                }

                else {


                    RequestQueue queue = Volley.newRequestQueue(sign_up.this);
                    String url = "http://" + sh.getString("ip", "") + ":5000/and_register_code";
                    StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            // Display the response string.
                            Log.d("+++++++++++++++++", response);
                            try {
                                JSONObject json = new JSONObject(response);
                                String res = json.getString("task");

                                if (res.equalsIgnoreCase("valid")) {
                                    Toast.makeText(getApplicationContext(), "successfully register", Toast.LENGTH_LONG).show();
                                    Intent in = new Intent(getApplicationContext(), login.class);

                                    startActivity(in);

                                } else {
                                    Toast.makeText(getApplicationContext(), "registration failed", Toast.LENGTH_LONG).show();

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

                            params.put("name", name);
                            params.put("place", place);
                            params.put("post", post);
                            params.put("pin_code", pin);
                            params.put("phone", phone);
                            params.put("email", email);
                            params.put("uname", username);
                            params.put("password", password);

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