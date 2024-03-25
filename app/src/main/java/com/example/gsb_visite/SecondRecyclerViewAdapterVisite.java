package com.example.gsb_visite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class SecondRecyclerViewAdapterVisite extends RecyclerView.Adapter<SecondRecyclerViewAdapterVisite.SecondRecyclerViewHolderVisite>{

    private List<Visite> visiteList;

    public SecondRecyclerViewAdapterVisite(List<Visite> visiteList) {
        this.visiteList = visiteList;
    }

    @NonNull
    @Override
    public SecondRecyclerViewHolderVisite onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        SecondRecyclerViewHolderVisite viewHolder;
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_visites, parent, false);
        viewHolder = new SecondRecyclerViewHolderVisite(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SecondRecyclerViewHolderVisite holder, int position) {
        holder.textViewVisiteDate.setText(visiteList.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return visiteList.size();
    }

    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public class SecondRecyclerViewHolderVisite extends RecyclerView.ViewHolder {
        TextView textViewVisiteDate;

        public SecondRecyclerViewHolderVisite(@NonNull View itemView) {
            super(itemView);
            textViewVisiteDate = itemView.findViewById(R.id.textViewRecyclerVisiteDate);
        }
    }
}