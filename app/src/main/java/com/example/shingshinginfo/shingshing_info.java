package com.example.shingshinginfo;

import android.os.Bundle;

//import com.example.shingshinginfo.key.youtubekey;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

public class shingshing_info extends YouTubeBaseActivity {
    YouTubePlayerView youTubePlayerView;
    //String YoutubeKey= youtubekey.YoutubeKey;
    String youTubePid="xHLu8gFP3Fw";
    YouTubePlayer.OnInitializedListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shingshing_info);
        youTubePlayerView=findViewById(R.id.youtubeView);
        listener=new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(youTubePid);
            }
            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) { }
        };
        //youTubePlayerView.initialize(YoutubeKey,listener);
    }



}
