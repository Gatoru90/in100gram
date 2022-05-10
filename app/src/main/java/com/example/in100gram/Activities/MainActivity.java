package com.example.in100gram.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.in100gram.R;
import com.example.in100gram.centrifuge.Service;
import com.google.android.material.button.MaterialButton;

import io.github.centrifugal.centrifuge.Client;

public class MainActivity extends AppCompatActivity {

    boolean mBound = false;
    Client client;
    public Service mService;

    TextView mBundView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        startService(new Intent(MainActivity.this, Service.class));


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MaterialButton storageBtn = findViewById(R.id.storage_btn);
        Button AuthBTN = findViewById(R.id.messenger_btn);
        Button MessengerBTN = findViewById(R.id.cloud_btn);
        Button SendRpcBtn = findViewById(R.id.test_btn);
        mBundView = findViewById(R.id.boundView);

        MessengerBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MessengerActivity.class);
                startActivity(intent);
            }
        });

        AuthBTN.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AuthActivity.class);
                startActivity(intent);
            }
        });

        storageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(checkPermission()){
                    //permission allowed
                    Intent intent = new Intent(MainActivity.this, FileListActivity.class);
                    String path = Environment.getExternalStorageDirectory().getPath();
                    intent.putExtra("path",path);
                    startActivity(intent);
                }else{
                    //permission not allowed
                    requestPermission();

                }
            }
        });

        SendRpcBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mService.SendRPC("get_messages", "{\"chat_id\": 9}");
            }
        });
    }
    private void setBView(){
        mBundView.setText(""+mBound);
    }

    private boolean checkPermission(){
        int result = ContextCompat.checkSelfPermission(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(result == PackageManager.PERMISSION_GRANTED){
            return true;
        }else
            return false;
    }

    private void requestPermission(){
        if(ActivityCompat.shouldShowRequestPermissionRationale(MainActivity.this,Manifest.permission.WRITE_EXTERNAL_STORAGE)){
            Toast.makeText(MainActivity.this,"Storage permission is requires,please allow from settings",Toast.LENGTH_SHORT).show();
        }else
            ActivityCompat.requestPermissions(MainActivity.this,new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE},111);
    }

    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className,
                                       IBinder service) {
            Service.LocalBinder binder = (Service.LocalBinder) service;
            mService = binder.getService();
            client = mService.getClient();
            mBound = true;
            setBView();

            Log.i("Service", "Connected");
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
            setBView();
        }
    };

    @Override
    protected void onStart() {
        super.onStart();
        Intent intent = new Intent(this, Service.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(connection);
        mBound = false;
    }
}