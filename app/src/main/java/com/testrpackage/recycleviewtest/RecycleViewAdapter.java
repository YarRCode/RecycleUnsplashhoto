package com.testrpackage.recycleviewtest;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.MyViewHolder> {

    private final RecycleViewInterface recycleViewInterface;

    Context cnt;
    ArrayList<RecyckeModel> recycleModels;

    public RecycleViewAdapter(Context cnt, ArrayList<RecyckeModel> recycleModels, RecycleViewInterface recycleViewInterface) {
        this.cnt = cnt;
        this.recycleModels = recycleModels;
        this.recycleViewInterface = recycleViewInterface;
    }

    @NonNull
    @Override
    public RecycleViewAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(cnt);
        View view = inflater.inflate(R.layout.recycle_custom_view, parent, false);
        return new RecycleViewAdapter.MyViewHolder(view, recycleViewInterface);
    }

    @Override
    public void onBindViewHolder(@NonNull RecycleViewAdapter.MyViewHolder holder, int position) {
        holder.nameImage.setText(recycleModels.get(position).getNameImange());
        holder.authImage.setText(recycleModels.get(position).getAuthorName());

        int doubleToInt = (int) ((int) 300*recycleModels.get(position).getResol());

        Picasso.get().load(recycleModels.get(position).getUrlOfImage()).resize(doubleToInt, 300).onlyScaleDown().into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return recycleModels.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView nameImage;
        TextView authImage;

        public MyViewHolder(@NonNull View itemView, RecycleViewInterface recycleViewInterface) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            nameImage = itemView.findViewById(R.id.nameView);
            authImage = itemView.findViewById(R.id.authView);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recycleViewInterface != null) {
                        int position = getAdapterPosition();

                        if(position != RecyclerView.NO_POSITION) {
                            recycleViewInterface.onItemClick(position);
                        }
                    }
                }
            });
        }
    }
}
