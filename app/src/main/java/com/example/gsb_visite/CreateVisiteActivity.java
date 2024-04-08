package com.example.gsb_visite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.Toast;

import com.example.gsb_visite.databinding.ActivityCreateVisiteBinding;
import com.example.gsb_visite.databinding.ActivityPraticienBinding;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CreateVisiteActivity extends AppCompatActivity {

    private ActivityCreateVisiteBinding binding;

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
                String motif = binding.editTextMotif.getText().toString();
                String commentaire = binding.editTextCommentaire.getText().toString();

                GSBServices service = RetrofitClientInstance.getRetrofitInstance().create(GSBServices.class);

                VisiteRequest visiteRequest = new VisiteRequest();
                visiteRequest.setDate(dateString);
                visiteRequest.setMotif(motif);
                visiteRequest.setCommentaire(commentaire);
                visiteRequest.setPraticien(praticien.getId());
                visiteRequest.setVisiteur(visiteur.getId());

                Call<Visite> callCreateVisite = service.createVisite(visiteur.getToken(), visiteRequest.getDate(), visiteRequest.getCommentaire(), visiteRequest.getVisiteur(), visiteRequest.getPraticien(), visiteRequest.getMotif());
                callCreateVisite.enqueue(new Callback<Visite>() {
                    @Override
                    public void onResponse(Call<Visite> call, Response<Visite> response) {
                        Toast.makeText(CreateVisiteActivity.this, "Visite créée avec succès" , Toast.LENGTH_SHORT).show();
                        finish();
                    }

                    @Override
                    public void onFailure(Call<Visite> call, Throwable t) {
                        Toast.makeText(CreateVisiteActivity.this, "Erreur lors de la création de la visite" , Toast.LENGTH_SHORT).show();
                    }
                });


            }
        });

    }
}