package com.example.gsb_visite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.gsb_visite.databinding.ActivityPraticienBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PraticienActivity extends AppCompatActivity {
    private ActivityPraticienBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityPraticienBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent myIntent = getIntent();
        Praticien praticien = (Praticien) myIntent.getSerializableExtra("praticien");
        Visiteur visiteur = (Visiteur) myIntent.getSerializableExtra("visiteur");

        binding.textViewPraticienNom.setText(praticien.getNom() + " " + praticien.getPrenom());
        binding.textViewPraticienEmail.setText(praticien.getEmail());
        binding.textViewPraticienTelephone.setText(praticien.getTel());
        binding.textViewPraticienAdresse.setText(praticien.getRue());
        binding.textViewPraticienVille.setText(praticien.getVille());

        GSBServices service = RetrofitClientInstance.getRetrofitInstance().create(GSBServices.class);
        Call<List<Visite>> callGetVisiteByPraticien = service.getVisiteByPraticien(visiteur.getToken(), praticien.getId());
        callGetVisiteByPraticien.enqueue(new Callback<List<Visite>>() {
            @Override
            public void onResponse(Call<List<Visite>> call, Response<List<Visite>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Visite> visites = response.body();
                    binding.recyclerViewVisites.setHasFixedSize(true);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                    binding.recyclerViewVisites.setLayoutManager(layoutManager);
                    binding.recyclerViewVisites.setFocusable(false);

                    SecondRecyclerViewAdapterVisite adapterVisites = new SecondRecyclerViewAdapterVisite(visites);
                    binding.recyclerViewVisites.setAdapter(adapterVisites);

                    binding.recyclerViewVisites.addOnItemTouchListener(new SecondRecyclerTouchListener(getApplicationContext(), binding.recyclerViewVisites, new SecondRecyclerViewClickListener() {
                        @Override
                        public void onClick(View view, int position) {
                            Visite visite = visites.get(position);
                            Intent myIntent = new Intent(getApplicationContext(), VisiteActivity.class);
                            myIntent.putExtra("visite", visite);
                            myIntent.putExtra("visiteur", visiteur);
                            startActivity(myIntent);
                        }
                    }));

                } else {
                    Toast.makeText(PraticienActivity.this, "Get Visite failed", Toast.LENGTH_SHORT).show();
                }
                }

            @Override
            public void onFailure(Call<List<Visite>> call, Throwable t) {
                Toast.makeText(PraticienActivity.this, "Get Visite failed", Toast.LENGTH_SHORT).show();
            }
        });

        binding.buttonPraticienReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.buttonCreateVisite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(getApplicationContext(), CreateVisiteActivity.class);
                myIntent.putExtra("praticien", praticien);
                myIntent.putExtra("visiteur", visiteur);
                startActivity(myIntent);
            }
        });
    }
}