package com.daemon.fiancy.recyclers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.daemon.fiancy.R;
import com.daemon.fiancy.models.Advertisements;

import java.util.ArrayList;

public class RecyclerViewForMatchFinder extends RecyclerView.Adapter<ViewHolderForMatchFinder> {

    private ArrayList<Advertisements> dbAdList = new ArrayList<>();
    private Context mContext;

    public RecyclerViewForMatchFinder(ArrayList<Advertisements> dbAdList, Context mContext) {
        this.dbAdList = dbAdList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolderForMatchFinder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_matchfinder, parent, false);
        ViewHolderForMatchFinder viewHolderForMatchFinder = new ViewHolderForMatchFinder(view);
        return viewHolderForMatchFinder;
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onBindViewHolder(@NonNull ViewHolderForMatchFinder holder, final int position) {

        Advertisements advertisement = dbAdList.get(position);

        Glide.with(mContext)
                .asBitmap().load(advertisement.getImage1())
                .into(holder.image);

        holder.imageName.setText(advertisement.getFullname());
        holder.location.setText(advertisement.getAddress());
        holder.age.setText(advertisement.getAge());
        holder.gender.setText(advertisement.getGender());
        holder.religion.setText(advertisement.getReligion());
        holder.profession.setText(advertisement.getProfession());
    }

    @Override
    public int getItemCount() {
        return dbAdList.size();
    }
}

