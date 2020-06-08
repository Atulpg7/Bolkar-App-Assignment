package com.example.assignment_atul.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment_atul.Adapters.Model.HorizontalView;
import com.example.assignment_atul.Adapters.Model.VerticalView;
import com.example.assignment_atul.R;

import java.util.ArrayList;

public class VerticalRecyclerViewAdapter extends RecyclerView.Adapter<VerticalRecyclerViewAdapter.RVViewHolder>{

    Context context;

    // ArrayList of Vertical data ie: titles and videos list as per title
    ArrayList<VerticalView> arrayList;


    public VerticalRecyclerViewAdapter(Context context, ArrayList<VerticalView> arrayList) {
        this.context = context;
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public RVViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        // Setting view to return.
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_main_list,parent,false);
        return new RVViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RVViewHolder holder, int position) {


        // Fetching data from arralist
        VerticalView verticalView= arrayList.get(position);


        // Setting main title of videos
        String title=verticalView.getTitle();
        holder.title.setText(title);


        // Fetching videos according to title
        ArrayList<HorizontalView> arrayList=verticalView.getArrayList();



        // Horizontal adapter called to insert videos in
        // horizontal order in recycler view
        HorizontalRecyclerViewAdapter horizontalRecyclerView=new HorizontalRecyclerViewAdapter(context,arrayList);



        // Setting properties and adapter in recycle view
        holder.recyclerView.setHasFixedSize(true);
        holder.recyclerView.setLayoutManager(new LinearLayoutManager(context,LinearLayoutManager.HORIZONTAL,false));
        holder.recyclerView.setAdapter(horizontalRecyclerView);


    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class RVViewHolder extends  RecyclerView.ViewHolder{

        // Getting reference of TextView and Horizontal Recycle View for videos
        RecyclerView recyclerView;
        TextView title;

        public RVViewHolder(@NonNull View itemView) {
            super(itemView);
            recyclerView=itemView.findViewById(R.id.recycler_view);
            title=itemView.findViewById(R.id.title);
        }
    }
}
