package com.example.shingshinginfo;

import android.os.Bundle;
import android.util.Log;

import com.example.shingshinginfo.h_network.AutoRetryCallback;
import com.example.shingshinginfo.h_network.RetrofitClient;
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

        //작물
        String firstName;//1등작물
        String secondName;//2등작물
        String thirdName;//3등작물

        // 1등 작물 설명
        String firstExp;//1등작물 설명 변수
        String firstImg;//1등작물 이미지

        // 병충해
        String BugName;//병충해 이름
        String []BugExplain=new String[3];//병충해 설명
        String BugHardName;//병충해 피해 이름
        String BugImg;//병충해 이미지
        String BugHardImg;//병충해 피해이미지

        //작물정보 다운로드
        String downLink;//작물정보 다운로드 링크

        //유투브 변수
        String youTubePid;//나중에 받아올거임

        //youtube위한 변수들
        YouTubePlayerView youTubePlayerView;
        //String YoutubeKey= youtubekey.YoutubeKey;
        String YoutubeKey= "12342532445756785676435";
        YouTubePlayer.OnInitializedListener listener;
        YouTubePlayer player;



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.shingshing_info);


        getShingInfoData();


    }

    public void YoutubeSet(String Pid){
        youTubePlayerView=findViewById(R.id.youtubeView);

        listener=new YouTubePlayer.OnInitializedListener() {
            @Override
            public void onInitializationSuccess(YouTubePlayer.Provider provider, YouTubePlayer youTubePlayer, boolean b) {
                youTubePlayer.loadVideo(Pid);
            }
            @Override
            public void onInitializationFailure(YouTubePlayer.Provider provider, YouTubeInitializationResult youTubeInitializationResult) { }
        };
        youTubePlayerView.initialize(YoutubeKey,listener);
        //player.cueVideo(youTubePid);
        Log.d("유투브 아이디",youTubePid);

    }

    //싱싱정보통 통신

    public void getShingInfoData(){
        Call<String> SingInfo= RetrofitClient.getApiService().
                getInfo();
        SingInfo.enqueue(new AutoRetryCallback<String>() {
            @Override
            public void onFinalFailure(Call<String> call, Throwable t) {
                Log.e("싱싱정보통",t.getMessage());
            }

            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if(!response.isSuccessful()){
                    Log.e("싱싱정보통 연결이 비정상적","error code:"+response.code());
                    return;
                }

                Log.d("싱싱정보통 통신 성공적",response.body().toString());
                String value=response.body();//통신 결과 받기
                Log.d("오는지 확인","오는지 확인"+value);
                value=value.replace("[","");
                value=value.replace("]","");
                String[] splitvalue=value.split("\",\"");
                for(int i=0;i<splitvalue.length;i++){
                    splitvalue[i]=splitvalue[i].replace("\"","");
                    Log.d("오는지 확인",splitvalue[i]);
                }

               // String[][]value=response.body();//String배열화
                //takeJson set=new takeJson(value);
                // 값 세팅
                firstName=splitvalue[0];//1등 이름
                secondName=splitvalue[1];//2등 이름
                thirdName=splitvalue[2];//3등 이름
                firstExp=splitvalue[3];//1등 설명
                firstImg=splitvalue[4];//1등 이미지
                BugName=splitvalue[5];//병해충 이름

                //이부분 아직 null이 온적이 없어서 null이 오면 어떻게 되는지 잘 모름
                if(splitvalue[6]==null)
                    BugExplain[0]="";//병충해 설명1
                else
                    BugExplain[0]= splitvalue[6];//병충해 설명1

                if(splitvalue[7]==null)
                    BugExplain[1]="";//병충해 설명2
                else
                    BugExplain[1]= splitvalue[7];//병충해 설명2

                if(splitvalue[8]==null)
                    BugExplain[2]="";//병충해 설명2
                else
                    BugExplain[2]= splitvalue[8];//병충해 설명2


                BugHardName=splitvalue[9];//병충해 피해 이름
                BugHardImg=splitvalue[10];//병충해 피해이미지
                BugImg=splitvalue[11];//병충해 벌레 이미지
                downLink=splitvalue[12];//작물정보 다운로드 링크
                youTubePid=splitvalue[13];//유투브 키

                /*Log.d("1등","1등"+firstName+"2등"+secondName+"3등"+thirdName+"1등 설명"+firstExp+
                        "1등 이미지"+firstImg+"해충 이름"+BugName+"해충설명 [0]"+BugExplain[0]+"해충 피해 이미지 이름"+BugHardName+
                        "해충 피해 이미지"+BugHardImg+"해충 이미지"+BugImg+"다운로드 링크"+downLink+"유투브 pid"+youTubePid);*/
                //Log.d("유투브 아이디",youTubePid);

                YoutubeSet(youTubePid);
            }
        });

    }




}
