package com.example.in100gram.Activities;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.IBinder;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.in100gram.R;
//import com.example.in100gram.centrifuge.Service;

import org.json.JSONException;
import org.json.JSONObject;

public class AuthActivity extends AppCompatActivity {

    boolean mBound = false;
//    public Service mService;

    JSONObject obj = new JSONObject();
    JSONObject obj2 = new JSONObject();

    String login = null;
    String pass = null;
    String auth_id = null;
    String device_name = Build.BRAND + " " + Build.DEVICE;
    private Boolean CheckOnOff;

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String PASSWORD = "password";
    public static final String LOGIN = "login";
    public static final String CHECKBOX1 = "checkbox1";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //getActionBar().hide();
//        startService(new Intent(AuthActivity.this, Service.class));

        super.onCreate(savedInstanceState);
        setContentView(R.layout.auth_main);

        Button authBtn = findViewById(R.id.authBtn);
        Button regBtn = findViewById(R.id.RegBtn);
        EditText LoginEdit = findViewById(R.id.LoginEdit);
        EditText PassEdit = findViewById(R.id.PasswordEdit);
        CheckBox saveCheck = findViewById(R.id.saveCheck);
        CheckBox showCheck = findViewById(R.id.showCheck);

        showCheck.setOnCheckedChangeListener((compoundButton, b) -> {
            Log.d("ShowInputType", String.valueOf(PassEdit.getInputType()));
            if(showCheck.isChecked()){
                PassEdit.setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            }
            else {
                PassEdit.setInputType(129);
            }
        });

        regBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(AuthActivity.this,RegActivity.class);
                startActivity(intent);
                finish();
            }
        });

        authBtn.setOnClickListener(view -> {
            login = LoginEdit.getText().toString();
            pass = PassEdit.getText().toString();

            try {
                obj.put("login", login);
                obj.put("password", pass);
                obj2.put("auth_id", auth_id);
                obj2.put("device_name", device_name);
                obj.put("auth_device", obj2);
            } catch (JSONException e) {
                e.printStackTrace();
            }

            Log.d("JSON", obj.toString());

            if(saveCheck.isChecked())
            {
                saveData();
            }
            else
            {
                saveDataNot();
            }

            Intent intent = new Intent(AuthActivity.this,ContactsActivity.class);
            startActivity(intent);
        });

        loadData();
        updateViews();
    }

    public void saveData(){
        EditText PassEdit = findViewById(R.id.PasswordEdit);
        EditText LoginEdit = findViewById(R.id.LoginEdit);
        CheckBox saveCheck = findViewById(R.id.saveCheck);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putString(PASSWORD, PassEdit.getText().toString());
        editor.putString(LOGIN, LoginEdit.getText().toString());
        editor.putBoolean(CHECKBOX1, saveCheck.isChecked());

        editor.apply();

        Log.d("???????????????????? ???????????? ?? ????????????", "??????????????????");
    }

    public void saveDataNot(){
        CheckBox saveCheck = findViewById(R.id.saveCheck);
        EditText LoginEdit = findViewById(R.id.LoginEdit);

        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        editor.putBoolean(CHECKBOX1, saveCheck.isChecked());
        editor.putString(LOGIN, LoginEdit.getText().toString());

        editor.putString(PASSWORD, "");

        editor.apply();

        Log.d("???????????????????? ???????????? ?? ????????????", "??????");
    }

    public void loadData(){
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);

        pass = sharedPreferences.getString(PASSWORD, "");
        login = sharedPreferences.getString(LOGIN, "");
        CheckOnOff = sharedPreferences.getBoolean(CHECKBOX1, false);
    }

    public void updateViews(){
        EditText PassEdit = findViewById(R.id.PasswordEdit);
        EditText LoginEdit = findViewById(R.id.LoginEdit);
        CheckBox saveCheck = findViewById(R.id.saveCheck);

        PassEdit.setText(pass);
        LoginEdit.setText(login);
        saveCheck.setChecked(CheckOnOff);
    }

//    private ServiceConnection connection = new ServiceConnection() {
//        @Override
//        public void onServiceConnected(ComponentName className, IBinder service) {
//            Service.LocalBinder binder = (Service.LocalBinder) service;
//            mService = binder.getService();
//            mBound = true;
//
//            Log.i("Service1", "Connected");
//        }
//
//        @Override
//        public void onServiceDisconnected(ComponentName arg0) {
//            mBound = false;
//        }
//    };

    @Override
    protected void onStart() {
        super.onStart();
//        Intent intent = new Intent(this, Service.class);
//        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
//        unbindService(connection);
        mBound = false;
    }
}
