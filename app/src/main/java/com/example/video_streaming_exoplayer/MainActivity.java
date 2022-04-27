package com.example.video_streaming_exoplayer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.video_streaming_exoplayer.databinding.ActivityMainBinding;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

public class MainActivity extends AppCompatActivity {
    String videolink = "https://firebasestorage.googleapis.com/v0/b/fir-activity-64e7c.appspot.com/o/media%2Fvideo%2FVID-20220426-WA0001.mp4?alt=media&token=105b7efc-987b-4ac9-a4c1-ec63962f2b09";
 ActivityMainBinding binding;
 PlayerView pv;
  SimpleExoPlayer player;
  boolean playerwhenread= true;
  long currentposition= 0;
    int currentwindow= 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
       pv = binding.playerView;
    }
    private void initplayer(){
        player = new SimpleExoPlayer.Builder(this).build();
        pv.setPlayer(player);
      MediaItem item=  MediaItem.fromUri(videolink);
      player.setMediaItem(item);
      player.setPlayWhenReady(playerwhenread);
      player.seekTo(currentwindow,currentposition);
      player.prepare();
    }
    private void releaseplayer(){
        if(player != null){
            playerwhenread = player.getPlayWhenReady();
            currentwindow = player.getCurrentWindowIndex();
            currentposition = player.getCurrentPosition();
            player = null;
        }

    }

    @Override
    protected void onStart() {
        super.onStart();
        initplayer();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(player != null){
            initplayer();

        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        releaseplayer();
    }

    @Override
    protected void onStop() {
        super.onStop();
        releaseplayer();
    }
}