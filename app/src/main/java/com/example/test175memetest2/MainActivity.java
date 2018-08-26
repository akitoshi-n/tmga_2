package com.example.test175memetest2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jins_jp.meme.MemeLib;

/**
 * Created by Yu on 2018/08/25.
 */

public class MainActivity extends AppCompatActivity {
    private Button button, buttonConnect;
    private MemeLib memeLib;

    private static final String APP_ID = "834048189444008";
    private static final String APP_SECRET = "993ox4vgs4s0js8moi7yst37g6ipj3mj";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView(){
        button = findViewById(R.id.button_start);
        buttonConnect = findViewById(R.id.button_connect);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), SelectBGMActivity.class);
                startActivity(intent);
            }
        });
        buttonConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MemeLib.setAppClientID(getApplicationContext(), APP_ID, APP_SECRET);
                memeLib = MemeLib.getInstance();
                Intent intent = new Intent(getApplication(), ConnectActivity.class);
                startActivity(intent);
            }
        });
    }
}
