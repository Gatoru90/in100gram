package com.example.in100gram;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

public class AuthActivity extends AppCompatActivity {

    List<LoginJSON> LoginJson = new ArrayList<LoginJSON>();
    List<auth_device> auth_device = new ArrayList<auth_device>();

    EditText LoginEdit = findViewById(R.id.LoginEdit);
    EditText PassEdit = findViewById(R.id.PasswordEdit);

    String login = null;
    String pass = null;
    String auth_id = null;
    String device_name = Build.BRAND + " " + Build.DEVICE;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_main);



        Button authBtn = findViewById(R.id.authBtn);

        authBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                login = LoginEdit.getText().toString();
                pass = PassEdit.getText().toString();

                auth_device.add(new auth_device(auth_id, device_name));

                LoginJson.add(new LoginJSON(login, pass));

                Log.d("JSON", auth_device.toString() + "  " + LoginJson.toString());
            }
        });
    }
}
