package com.example.gsb_visite;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewHolder> {
    @NonNull
    @Override
    public RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class RecyclerViewHolder extends RecyclerView.ViewHolder {
        private TextView textViewNom;
        private TextView textViewPrenom;
        private TextView textViewTel;
        private TextView textViewEmail;
        private TextView textViewRue;
        private TextView textViewCodePostal;
        private TextView textViewVille;

        public RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            textViewNom = itemView.findViewById(R.id.textViewNom);
            textViewPrenom = itemView.findViewById(R.id.textViewPrenom);
            textViewTel = itemView.findViewById(R.id.textViewTel);
            textViewEmail = itemView.findViewById(R.id.textViewEmail);
            textViewRue = itemView.findViewById(R.id.textViewRue);
            textViewCodePostal = itemView.findViewById(R.id.textViewCodePostal);
            textViewVille = itemView.findViewById(R.id.textViewVille);
        }
    }
}


