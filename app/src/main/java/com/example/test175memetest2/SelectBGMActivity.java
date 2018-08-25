package com.example.test175memetest2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Yu on 2018/08/25.
 */

public class SelectBGMActivity extends AppCompatActivity{

    private Button buttonRight, buttonLeft, buttonNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_bgm_activity);
        initView();
    }

    private void initView(){
        buttonNext = findViewById(R.id.button_to_next);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), LiveViewActivity.class);
                startActivity(intent);
            }
        });
    }

}
