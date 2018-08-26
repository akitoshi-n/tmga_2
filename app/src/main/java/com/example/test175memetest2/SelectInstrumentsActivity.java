package com.example.test175memetest2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

/**
 * Created by Yu on 2018/08/25.
 */

public class SelectInstrumentsActivity extends AppCompatActivity {

    private Button buttonToNext, buttonKubifuri, buttonShisen;
    private RelativeLayout buttonKubifuriBack, buttonShisenBack;
    private ImageView buttonLeft, buttonRight;
    private int transitionAnimLong = 200;

    private int currentKubifuriInstrument = 0;
    private int currentShisenInstrument = 0;
    //首振り: 0 視線: 1
    private int selectedTarget = 0;

    private LinearLayout[] instrumentWrapper = new LinearLayout[7];

    private MyPreferencesActivity myPreferencesActivity = new MyPreferencesActivity();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.select_instruments_activity);
        initView();
        getPresetInstrument();
        setFirstInstrument();
    }

    private void initView(){
        buttonKubifuriBack = findViewById(R.id.button_kubifuri_background);
        buttonShisenBack = findViewById(R.id.button_sisen_background);
        buttonToNext = findViewById(R.id.button_to_next);
        buttonKubifuri = findViewById(R.id.button_select_kubifuri);
        buttonShisen = findViewById(R.id.button_select_sisen);
        buttonToNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setPresetInstrument();
                Intent intent = new Intent(getApplication(), CheckSettingsActivity.class);
                startActivity(intent);
            }
        });
        buttonKubifuri.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTarget = 0;
                buttonKubifuri.setTextColor(0xff000000);
                buttonShisen.setTextColor(0xffffffff);
                AlphaAnimation animation1 = new AlphaAnimation(0.0f, 1.0f);
                AlphaAnimation animation2 = new AlphaAnimation(1.0f, 0.0f);
                animation1.setDuration(transitionAnimLong);
                animation2.setDuration(transitionAnimLong);
                animation1.setFillAfter(true);
                animation2.setFillAfter(true);
                buttonKubifuriBack.setAnimation(animation1);
                buttonShisenBack.setAnimation(animation2);
                buttonKubifuriBack.setVisibility(View.VISIBLE);
                buttonKubifuriBack.setVisibility(View.INVISIBLE);
                showCorrectInstrument(currentKubifuriInstrument);
            }
        });
        buttonShisen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                selectedTarget = 1;
                buttonKubifuri.setTextColor(0xffffffff);
                buttonShisen.setTextColor(0xff000000);
                AlphaAnimation animation1 = new AlphaAnimation(0.0f, 1.0f);
                AlphaAnimation animation2 = new AlphaAnimation(1.0f, 0.0f);
                animation1.setDuration(transitionAnimLong);
                animation2.setDuration(transitionAnimLong);
                animation1.setFillAfter(true);
                animation2.setFillAfter(true);
                buttonKubifuriBack.setAnimation(animation2);
                buttonShisenBack.setAnimation(animation1);
                buttonKubifuriBack.setVisibility(View.INVISIBLE);
                buttonKubifuriBack.setVisibility(View.VISIBLE);
                showCorrectInstrument(currentShisenInstrument + 4);
            }
        });
        buttonShisenBack.setVisibility(View.INVISIBLE);
        buttonShisen.setTextColor(0xffffffff);
        buttonLeft = findViewById(R.id.select_left);
        buttonRight = findViewById(R.id.select_right);
        buttonRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (selectedTarget){
                    case 0:
                        if (currentKubifuriInstrument == 3){
                            currentKubifuriInstrument = 0;
                        } else {
                            currentKubifuriInstrument++;
                        }
                        showCorrectInstrument(currentKubifuriInstrument);
                        break;
                    case 1:
                        if (currentShisenInstrument == 2){
                            currentShisenInstrument = 0;
                        } else {
                            currentShisenInstrument++;
                        }
                        showCorrectInstrument(currentShisenInstrument + 4);
                        break;
                }
            }
        });
        buttonLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                switch (selectedTarget){
                    case 0:
                        if (currentKubifuriInstrument == 0){
                            currentKubifuriInstrument = 3;
                        } else {
                            currentKubifuriInstrument--;
                        }
                        showCorrectInstrument(currentKubifuriInstrument);
                        break;
                    case 1:
                        if (currentShisenInstrument == 0){
                            currentShisenInstrument = 2;
                        } else {
                            currentShisenInstrument--;
                        }
                        showCorrectInstrument(currentShisenInstrument + 4);
                        break;
                }
            }
        });

        instrumentWrapper[0] = findViewById(R.id.instrument_select_0);
        instrumentWrapper[1] = findViewById(R.id.instrument_select_1);
        instrumentWrapper[2] = findViewById(R.id.instrument_select_2);
        instrumentWrapper[3] = findViewById(R.id.instrument_select_3);
        instrumentWrapper[4] = findViewById(R.id.instrument_select_4);
        instrumentWrapper[5] = findViewById(R.id.instrument_select_5);
        instrumentWrapper[6] = findViewById(R.id.instrument_select_6);
    }

    private void getPresetInstrument(){
        currentKubifuriInstrument = myPreferencesActivity.getKubifuriInstrument(SelectInstrumentsActivity.this);
        currentShisenInstrument = myPreferencesActivity.getShisenInstrument(SelectInstrumentsActivity.this);
    }

    private void setPresetInstrument(){
        myPreferencesActivity.setKubifuriInstrument(SelectInstrumentsActivity.this, currentKubifuriInstrument);
        myPreferencesActivity.setShisenInstrument(SelectInstrumentsActivity.this, currentShisenInstrument);
    }

    private void showCorrectInstrument(int target){
        for (int i = 0; i < instrumentWrapper.length; i++){
            if (i == target) {
                instrumentWrapper[i].setVisibility(View.VISIBLE);
                continue;
            }
            instrumentWrapper[i].setVisibility(View.GONE);
        }
    }

    private void setFirstInstrument(){
        for (int i = 0; i < instrumentWrapper.length; i++){
            if (i == currentKubifuriInstrument) {
                instrumentWrapper[i].setVisibility(View.VISIBLE);
                continue;
            }
            instrumentWrapper[i].setVisibility(View.GONE);
        }
    }

    private void makeToast(String text){
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}
