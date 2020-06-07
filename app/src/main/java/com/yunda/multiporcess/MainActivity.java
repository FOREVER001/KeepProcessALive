package com.yunda.multiporcess;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.yunda.multiporcess.activity.KeepManager;
import com.yunda.multiporcess.service.ForegroundService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        KeepManager.getInstance().registerKeepReceiver(this);

        startService(new Intent(this, ForegroundService.class));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        KeepManager.getInstance().unRegisterKeepReceiver(this);
    }
}
