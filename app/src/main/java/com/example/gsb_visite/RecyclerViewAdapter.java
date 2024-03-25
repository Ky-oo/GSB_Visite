package com.example.gsb_visite;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {
    private List<Praticien> dataModelList;

    public RecyclerViewAdapter(List<Praticien> dataModelList) {
        this.dataModelList = dataModelList;
    }

    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerViewHolder viewHolder;
        View view  = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_praticien, parent, false);
        viewHolder = new RecyclerViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {
        holder.textViewPraticienNom.setText(dataModelList.get(position).getNom());
        holder.textViewPraticienPrenom.setText(dataModelList.get(position).getPrenom());
    }

    @Override
    public int getItemCount() {
        return dataModelList.size();
    }

    public long getItemId(int position) {
        return super.getItemId(position);
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewPraticienNom;
        public TextView textViewPraticienPrenom;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewPraticienNom = itemView.findViewById(R.id.textViewRecyclerPraticienNom);
            textViewPraticienPrenom = itemView.findViewById(R.id.textViewRecyclerPraticienPrenom);
        }
    }
}


