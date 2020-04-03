package com.tuyuservices.tuyumain;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;

import androidx.appcompat.app.AppCompatActivity;

public class Register extends AppCompatActivity {

    //private Spinner spinner;
    private EditText editText,editname;
    private SharedPreferences sharedPreferences;
    Boolean isLoggedIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        isLoggedIn = LoadBool();

        //spinner = findViewById(R.id.spinnerCountries);
        //spinner.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_dropdown_item, CountryData.countryNames));

        editText = findViewById(R.id.editTextPhone);
        editname = findViewById(R.id.editTextname);

        findViewById(R.id.buttonContinue).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // String code = CountryData.countryAreaCodes[spinner.getSelectedItemPosition()];

                String number = editText.getText().toString().trim();
                String medittext  = editname.getText().toString().trim();
                SaveNUM("NUMBER", number);
                SaveName("name", medittext);


                if (number.isEmpty() || number.length() < 10) {
                    editText.setError("Valid number is required");
                    editText.requestFocus();
                    return;
                }


                String phonenumber = "+" + "91"+ number;

                Intent intent = new Intent(Register.this, Otp.class);
                intent.putExtra("phonenumber", phonenumber);
                startActivity(intent);

            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();

        if (FirebaseAuth.getInstance().getCurrentUser() != null ) {
            Intent intent = new Intent(this, OrdersActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);

            startActivity(intent);
        }
    }
    public void SaveNUM(String key, String value){
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


    public boolean LoadBool(){
        sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        return  sharedPreferences.getBoolean("login", false);
    }
}
