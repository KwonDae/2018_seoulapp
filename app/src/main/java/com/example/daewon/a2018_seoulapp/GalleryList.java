package com.example.daewon.a2018_seoulapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.daewon.a2018_seoulapp.Activity.BaseActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class GalleryList extends BaseActivity {

    private RecyclerView recyclerView;
    private List<ImageDTO> imageDTOs = new ArrayList<>();
    private List<String> uidLists = new ArrayList<>();
    private FirebaseDatabase database;
    private String temp1,temp2,temp3,temp4,temp5;
    private int count=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery_list);

        database = FirebaseDatabase.getInstance();
        recyclerView = findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        final GalleryListAdapter galleryListAdapter = new GalleryListAdapter();
        recyclerView.setAdapter(galleryListAdapter);

        database.getReference().child("Gallerys").child("강서구").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                imageDTOs.clear();
                for(DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    ImageDTO imageDTO = snapshot.getValue(ImageDTO.class);
                    temp1 = snapshot.child("Gallery_imgs").child("01").getValue().toString();
                    imageDTOs.add(imageDTO);
                }
                galleryListAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    class GalleryListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_gallery,parent,false);
            return new CustomViewHolder(view);
        }
        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ((CustomViewHolder)holder).textView.setText(imageDTOs.get(position).Gallery_name);
            ((CustomViewHolder)holder).textView2.setText(imageDTOs.get(position).Gallery_location_from_list);
            Glide.with(holder.itemView.getContext()).load(temp1).into(((CustomViewHolder)holder).imageView);

        }

        @Override
        public int getItemCount() {
            return imageDTOs.size();
        }

        private class CustomViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            TextView textView;
            TextView textView2;
            public CustomViewHolder(View view) {
                super(view);
                imageView = view.findViewById(R.id.item_imageView);
                textView = view.findViewById(R.id.item_textView);
                textView2 = view.findViewById(R.id.item_textView2);
            }
        }
    }
}
