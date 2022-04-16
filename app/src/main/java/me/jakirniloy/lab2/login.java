package me.jakirniloy.lab2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class login extends AppCompatActivity {
    Button login,exit;
    EditText Userid,UserPassword;


    SharedPreferences sharedpreferences;

    // sharedPref = getSharedPreferences("MySharedPref", MODE_PRIVATE); // create
    //§ prefsEditor = sharedPref.edit(); // get pref-file editor
    //§ prefsEditor.putString("user_id", userid); // insert an item to pref
    //§ String userID = sharedPref.getString("user_id", null); // read-back the data
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        login = findViewById(R.id.btnlogin);
        exit = findViewById(R.id.btnexit);
        Userid = findViewById(R.id.userid);
        UserPassword = findViewById(R.id.userpassword);
        sharedpreferences = getSharedPreferences("MyPREFERENCES", Context.MODE_PRIVATE);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String s= sharedpreferences.getString("user_log",null);
                String p =sharedpreferences.getString("user_password",null);
                SharedPreferences.Editor editor = sharedpreferences.edit();
                editor.putString("user_log", Userid.getText().toString());
                editor.putString("user_password", UserPassword.getText().toString());
                editor.commit();
                Toast.makeText(login.this,"Thanks!login saved", Toast.LENGTH_LONG).show();
            }
        });



        exit.setOnClickListener(view -> finish());
    }
}

