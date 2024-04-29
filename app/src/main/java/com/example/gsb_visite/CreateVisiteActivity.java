package com.example.gsb_visite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.gsb_visite.databinding.ActivityCreateVisiteBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateVisiteActivity extends AppCompatActivity {

    private ActivityCreateVisiteBinding binding;
    private Motif motifs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityCreateVisiteBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent myIntent = getIntent();
        Praticien praticien = (Praticien) myIntent.getSerializableExtra("praticien");
        Visiteur visiteur = (Visiteur) myIntent.getSerializableExtra("visiteur");

        binding.buttonCreateVisiteReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        binding.buttonEnregistrer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dateString = binding.editTextDate.getText().toString();
                String motifLibelle = binding.editTextMotif.getText().toString();
                String commentaire = binding.editTextCommentaire.getText().toString();

                VisiteRequest visiteRequest = new VisiteRequest();
                visiteRequest.setDate(dateString);
                visiteRequest.setCommentaire(commentaire);
                visiteRequest.setVisiteur(visiteur.getId());
                visiteRequest.setPraticien(praticien.getId());
                visiteRequest.setMotif(motifLibelle);

                GSBServices service = RetrofitClientInstance.getRetrofitInstance().create(GSBServices.class);
                Call<VisiteRequest> callCreateVisite = service.createVisite(visiteur.getToken(), visiteRequest);
                callCreateVisite.enqueue(new Callback<VisiteRequest>() {
                    @Override
                    public void onResponse(Call<VisiteRequest> call, Response<VisiteRequest> response) {
                        Toast.makeText(CreateVisiteActivity.this, "Visite créée avec succès" , Toast.LENGTH_SHORT).show();
                        Intent resultIntent = new Intent();
                        resultIntent.putExtra("newVisite", visiteRequest);
                        setResult(1, resultIntent);
                        finish();
                    }

                    @Override
                    public void onFailure(Call<VisiteRequest> call, Throwable t) {
                        Toast.makeText(CreateVisiteActivity.this, "Erreur lors de la création de la visite" , Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }
}