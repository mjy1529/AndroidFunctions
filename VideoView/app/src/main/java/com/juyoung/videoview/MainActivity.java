package com.juyoung.videoview;

import android.media.MediaPlayer;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.Toast;
import android.widget.VideoView;

public class MainActivity extends AppCompatActivity {

    VideoView videoView;

    public static final String url = "http://sites.google.com/site/ubiaccessmobile/sample_video.mp4";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        videoView = (VideoView) findViewById(R.id.video);

        MediaController controller = new MediaController(this); //재생이나 중지 버튼이 있는 controller
        videoView.setMediaController(controller);
        videoView.setVideoURI(Uri.parse(url));
        videoView.requestFocus(); //동영상 정보 가져옴

        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) { //videoView가 재생준비 되었을 때 호출
                Toast.makeText(MainActivity.this, "동영상 준비됨.", Toast.LENGTH_SHORT).show();
            }
        });

        Button button = (Button) findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                videoView.seekTo(0); //처음부터 실행
                videoView.start();
            }
        });
    }
}
