package com.example.young.exoplayer;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;

public class MainActivity extends AppCompatActivity {

    SimpleExoPlayerView exoPlayerView;
    SimpleExoPlayer videoPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        exoPlayerView = (SimpleExoPlayerView) findViewById(R.id.playerView);
        String video_url = "http://175.123.138.125:8070/hot.mp4";

        //Create a default TrackSelector
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTrackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
        DefaultTrackSelector trackSelector = new DefaultTrackSelector(videoTrackSelectionFactory);

        //Create the player
        videoPlayer = ExoPlayerFactory.newSimpleInstance(MainActivity.this, trackSelector);

        //Attaching the player to a view
        exoPlayerView.setPlayer(videoPlayer);

        //Preparing the player
        DefaultBandwidthMeter defaultBandwidthMeter = new DefaultBandwidthMeter();
        DataSource.Factory dataSourceFactory = new DefaultDataSourceFactory(MainActivity.this,
                Util.getUserAgent(MainActivity.this, "NamsanHanokDocent"), defaultBandwidthMeter);
        MediaSource videoSource = new ExtractorMediaSource.Factory(dataSourceFactory)
                .createMediaSource(Uri.parse(video_url));
        videoPlayer.prepare(videoSource);

        //Preparing the player(2) - http
//        DefaultBandwidthMeter defaultBandwidthMeter = new DefaultBandwidthMeter();
//        MediaSource videoSource = new ExtractorMediaSource.Factory(
//                new DefaultHttpDataSourceFactory(Util.getUserAgent(getApplicationContext(), "DOCENT"), defaultBandwidthMeter)
//        ).createMediaSource(Uri.parse(video_url));
//        videoPlayer.prepare(videoSource);

        //Preparing the player(3) - raw directory에서 가져올 때 (local video)
//        final RawResourceDataSource rawResourceDataSource = new RawResourceDataSource(this);
//        DataSpec dataSpec = new DataSpec(RawResourceDataSource.buildRawResourceUri(R.raw.hot));
//        try {
//            rawResourceDataSource.open(dataSpec);
//            DataSource.Factory factory = new DataSource.Factory() {
//                @Override
//                public DataSource createDataSource() {
//                    return rawResourceDataSource;
//                }
//            };
//
//            MediaSource videoSource = new ExtractorMediaSource.Factory(factory).createMediaSource(rawResourceDataSource.getUri());
//            videoPlayer.prepare(videoSource);
//        } catch (RawResourceDataSource.RawResourceDataSourceException e) {
//            e.printStackTrace();
//        }
    }
}
