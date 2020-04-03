package com.tuyuservices.tuyumain;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;

public class details extends AppCompatActivity {

    //EditText name = (EditText) findViewById(R.id.input_name);
    Button mbutton;
    EditText mname,maddress,mpincode;
    private SharedPreferences sharedPreferences;
    DatabaseReference mreff;
    Shop shop;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        mname =findViewById(R.id. input_name);
        maddress =findViewById(R.id. addresslane1);
        mpincode =findViewById(R.id. pincode);
        mbutton = (Button) findViewById(R.id.sbmit);
        final String phonenumber = getIntent().getStringExtra("phonenumber");



        shop = new Shop();

        mreff = FirebaseDatabase.getInstance().getReference().child("Shop");


        mbutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(details.this , OrdersActivity.class);
                final String nmname = mname.getText().toString().trim();
                final String nmaddress = maddress.getText().toString().trim();
                final String nmpincode = mpincode.getText().toString().trim();

                shop.setAddress(nmaddress);
                shop.setPincode(nmpincode);
                shop.setShopname(nmname);
                shop.setPhonenumber(phonenumber);
                SaveName("shopname",nmname);
                SavePIN("pincode",nmpincode);
                SaveADD("address",nmaddress);

                mreff.push().setValue(shop);

                startActivity(intent);
            }
        });

    }

    public void SaveADD(String key, String value){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public void SaveName(String key, String value){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }
    public void SavePIN(String key, String value){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }
}
