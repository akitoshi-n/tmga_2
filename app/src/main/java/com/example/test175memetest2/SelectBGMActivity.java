package com.example.test175memetest2;

import android.content.Intent;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Yu on 2018/08/25.
 */

public class SelectBGMActivity extends AppCompatActivity{

    private int currentBMG = 0;
    private Button buttonRight, buttonLeft, buttonNext;
    private SoundPool soundPool;
    private int[] sounds = new int[2];
    private TextView textBGM0, textBGM1, textBGM2;

    private MyPreferencesActivity myPreferencesActivity = new MyPreferencesActivity();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_bgm_activity);
        initView();
        displayCurrentBMGText();
    }

    private void initView(){
        buttonNext = findViewById(R.id.button_to_next);
        buttonRight = findViewById(R.id.select_right);
        buttonLeft = findViewById(R.id.select_left);
        buttonNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplication(), SelectInstrumentsActivity.class);
                startActivity(intent);
                myPreferencesActivity.setCurrentBGM(SelectBGMActivity.this, currentBMG);
            }
        });
        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentBMG == 0){
                    currentBMG = sounds.length -1;
                } else {
                    currentBMG--;
                }
                displayCurrentBMGText();
            }
        });
        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (currentBMG == sounds.length - 1){
                    currentBMG = 0;
                } else {
                    currentBMG++;
                }
                displayCurrentBMGText();
            }
        });
        currentBMG = myPreferencesActivity.getCurrentBGM(SelectBGMActivity.this);
        textBGM0 = findViewById(R.id.textview_bgm_0);
        textBGM1 = findViewById(R.id.textview_bgm_1);
    }

    private void soundPool(){
        setVolumeControlStream(AudioManager.STREAM_MUSIC);
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_GAME)
                .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH)
                .build();
        soundPool = new SoundPool.Builder()
                .setAudioAttributes(audioAttributes)
                .setMaxStreams(2)
                .build();
        sounds[0] = soundPool.load(this, R.raw.tmga_drum1, 1);
        sounds[1] = soundPool.load(this, R.raw.tmga_drum2, 1);
    }

    private void displayCurrentBMGText(){
        if (currentBMG == 0){
            textBGM0.setVisibility(View.VISIBLE);
            textBGM1.setVisibility(View.GONE);
        } else if (currentBMG == 1){
            textBGM0.setVisibility(View.GONE);
            textBGM1.setVisibility(View.VISIBLE);
        }
    }


}
