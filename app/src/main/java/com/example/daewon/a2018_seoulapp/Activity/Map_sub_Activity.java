package com.example.daewon.a2018_seoulapp.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.daewon.a2018_seoulapp.GalleryList;
import com.example.daewon.a2018_seoulapp.ImageDTO;
import com.example.daewon.a2018_seoulapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.MutableData;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Map_sub_Activity extends BaseActivity {
    private RecyclerView recyclerView;
    private List<ImageDTO> imageDTOs = new ArrayList<>();
    private List<String> uidLists = new ArrayList<>();
    private FirebaseDatabase database;
    private FirebaseAuth firebaseAuth;
    private FirebaseAuth auth;
    String region;
    int check;
    int position;

    private String temp1,temp2,temp3,temp4,temp5;
    private int count=0;

    private ImageButton best5, find_gallery, mypage;
    int i = 4;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map_sub);

        auth = FirebaseAuth.getInstance();
        Intent intent = getIntent();

        if(intent != null){
            region = intent.getStringExtra("name");
        }

        database = FirebaseDatabase.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        FirebaseUser user = firebaseAuth.getCurrentUser();

        recyclerView = (RecyclerView)findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final BoardRecylcerViewAdapter boardRecylcerViewAdapter = new BoardRecylcerViewAdapter();
        recyclerView.setAdapter(boardRecylcerViewAdapter);

        best5 = findViewById(R.id.best5);
        find_gallery = findViewById(R.id.find_gallery);
        mypage = findViewById(R.id.mypage);

        database.getReference().child("Gallerys").child(region).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                imageDTOs.clear();
                uidLists.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()){
                    ImageDTO imageDTO = snapshot.getValue(ImageDTO.class);
                    String uidKey = snapshot.getKey();
                    imageDTOs.add(imageDTO);
                    uidLists.add(uidKey);
                }
                boardRecylcerViewAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

        best5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i != 1) {
                    best5.setImageResource(R.drawable.best5_on);
                    find_gallery.setImageResource(R.drawable.gallery_off);
                    mypage.setImageResource(R.drawable.mypage_off);
                    Intent intent = new Intent(Map_sub_Activity.this, GalleryList.class);
                    finish();
                    startActivity(intent);
                    i =1;
                }
            }
        });

        find_gallery.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i != 2) {
                    best5.setImageResource(R.drawable.best5_off);
                    find_gallery.setImageResource(R.drawable.gallery_on);
                    mypage.setImageResource(R.drawable.mypage_off);
                    i = 2;
                    Intent intent = new Intent(Map_sub_Activity.this, MapActivity.class);
                    finish();
                    startActivity(intent);
                }
            }
        });

        mypage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(i != 3) {
                    best5.setImageResource(R.drawable.best5_off);
                    find_gallery.setImageResource(R.drawable.gallery_off);
                    mypage.setImageResource(R.drawable.mypage_on);
                    i = 3;
                    Intent intent = new Intent(Map_sub_Activity.this, MyPage.class);
                    finish();
                    startActivity(intent);
                }
            }
        });
    }

    class BoardRecylcerViewAdapter extends  RecyclerView.Adapter<RecyclerView.ViewHolder>{

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gallery,parent,false);

            return new CustomViewHolder(view);
        }

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position){
            ((CustomViewHolder)holder).textView.setText(imageDTOs.get(position).Gallery_name);
            ((CustomViewHolder)holder).textView2.setText(imageDTOs.get(position).Gallery_location_from_list);
            Glide.with(holder.itemView.getContext()).load(imageDTOs.get(position).Main_img).into(((CustomViewHolder)holder).imageView);
            ((CustomViewHolder)holder).starButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    onStarClicked(database.getReference().child("Gallerys").child(region).child(uidLists.get(position)));
                }
            });

            if (imageDTOs.get(position).stars.containsKey(auth.getCurrentUser().getUid())) {
                ((CustomViewHolder)holder).starButton.setImageResource(R.drawable.star);
                check = 1;
            } else {
                ((CustomViewHolder)holder).starButton.setImageResource(R.drawable.baseline_favorite_border_black_24);
                check = 0;
            }
        }

        @Override
        public int getItemCount() {
            return imageDTOs.size();
        }

        private void onStarClicked(DatabaseReference postRef) {
            postRef.runTransaction(new Transaction.Handler() {
                @Override
                public Transaction.Result doTransaction(MutableData mutableData) {
                    ImageDTO imageDTO = mutableData.getValue(ImageDTO.class);
                    if (imageDTO == null) {
                        return Transaction.success(mutableData);
                    }

                    if (imageDTO.stars.containsKey(auth.getCurrentUser().getUid())) {
                        // Unstar the post and remove self from stars
                        imageDTO.starCount = imageDTO.starCount - 1;
                        imageDTO.stars.remove(auth.getCurrentUser().getUid());
                    } else {
                        // Star the post and add self to stars
                        imageDTO.starCount = imageDTO.starCount + 1;
                        imageDTO.stars.put(auth.getCurrentUser().getUid(), true);
                    }

                    // Set value and report transaction success
                    mutableData.setValue(imageDTO);
                    return Transaction.success(mutableData);
                }

                @Override
                public void onComplete(DatabaseError databaseError, boolean b,
                                       DataSnapshot dataSnapshot) {
                    // Transaction completed

                }
            });
        }

        private class CustomViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView textView;
            TextView textView2;
            ImageView starButton;
            public CustomViewHolder(View view) {
                super(view);
                imageView = view.findViewById(R.id.item_imageView);
                textView = view.findViewById(R.id.item_textView);
                textView2 = view.findViewById(R.id.item_textView2);
                starButton = view.findViewById(R.id.starButton_imageView);

                view.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(view.getContext(),Detail_Gallery.class);
                        intent.putExtra("Location",textView2.getText().toString());
                        intent.putExtra("Name",textView.getText().toString());
                        if(check == 1 ) {
                            intent.putExtra("star",check);
                        }
                        else {
                            intent.putExtra("star",check);
                        }
                        startActivity(intent);
                    }
                });
            }
        }
    }
}
