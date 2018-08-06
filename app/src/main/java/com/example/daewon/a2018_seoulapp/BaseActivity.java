package com.example.daewon.a2018_seoulapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class BaseActivity extends AppCompatActivity{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = getWindow().getDecorView();
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if( view != null) {
                //23 버젼 이상일 때 상태바 하얀 색상에 회색 아이콘 색상을 설정
                view.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
                getWindow().setStatusBarColor(Color.parseColor("#f2f2f2"));
            }
        } else if( Build.VERSION.SDK_INT >= 21) {
            //21 버젼 이상일 때
            getWindow().setStatusBarColor(Color.BLACK);
        }

        //액션바 설정하기//
        //액션바 타이틀 변경하기
        getSupportActionBar().setTitle("갤러리숲");
        //액션바 배경색 변경
        getSupportActionBar().setBackgroundDrawable(new ColorDrawable(0xFF339999));
        //홈버튼 표시
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //액션바 숨기기
        //hideActionBar();

    }

    //액션버튼 메뉴 액션바에 집어 넣기
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    //액션버튼을 클릭했을때의 동작
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            Toast.makeText(this, "뒤로가기 클릭", Toast.LENGTH_SHORT).show();
            return true;
        }
        if (id == R.id.action_search) {
            finish();
            Toast.makeText(this, "검색 클릭", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), Topgallery.class));
            return true;
        }

        if (id == R.id.action_button2) {
            Toast.makeText(this, "액션버튼 클릭", Toast.LENGTH_SHORT).show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void hideActionBar() {
        android.support.v7.app.ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.hide();
    }


}