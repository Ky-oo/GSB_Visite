package com.example.gsb_visite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.gsb_visite.databinding.ActivityVisiteBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VisiteActivity extends AppCompatActivity {
    private ActivityVisiteBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityVisiteBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent myIntent = getIntent();
        Visite visite = (Visite) myIntent.getSerializableExtra("visite");
        Visiteur visiteur = (Visiteur) myIntent.getSerializableExtra("visiteur");

        binding.textViewVisiteDate.setText(visite.getDate());
        binding.textViewVisiteId.setText(visite.getId());
        binding.textViewVisiteCommentaire.setText(visite.getCommentaire());

        binding.buttonVisiteReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}