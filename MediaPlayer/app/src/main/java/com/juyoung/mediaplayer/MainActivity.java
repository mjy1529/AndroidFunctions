package com.juyoung.mediaplayer;

import android.media.MediaPlayer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @BindView(R.id.button)
    Button button;
    @BindView(R.id.button2)
    Button button2;
    @BindView(R.id.button3)
    Button button3;
    @BindView(R.id.button4)
    Button button4;

    MediaPlayer player;
    int position = 0;

    public static final String url = "http://sites.google.com/site/ubiaccessmobile/sample_audio.amr";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.button, R.id.button2, R.id.button3, R.id.button4})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                playAudio();
                break;
            case R.id.button2:
                pauseAudio();
                break;
            case R.id.button3:
                resumeAudio();
                break;
            case R.id.button4:
                stopAudio();
                break;
        }
    }

    public void playAudio() {
        closePlayer();

        player = new MediaPlayer();
        try {
            player.setDataSource(url);
            player.prepare(); //준비
            player.start();

            Toast.makeText(this, R.string.play, Toast.LENGTH_SHORT).show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void pauseAudio() {
        if (player != null) {
            position = player.getCurrentPosition();
            player.pause();

            Toast.makeText(this, R.string.pause, Toast.LENGTH_SHORT).show();
        }
    }

    public void resumeAudio() {
        if (player != null && !player.isPlaying()) {
            player.seekTo(position);
            player.start();

            Toast.makeText(this, R.string.resume, Toast.LENGTH_SHORT).show();
        }
    }

    public void stopAudio() {
        if (player != null && player.isPlaying()) {
            player.stop();

            Toast.makeText(this, R.string.stop, Toast.LENGTH_SHORT).show();
        }
    }

    public void closePlayer() {
        if (player != null) {
            player.release();
            player = null;
        }
    }
}
