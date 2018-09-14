package com.example.daewon.a2018_seoulapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.daewon.a2018_seoulapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class Map_sub_Activity extends BaseActivity {
    private List<My_gallery_read_data> my_gallery_read_datas = new ArrayList<>();
    FirebaseDatabase database;
    FirebaseAuth firebaseAuth;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_sub);
        database = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();

        Intent intent = getIntent();

        if(intent != null){
            String region = intent.getStringExtra("name");


        }

    }
}
