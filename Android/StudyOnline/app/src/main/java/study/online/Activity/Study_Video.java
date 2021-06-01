package study.online.Activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.google.android.exoplayer2.ExoPlayerFactory;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.source.ExtractorMediaSource;
import com.google.android.exoplayer2.source.MediaSource;
import com.google.android.exoplayer2.trackselection.AdaptiveTrackSelection;
import com.google.android.exoplayer2.trackselection.DefaultTrackSelector;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.trackselection.TrackSelector;
import com.google.android.exoplayer2.ui.SimpleExoPlayerView;
import com.google.android.exoplayer2.upstream.BandwidthMeter;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DefaultBandwidthMeter;
import com.google.android.exoplayer2.upstream.DefaultDataSourceFactory;
import com.google.android.exoplayer2.util.Util;


import study.online.R;

public class Study_Video extends AppCompatActivity {
    private String url;
    private SimpleExoPlayer simpleExoPlayer;
    private TrackSelector trackSelector;
    private SimpleExoPlayerView simpleExoPlayerView;
    private boolean shouldAutoPlay;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study_video);
        Intent intent = getIntent();
        url = intent.getStringExtra("url");
        initPlayer();
        DataSource.Factory mediaDataSourceFactory = new DefaultDataSourceFactory(this, Util.getUserAgent(this, "StudyOnline"));
        MediaSource mediaSource = new ExtractorMediaSource.Factory(mediaDataSourceFactory).createMediaSource(Uri.parse(url));
        simpleExoPlayer.prepare(mediaSource);
    }

    private void initPlayer() {
        shouldAutoPlay = true;
        //1. 创建一个默认的 TrackSelector
        BandwidthMeter bandwidthMeter = new DefaultBandwidthMeter();
        TrackSelection.Factory videoTackSelectionFactory = new AdaptiveTrackSelection.Factory(bandwidthMeter);
        trackSelector = new DefaultTrackSelector(videoTackSelectionFactory);
        //2.创建ExoPlayer
        simpleExoPlayer = ExoPlayerFactory.newSimpleInstance(this, trackSelector);
        //3.创建SimpleExoPlayerView
        simpleExoPlayerView = (SimpleExoPlayerView) findViewById(R.id.exoView);
        //4.为SimpleExoPlayer设置播放器
        simpleExoPlayerView.setPlayer(simpleExoPlayer);
        simpleExoPlayer.setPlayWhenReady(shouldAutoPlay);
    }

    private void releasePlayer() {
        if (simpleExoPlayer!= null) {
            shouldAutoPlay = simpleExoPlayer.getPlayWhenReady();
            //释放播放器对象
            simpleExoPlayer.release();
            simpleExoPlayer = null;
            trackSelector = null;
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (simpleExoPlayer == null) {
            initPlayer();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        releasePlayer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        releasePlayer();
    }
}
