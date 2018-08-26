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

    private int bgm = 0;
    private int kubifuriSound = 0;
    private int shisenSound = 0;

    private String[] bgms = {"サンプル1", "サンプル2"};
    private String[] kubifuriSounds = {"ドラム", "技系①", "技系②", "特殊系"};
    private String[] shisenSounds = {"ピアノ", "ギター", "ベース"};

    private MyPreferencesActivity myPreferencesActivity = new MyPreferencesActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.check_settings_activity);
        initView();
        getValues();
        setToTextView();
    }

    private void initView(){
        textViewCheck0 = findViewById(R.id.bgm_selected);
        textViewCheck1 = findViewById(R.id.swing_selected);
        textViewCheck2 = findViewById(R.id.look_selected);
        buttonStart = findViewById(R.id.button_start_play);
        buttonStart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), LiveViewActivity.class);
                startActivity(intent);
            }
        });
    }

    //設定した値を取得
    private void getValues(){
        bgm = myPreferencesActivity.getCurrentBGM(CheckSettingsActivity.this);
        kubifuriSound = myPreferencesActivity.getKubifuriInstrument(CheckSettingsActivity.this);
        shisenSound = myPreferencesActivity.getShisenInstrument(CheckSettingsActivity.this);
    }

    //テキストビューに文字を指定
    private void setToTextView(){
        String s0 = bgms[bgm];
        String s1 = kubifuriSounds[kubifuriSound];
        String s2 = shisenSounds[shisenSound];
        textViewCheck0.setText(s0);
        textViewCheck1.setText(s1);
        textViewCheck2.setText(s2);
    }
}
