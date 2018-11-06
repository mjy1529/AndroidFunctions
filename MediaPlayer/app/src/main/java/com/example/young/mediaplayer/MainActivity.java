package com.example.young.mediaplayer;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements MediaPlayer.OnPreparedListener{

    MediaPlayer audioPlayer = null;
    TextView startTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startTv = (TextView)findViewById(R.id.startTv);

        setAudioPlayer();
    }

    public void setAudioPlayer() {
        String audio_url = "http://175.123.138.125:8070/kkk.mp3";

        try {
            audioPlayer = new MediaPlayer();
            audioPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
            audioPlayer.setDataSource(audio_url);
            audioPlayer.prepareAsync();
        } catch (IOException e) {
            startTv.setText("audio error");
        } catch (IllegalStateException e) {
            //startTv.setText("audio error");
        }
    }

    @Override
    public void onPrepared(MediaPlayer mediaPlayer) {
        audioPlayer.start();
        startTv.setText("audio playing");
    }
}
