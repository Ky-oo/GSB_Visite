package com.example.gsb_visite;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.gsb_visite.databinding.ActivityVisiteBinding;

import java.util.List;

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
        binding.textViewVisiteMotif.setText(visite.getMotif().getLibelle());

        GSBServices service = RetrofitClientInstance.getRetrofitInstance().create(GSBServices.class);
        Call<List<Echantillon>> callGetEchantillon = service.getEchantillons(visiteur.getToken());
        callGetEchantillon.enqueue(new Callback<List<Echantillon>>() {
            @Override
            public void onResponse(Call<List<Echantillon>> call, Response<List<Echantillon>> response) {
                if (response.isSuccessful()) {
                    List<Echantillon> echantillons = response.body();
                    ArrayAdapter<Echantillon> adapter = new ArrayAdapter<Echantillon>(getApplicationContext(), android.R.layout.simple_spinner_item, echantillons);                    adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                    binding.spinnerEchantillons.setAdapter(adapter);

                    binding.ButtonSaveEchantillon.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {

                            Echantillon selectedEchantillon = (Echantillon) binding.spinnerEchantillons.getSelectedItem();

                            GSBServices service = RetrofitClientInstance.getRetrofitInstance().create(GSBServices.class);
                            Call<Visite> callAddEchantillon = service.addEchantillonToVisite(visiteur.getToken(), visite.getId(), selectedEchantillon.getId());
                            callAddEchantillon.enqueue(new Callback() {
                                @Override
                                public void onResponse(Call call, Response response) {
                                    if (response.isSuccessful()) {
                                        Toast.makeText(VisiteActivity.this, "Echantillon ajouté", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(VisiteActivity.this, "Erreur ajout echantillon", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call call, Throwable t) {
                                    Toast.makeText(VisiteActivity.this, "Erreur ajout echantillon OnFailure", Toast.LENGTH_SHORT).show();
                                }
                            });
                        }
                    });

                } else {
                    Toast.makeText(VisiteActivity.this, "Erreur recupération echantillons", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<Echantillon>> call, Throwable t) {
                Toast.makeText(VisiteActivity.this, "Erreur recupération echantillons", Toast.LENGTH_SHORT).show();
            }
        });


        binding.buttonVisiteReturn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}