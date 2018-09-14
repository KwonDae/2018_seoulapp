package com.example.daewon.a2018_seoulapp.Activity;

import android.content.ComponentName;
import android.content.Intent;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import com.example.daewon.a2018_seoulapp.R;

public class MapActivity extends BaseActivity {

    Intent intent = new Intent(this,Map_sub_Activity.class);
    private long mLastClickTime;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
    }

    public void clickbtn(View v){
        if(SystemClock.elapsedRealtime()-mLastClickTime < 1000){
            return;
        }
        mLastClickTime = SystemClock.elapsedRealtime();
        Intent intent = new Intent(this,Map_sub_Activity.class);
        switch (v.getId()){
            case R.id.dobong:
                intent.putExtra("name","도봉구");
                break;
            case R.id.gangbook:
                intent.putExtra("name","강북구");
                break;
            case R.id.jongro:
                intent.putExtra("name","종로구");
                break;
            case R.id.nowon:
                intent.putExtra("name","노원구");
                break;
            case R.id.eunpyoung:
                intent.putExtra("name","은평구");
                break;
            case R.id.seodamoon:
                intent.putExtra("name","서대문구");
                break;
            case R.id.mapo:
                intent.putExtra("name","마포구");
                break;
            case R.id.gangseo:
                intent.putExtra("name","강서구");
                break;
            case R.id.yangcheon:
                intent.putExtra("name","양천구");
                break;
            case R.id.ydp:
                intent.putExtra("name","영등포구");
                break;
            case R.id.guro:
                intent.putExtra("name","구로구");
                break;
            case R.id.guemcheon:
                intent.putExtra("name","금천구");
                break;
            case R.id.dongjak:
                intent.putExtra("name","동작구");
                break;
            case R.id.yoongsan:
                intent.putExtra("name","용산구");
                break;
            case R.id.jung:
                intent.putExtra("name","중구");
                break;
            case R.id.gangnam:
                intent.putExtra("name","강남구");
                break;
            case R.id.seocho:
                intent.putExtra("name","서초구");
                break;
            case R.id.sungdong:
                intent.putExtra("name","성동구");
                break;
            case R.id.jungrang:
                intent.putExtra("name","중랑구");
                break;
            case R.id.gangdong:
                intent.putExtra("name","강동구");
                break;
            case R.id.gwangjin:
                intent.putExtra("name","광진구");
                break;
            case R.id.gwanak:
                intent.putExtra("name","관악구");
                break;
            case R.id.dongdamoon:
                intent.putExtra("name","동대문구");
                break;
            case R.id.sungbook:
                intent.putExtra("name","성북구");
                break;
            case R.id.songpa:
                intent.putExtra("name","송파구");
                break;
        }
        startActivity(intent);
    }
}
