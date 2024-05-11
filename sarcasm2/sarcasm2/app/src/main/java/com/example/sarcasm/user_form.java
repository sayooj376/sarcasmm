package com.example.sarcasm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;

public class user_form extends AppCompatActivity {
    Button b1,b2,b3,b4,b5,b6;
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
        setContentView(R.layout.activity_main1);
        b1=findViewById(R.id.bttLights);
        b2=findViewById(R.id.bttShutters);
        b3=findViewById(R.id.bttAutomations);
        b4=findViewById(R.id.bttStopServices);
        b5=findViewById(R.id.bttStopServices1);
        b6=findViewById(R.id.bttStopServices2);

        sh= PreferenceManager.getDefaultSharedPreferences(getApplicationContext());

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ik=new Intent(getApplicationContext(),view_product.class);
                startActivity(ik);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ik=new Intent(getApplicationContext(),view_orderstatus.class);
                startActivity(ik);
            }
        });
        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ik=new Intent(getApplicationContext(),viewmycart.class);
                startActivity(ik);
            }
        });

        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ik=new Intent(getApplicationContext(),view_productreview.class);
                startActivity(ik);
            }
        });
        b5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ik=new Intent(getApplicationContext(),complaint.class);
                startActivity(ik);
            }
        });
        b6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent ik=new Intent(getApplicationContext(),login.class);
                startActivity(ik);

            }
        });

    }
}