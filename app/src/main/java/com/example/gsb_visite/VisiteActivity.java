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
        String token = myIntent.getSerializableExtra("token").toString();

        binding.textViewVisiteDate.setText(visite.getDate());
        binding.textViewVisiteId.setText(visite.getId());
        binding.textViewVisiteCommentaire.setText(visite.getCommentaire());

        GSBServices service = RetrofitClientInstance.getRetrofitInstance().create(GSBServices.class);
        Call<Motif> callGetMotif = service.getMotifById(token, visite.getMotif());
        callGetMotif.enqueue(new Callback<Motif>() {
            @Override
            public void onResponse(Call<Motif> call, Response<Motif> response) {
                if (response.isSuccessful() && response.body() != null) {
                    Motif motif = response.body();
                    binding.textViewVisiteMotif.setText(motif.getLibelle());
                    Toast.makeText(VisiteActivity.this, "Ok", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<Motif> call, Throwable t) {
                Toast.makeText(VisiteActivity.this, "Erreur", Toast.LENGTH_SHORT).show();
            }
    });
    }
}