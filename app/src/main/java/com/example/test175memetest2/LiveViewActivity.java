package com.example.test175memetest2;

import android.content.Intent;
import android.graphics.Matrix;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.VideoView;

import com.jins_jp.meme.MemeConnectListener;
import com.jins_jp.meme.MemeLib;
import com.jins_jp.meme.MemeRealtimeData;
import com.jins_jp.meme.MemeRealtimeListener;

public class LiveViewActivity extends AppCompatActivity {

    // TODO : Replace APP_ID and APP_SECRET
    private static final String APP_ID = "";
    private static final String APP_SECRET = "";

    private FrameLayout blinkLayout;
    private ImageView blinkImage;
    private VideoView blinkView;
    private FrameLayout bodyLayout;
    private ImageView bodyImage;
    private TextView statusLabel;
    private Button connectButton;

    private SoundPool soundPool;
    private String[] audioFileNames = {"music2.mp3", "music3.mp3", "music4.mp3", "music5.mp3", "music.mp3"};
    private int[] sounds = new int[5];

    private MemeLib memeLib;

    final private MemeConnectListener memeConnectListener = new MemeConnectListener() {
        @Override
        public void memeConnectCallback(boolean b) {
            //describe actions after connection with JINS MEME
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    changeViewStatus(true);
                }
            });
        }

        @Override
        public void memeDisconnectCallback() {
            //describe actions after disconnection from JINS MEME
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    changeViewStatus(false);
                    Toast.makeText(LiveViewActivity.this, "DISCONNECTED", Toast.LENGTH_LONG).show();
                }
            });
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_live_view);
        init();
        soundPool();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);

        //Sets MemeConnectListener to get connection result.
        memeLib.setMemeConnectListener(memeConnectListener);

        changeViewStatus(memeLib.isConnected());

        //Starts receiving realtime data if MEME is connected
        if (memeLib.isConnected()) {
            memeLib.startDataReport(memeRealtimeListener);
        }
    }

    private void init() {
        //Authentication and authorization of App and SDK
        MemeLib.setAppClientID(getApplicationContext(), APP_ID, APP_SECRET);
        memeLib = MemeLib.getInstance();

        blinkLayout = (FrameLayout)findViewById(R.id.blink_layout);
        blinkImage = (ImageView)findViewById(R.id.blink_image);
        blinkView = (VideoView)findViewById(R.id.blink_view);
        blinkView.setZOrderOnTop(true);
        blinkView.setVideoPath("android.resource://" + this.getPackageName() + "/" + R.raw.blink);
        blinkView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                mediaPlayer.seekTo(0);
            }
        });

        bodyLayout = (FrameLayout)findViewById(R.id.body_layout);

        bodyImage = (ImageView)findViewById(R.id.body_image);

        statusLabel = (TextView)findViewById(R.id.status_label);

        connectButton = (Button)findViewById(R.id.connect_button);
        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (memeLib.isConnected()) {
                    memeLib.disconnect();
                } else {
                    Intent intent = new Intent(LiveViewActivity.this, ConnectActivity.class);
                    startActivity(intent);
                }
            }
        });

        changeViewStatus(memeLib.isConnected());
    }

    private void changeViewStatus(boolean connected) {
        if (connected) {
            statusLabel.setText(R.string.connected);
            statusLabel.setBackgroundColor(ContextCompat.getColor(this, R.color.black));

            connectButton.setBackground(ContextCompat.getDrawable(this, R.drawable.disconnect_button));

            blinkLayout.setAlpha(1.0f);
            blinkView.setVisibility(View.VISIBLE);
            bodyLayout.setAlpha(1.0f);
        } else {
            statusLabel.setText(R.string.not_connected);
            statusLabel.setBackgroundColor(ContextCompat.getColor(this, R.color.red));

            connectButton.setBackground(ContextCompat.getDrawable(this, R.drawable.connect_button));

            blinkImage.setVisibility(View.VISIBLE);
            blinkLayout.setAlpha(0.2f);
            blinkView.setVisibility(View.INVISIBLE);
            bodyLayout.setAlpha(0.2f);
        }
    }


    private final MemeRealtimeListener memeRealtimeListener = new MemeRealtimeListener() {
        @Override
        public void memeRealtimeCallback(final MemeRealtimeData memeRealtimeData) {
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    updateMemeData(memeRealtimeData);
                }
            });
        }
    };

    private void updateMemeData(MemeRealtimeData d) {

        // for blink
        //Log.d("LiveViewActivity", "Blink Speed:" + d.getBlinkSpeed());
        if (d.getBlinkSpeed() > 0) {
            blinkImage.setVisibility(View.INVISIBLE);

            blink();
        }
        int dataUp = d.getEyeMoveUp();
        int dataDown = d.getEyeMoveDown();
        int dataLeft = d.getEyeMoveLeft();
        int dataRight = d.getEyeMoveRight();
        float dataRoll = d.getRoll();
        float dataPitch = d.getPitch();
        float dataYaw = d.getYaw();
        float dataAccZ = d.getAccZ();
        if (dataUp > 2){
            Log.d("error1", "up");
            //soundPool.play(sounds[0], 1.0f, 1.0f, 0, 0, 1);
        }
        if (dataDown > 2){
            Log.d("error1", "down");
            //soundPool.play(sounds[1], 1.0f, 1.0f, 0, 0, 1);
        }
        if (dataLeft > 2){
            Log.d("error1", "left");
            //soundPool.play(sounds[2], 1.0f, 1.0f, 0, 0, 1);
        }
        if (dataRight > 2){
            Log.d("error1", "right");
            //soundPool.play(sounds[3], 1.0f, 1.0f, 0, 0, 1);
        }
        if (dataAccZ > -6.0f || dataAccZ < -26.0f){
            soundPool.play(sounds[3], 1.0f, 1.0f, 0, 0, 1);
        } else if (dataRoll > 40.0f || dataRoll < -40.0f){
            soundPool.play(sounds[3], 1.0f, 1.0f, 0, 0, 1);
        } else if (dataPitch > 40.0f || dataPitch < -40.0f){
            soundPool.play(sounds[2], 1.0f, 1.0f, 0, 0, 1);
        } else if (dataYaw > 40.0f || dataYaw < -40.0f){
            soundPool.play(sounds[1], 1.0f, 1.0f, 0, 0, 1);
        }

        //Log.d("error1", "pitch: " + dataPitch);
        //Log.d("error1", "yaw: " + dataYaw);
        Log.d("error1", "accZ: " + dataAccZ);


        // for body (Y axis rotation)
        double radian = Math.atan2(d.getAccX(), d.getAccZ());
        rotate(Math.toDegrees(-radian)); // for mirroring display(radian x -1)
    }

    private void blink(){
        blinkView.seekTo(0);
        blinkView.start();
    }

    private void rotate(double degree) {
        int width = bodyImage.getDrawable().getBounds().width();
        int height = bodyImage.getDrawable().getBounds().height();

        Matrix matrix = new Matrix();
        bodyImage.setScaleType(ImageView.ScaleType.MATRIX);
        matrix.postRotate((float)degree, width/2, height/2);
        matrix.postScale(0.5f, 0.5f);
        bodyImage.setImageMatrix(matrix);
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
        sounds[0] = soundPool.load(this, R.raw.music2, 1);
        sounds[1] = soundPool.load(this, R.raw.music3, 1);
        sounds[2] = soundPool.load(this, R.raw.music4, 1);
        sounds[3] = soundPool.load(this, R.raw.music5, 1);
    }
}