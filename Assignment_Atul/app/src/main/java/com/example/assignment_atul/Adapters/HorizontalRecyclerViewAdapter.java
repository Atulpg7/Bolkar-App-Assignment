package com.example.assignment_atul.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.assignment_atul.Adapters.Model.HorizontalView;
import com.example.assignment_atul.R;

import java.util.ArrayList;

public class HorizontalRecyclerViewAdapter extends RecyclerView.Adapter<HorizontalRecyclerViewAdapter.HRAViewHolder> {


    Context context;

    // Array to store video's details
    ArrayList<HorizontalView> arrayList;

    String urlPlay = "https://bolkar.s3.ap-south-1.amazonaws.com/demo/play_new.webp";

    public HorizontalRecyclerViewAdapter(Context context, ArrayList<HorizontalView> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public HRAViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Setting layout to return view
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_videos,parent,false);
        return  new HRAViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull HRAViewHolder holder, int position) {

        final HorizontalView horizontalView=arrayList.get(position);


        // Setting Videos thumbnail
        holder.textView.setText(horizontalView.getName());


        // making toast to show description of any video after click
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context, "Description: "+horizontalView.getDescription(), Toast.LENGTH_SHORT).show();
            }
        });


        // Fetching image for particular video
        String url = horizontalView.getImageUrl();
        Glide.with(context).load(url).centerCrop().into(holder.imageView);


        //fetching image of play button
        Glide.with(context).load(urlPlay).centerCrop().into(holder.imageViewPlay);

    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class HRAViewHolder extends RecyclerView.ViewHolder {

        // Getting reference of all the imageViews and Text Vies for Video from
        // custom horizontal view file
        ImageView imageView;
        ImageView imageViewPlay;
        TextView textView;
        public HRAViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView=itemView.findViewById(R.id.image);
            imageViewPlay=itemView.findViewById(R.id.imagePlay);
            textView=itemView.findViewById(R.id.title);

        }
    }
}
