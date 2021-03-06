package com.fajaryga.doyog;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.fajaryga.doyog.util.PreferencesHelper;

public class LoginActivity extends AppCompatActivity {

    PreferencesHelper instance;
    private EditText name;
    private EditText email;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        instance = PreferencesHelper.getInstance(getApplicationContext());

        name = (EditText) findViewById(R.id.etNama);
        email = (EditText) findViewById(R.id.etEmail);
    }

    public void Login (View view) {
        String n = name.getText().toString();
        String e = email.getText().toString();
        Toast.makeText(getApplicationContext(),"Succes Login",Toast.LENGTH_SHORT).show();
        instance.setLogin(true);
        instance.setName(n);
        instance.setEmail(e);

        Intent intent = new Intent(LoginActivity.this,MainActivity.class);
        startActivity(intent);
    }


}
