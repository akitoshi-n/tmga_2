package com.example.test175memetest2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Yu on 2018/08/26.
 */

public class CheckSettingsActivity extends AppCompatActivity {
    private TextView textViewCheck0, textViewCheck1, textViewCheck2;
    private Button buttonStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_settings_activity);
        initView();
    }

    private void initView(){
        textViewCheck0 = findViewById(R.id.textview_bgm_0);
        textViewCheck1 = findViewById(R.id.textview_bgm_1);
        buttonStart = findViewById(R.id.button_start_play);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), LiveViewActivity.class);
                startActivity(intent);
            }
        });
    }
}
