package com.tareqrobin.musicplayer;

import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

public class MainActivity extends AppCompatActivity {

    private SeekBar seekBar;
    private Button back,play,forw;
    private MediaPlayer mp;

    Runnable runnable;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar=findViewById(R.id.seekBar);
        back=findViewById(R.id.backw);
        play=findViewById(R.id.play);
        forw=findViewById(R.id.forw);

        handler=new Handler();

        mp=MediaPlayer.create(getApplicationContext(),R.raw.nilanjona);

        mp.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mediaPlayer) {
                seekBar.setMax(mp.getDuration());
                mp.start();
                changeSeekBar();
            }
        });
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.seekTo(mp.getCurrentPosition()-5000);
            }
        });
        play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (mp.isPlaying()){
                    mp.pause();
                    play.setText("play");
                } else {
                    mp.start();
                    play.setText("pause");
                }
                changeSeekBar();
            }
        });
        forw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mp.seekTo(mp.getCurrentPosition()+5000);
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                if (b){
                    mp.seekTo(i);
                }

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
private  void changeSeekBar(){
    seekBar.setProgress(mp.getCurrentPosition());
    if (mp.isPlaying()){
        runnable=new Runnable() {
            @Override
            public void run() {
                changeSeekBar();
            }
        };
        handler.postDelayed(runnable,1000);
        }
    }
}
