package com.example.shingshinginfo;

import android.os.Bundle;
import android.util.Log;

import com.example.shingshinginfo.youtubekey;
import com.google.android.youtube.player.YouTubeBaseActivity;
import com.google.android.youtube.player.YouTubeInitializationResult;
import com.google.android.youtube.player.YouTubePlayer;
import com.google.android.youtube.player.YouTubePlayerView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Response;

public class shingshing_info extends YouTubeBaseActivity {
    //통신으로 받아올 변수들
    String firstName;//1등작물
    String secondName;//2등작물
    String thirdName;//3등작물

    // 1등 작물 설명
    String firstExp;//1등작물 설명 변수
    String firstImg;//1등작물 이미지

    // 병충해
    String BugName;//병충해 이름
    String []BugExplain;//병충해 설명
    String BugHardName;//병충해 피해 이름
    String BugImg;//병충해 이미지
    String BugHardImg;//병충해 피해이미지

    //작물정보 다운로드
    String downLink;//작물정보 다운로드 링크
    


    //youtube위한 변수들
    YouTubePlayerView youTubePlayerView;
    //String YoutubeKey= youtubekey.YoutubeKey;
    String YoutubeKey= "12342532445756785676435";
    String youTubePid="CfPxlb8-ZQ0";//나중에 받아올거임
    YouTubePlayer.OnInitializedListener listener;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shingshing_info);


        YoutubeSet();
    }

    public void YoutubeSet(){
        youTubePlayerView=findViewById(R.id.youtubeView);
        listener=new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(youTubePid);
            }
            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) { }
        };
        youTubePlayerView.initialize(YoutubeKey,listener);
    }
    //싱싱정보통 통신
    public void getShingInfoData(){
        Call<List<ResponseShingShingInfo>> SingInfo=RetrofitClient.getApiService()
                .getInfo();
        SingInfo.enqueue(new AutoRetryCallback<List<ResponseShingShingInfo>>() {
            @Override
            public void onFinalFailure(Call<List<ResponseShingShingInfo>> call, Throwable t) {
                Log.e("그래프 정보 연결 실패",t.getMessage());
            }

            @Override
            public void onResponse(Call<List<ResponseShingShingInfo>> call, Response<List<ResponseShingShingInfo>> response) {
                if(!response.isSuccessful()){
                    Log.e("그래프 연결이 비정상적","error code:"+response.code());
                    return;
                }

                Log.d("그래프 통신 성공적",response.body().toString());
                List<ResponseShingShingInfo> ShingInfoJson=response.body();//통신 결과 받기


            }
        });
    }


}
