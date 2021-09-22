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

public class RecycleViewAdapterforPending extends RecyclerView.Adapter<ViewHolderforPending> {

    private ArrayList<Advertisements> advertisementsArrayList = new ArrayList<>();
    private Context mContext;

    public RecycleViewAdapterforPending(ArrayList<Advertisements> advertisementsArrayList, Context mContext) {
        this.advertisementsArrayList = advertisementsArrayList;
        this.mContext = mContext;
    }

    @NonNull
    @Override
    public ViewHolderforPending onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_pending, parent, false);
        ViewHolderforPending viewHolderforPending = new ViewHolderforPending(view);
        return viewHolderforPending;
    }

    @SuppressLint("LongLogTag")
    @Override
    public void onBindViewHolder(@NonNull ViewHolderforPending holder, final int position) {

        Advertisements advertisement = advertisementsArrayList.get(position);

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
        return advertisementsArrayList.size();
    }
}

