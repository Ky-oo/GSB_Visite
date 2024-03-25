package com.example.gsb_visite;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.gsb_visite.databinding.ActivityAcceuilBinding;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Acceuil extends AppCompatActivity {

    private ActivityAcceuilBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityAcceuilBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        Intent myIntent = getIntent();
        Visiteur visiteur = (Visiteur) myIntent.getSerializableExtra("visiteur");

        binding.TextViewNameAcceuil.setText("Bonjour " + visiteur.getPrenom());

        GSBServices service = RetrofitClientInstance.getRetrofitInstance().create(GSBServices.class);
        Call<List<Praticien>> callGetPraticien = service.getPraticiens(visiteur.getToken());
        callGetPraticien.enqueue(new Callback<List<Praticien>>() {
            @Override
            public void onResponse(Call<List<Praticien>> call, Response<List<Praticien>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    List<Praticien> praticiens = response.body();
                    binding.recyclerViewPraticiens.setHasFixedSize(true);
                    LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
                    binding.recyclerViewPraticiens.setLayoutManager(layoutManager);
                    binding.recyclerViewPraticiens.setFocusable(false);

                    RecyclerViewAdapter adapterPraticiens = new RecyclerViewAdapter(praticiens);
                    binding.recyclerViewPraticiens.setAdapter(adapterPraticiens);

                    binding.recyclerViewPraticiens.addOnItemTouchListener(new RecyclerTouchListener(getApplicationContext(), binding.recyclerViewPraticiens, new RecyclerViewClickListener() {
                        @Override
                        public void onClick(View view, int position) {
                            Praticien praticien = praticiens.get(position);
                            Intent myIntent = new Intent(getApplicationContext(), PraticienActivity.class);
                            myIntent.putExtra("praticien", praticien);
                            myIntent.putExtra("token", visiteur.getToken());
                            startActivity(myIntent);
                        }
                    }));

                } else {

                }
            }

            @Override
            public void onFailure(Call<List<Praticien>> call, Throwable t) {
            }
        });
    }
}
