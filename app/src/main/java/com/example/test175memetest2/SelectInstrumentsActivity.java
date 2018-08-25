package com.example.test175memetest2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by Yu on 2018/08/25.
 */

public class SelectInstrumentsActivity extends AppCompatActivity {

    private Button buttonToNext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_instruments_activity);
        initView();
    }

    private void initView(){
        buttonToNext = findViewById(R.id.button_to_next);
        buttonToNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), CheckSettingsActivity.class);
                startActivity(intent);
            }
        });
    }

}
