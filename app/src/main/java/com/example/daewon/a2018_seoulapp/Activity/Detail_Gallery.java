package com.example.daewon.a2018_seoulapp.Activity;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.daewon.a2018_seoulapp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Detail_Gallery extends BaseActivity  {

    TextView Welcome_text;
    TextView Gallery_name;
    TextView Gallery_explain;
    TextView Owner_explain;
    TextView Owner_insta;
    TextView Gallery_location_big;
    TextView Gallery_location;
    TextView Gallery_time;
    TextView Gallery_fee;
    private String Detail_Loc;
    private String Detail_Name;
    private ImageView imageView1;
    private ImageView imageView2;
    private ImageView imageView3;
    private ImageView imageView4;
    private ImageView imageView5;
    private ImageView imageView6;

    private List<String> img_lists = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail__gallery);

        Intent intent=getIntent();
        Detail_Loc = intent.getExtras().getString("Location");
        Detail_Name = intent.getExtras().getString("Name");

        Welcome_text = (TextView)findViewById(R.id.textView_title);
        imageView1 = (ImageView)findViewById(R.id.imageView1);
        imageView2 = (ImageView)findViewById(R.id.imageView2);
        imageView3 = (ImageView)findViewById(R.id.imageView3);
        imageView4 = (ImageView)findViewById(R.id.imageView4);
        imageView5 = (ImageView)findViewById(R.id.imageView5);
        imageView6 = (ImageView)findViewById(R.id.imageView6);
        Gallery_name = (TextView)findViewById(R.id.Gallery_name);
        Gallery_explain= (TextView)findViewById(R.id.Gallery_explain);
        Owner_explain= (TextView)findViewById(R.id.Owner_explain);
        Owner_insta= (TextView)findViewById(R.id.Owner_insta);
        Gallery_location_big = (TextView)findViewById(R.id.Gallery_location_big);
        Gallery_location= (TextView)findViewById(R.id.Gallery_location);
        Gallery_time= (TextView)findViewById(R.id.Gallery_time);
        Gallery_fee= (TextView)findViewById(R.id.Gallery_fee);

        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference Gal_Ref = rootRef.child("Gallerys");
        DatabaseReference category_Ref = Gal_Ref.child(Detail_Loc);

        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    if(Detail_Name.equals(ds.getKey().toString())){
                        Welcome_text.setText(Detail_Name+ " 갤러리 소개 페이지");
                        Gallery_name.setText(ds.child("Gallery_name").getValue().toString());
                        Gallery_explain.setText(ds.child("Gallery_explain").getValue().toString());
                        Owner_explain.setText(ds.child("Owner_explain").getValue().toString());
                        Owner_insta.setText(ds.child("Owner_insta").getValue().toString());
                        Gallery_location.setText(ds.child("Gallery_location").getValue().toString());
                        Gallery_time.setText(ds.child("Gallery_time").getValue().toString());
                        Gallery_fee.setText(ds.child("Gallery_fee").getValue().toString());
                        Gallery_location_big.setText(Detail_Loc);

                        if(ds.child("Gallery_imgs").hasChild("01")){
                            img_lists.add(ds.child("Gallery_imgs").child("01").getValue().toString());
                        }
                        if(ds.child("Gallery_imgs").hasChild("02")){
                            img_lists.add(ds.child("Gallery_imgs").child("02").getValue().toString());
                        }
                        if(ds.child("Gallery_imgs").hasChild("03")){
                            img_lists.add(ds.child("Gallery_imgs").child("03").getValue().toString());
                        }
                        if(ds.child("Gallery_imgs").hasChild("04")){
                            img_lists.add(ds.child("Gallery_imgs").child("04").getValue().toString());
                        }
                        if(ds.child("Gallery_imgs").hasChild("05")){
                            img_lists.add(ds.child("Gallery_imgs").child("05").getValue().toString());
                        }
                        if(ds.child("Gallery_imgs").hasChild("06")){
                            img_lists.add(ds.child("Gallery_imgs").child("06").getValue().toString());
                        }
                        push_imgs(img_lists);
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        category_Ref.addListenerForSingleValueEvent(valueEventListener);
    }

    public void push_imgs(final List img_list){

        if(img_list.size() == 1){
            imageView2.setVisibility(View.GONE);
            imageView3.setVisibility(View.GONE);
            imageView4.setVisibility(View.GONE);
            imageView5.setVisibility(View.GONE);
            imageView6.setVisibility(View.GONE);
        }
        if(img_list.size() == 2){
            imageView3.setVisibility(View.GONE);
            imageView4.setVisibility(View.GONE);
            imageView5.setVisibility(View.GONE);
            imageView6.setVisibility(View.GONE);
        }
        if(img_list.size() == 3){
            imageView4.setVisibility(View.GONE);
            imageView5.setVisibility(View.GONE);
            imageView6.setVisibility(View.GONE);
        }
        if(img_list.size() == 4){
            imageView5.setVisibility(View.GONE);
            imageView6.setVisibility(View.GONE);
        }
        if(img_list.size() == 5){
            imageView6.setVisibility(View.GONE);
        }
        try{
            if(img_list.get(0) != null){
                Glide.with(this).load(img_list.get(0)).into(imageView1);
                imageView1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String url = (String)img_list.get(0);
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                    }
                });
            }
            if(img_list.get(1) != null){
                Glide.with(this).load(img_list.get(1)).into(imageView2);
                imageView2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String url = (String)img_list.get(1);
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                    }
                });
            }
            if(img_list.get(2) != null){
                Glide.with(this).load(img_list.get(2)).into(imageView3);
                imageView3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String url = (String)img_list.get(2);
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                    }
                });
            }
            if(img_list.get(3) != null){
                Glide.with(this).load(img_list.get(3)).into(imageView4);
                imageView4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String url = (String)img_list.get(3);
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                    }
                });
            }
            if(img_list.get(4) != null){
                Glide.with(this).load(img_list.get(4)).into(imageView5);
                imageView5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String url = (String)img_list.get(4);
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                    }
                });
            }
            if(img_list.get(5) != null){
                Glide.with(this).load(img_list.get(5)).into(imageView6);
                imageView6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String url = (String)img_list.get(5);
                        Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                        startActivity(intent);
                    }
                });
            }

        }catch (Exception e){

        }
    }

}
