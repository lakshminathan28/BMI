package com.lakshmi.music3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.SeekBar;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    MediaPlayer player;
    AudioManager audioManager;
    public void play(View view)
    {
        player.start();
    }

    public void pause(View view)
    {
        player.pause();
    }
    public void stop(View view)
    {
        player.stop();
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        player=MediaPlayer.create(this,R.raw.music1);
        audioManager= (AudioManager) getSystemService(Context.AUDIO_SERVICE);
       // int maxvol=audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
        //int curvol=audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);


        final SeekBar sk=findViewById(R.id.seek);
       // sk.setMax(maxvol);
        sk.setProgress(0);

        sk.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                audioManager.setStreamVolume(AudioManager.STREAM_MUSIC,i,0);

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
      final SeekBar seek1=findViewById(R.id.seek1);
      seek1.setMax(player.getDuration());
         new Timer().scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                seek1.setProgress(player.getCurrentPosition());
            }
        },0,1000);


        seek1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
          @Override
          public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
              player.seekTo(i);
          }

          @Override
          public void onStartTrackingTouch(SeekBar seekBar) {

          }

          @Override
          public void onStopTrackingTouch(SeekBar seekBar) {

          }
      });
    }
}