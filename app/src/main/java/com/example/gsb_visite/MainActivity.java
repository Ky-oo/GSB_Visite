package com.example.gsb_visite;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.gsb_visite.databinding.ActivityMainBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private Visiteur visiteur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        binding.buttonConnectionLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = binding.editTextEmailLogin.getText().toString();
                String password = binding.editTextPasswordLogin.getText().toString();
                visiteur = new Visiteur("walker.reinger84@yahoo.com", "123456789abcD!");

                GSBServices service = RetrofitClientInstance.getRetrofitInstance().create(GSBServices.class);
                Call<Visiteur> callConnectVisiteur = service.connectUser(visiteur);
                callConnectVisiteur.enqueue(new Callback<Visiteur>() {
                    @Override
                    public void onResponse(Call<Visiteur> call, Response<Visiteur> response) {
                        if (response.isSuccessful() && response.body() != null) {
                            visiteur.setId(response.body().getId());
                            visiteur.setToken("Bearer " + response.body().getToken());

                            // Une fois connecté, récupérer les détails du visiteur
                            Call<Visiteur> callGetVisiteur = service.getVisiteur(visiteur.getToken(), visiteur.getId());
                            callGetVisiteur.enqueue(new Callback<Visiteur>() {
                                @Override
                                public void onResponse(Call<Visiteur> call, Response<Visiteur> response) {
                                    if (response.isSuccessful() && response.body() != null) {
                                        visiteur.setNom(response.body().getNom());
                                        visiteur.setPrenom(response.body().getPrenom());
                                        visiteur.setTel(response.body().getTel());
                                        visiteur.setDate_embauche(response.body().getDate_embauche());

                                        Toast.makeText(MainActivity.this, "Connexion réussie.", Toast.LENGTH_SHORT).show();
                                    } else {
                                        Toast.makeText(MainActivity.this, "Impossible de récupérer les détails du visiteur.", Toast.LENGTH_SHORT).show();
                                    }
                                }

                                @Override
                                public void onFailure(Call<Visiteur> call, Throwable t) {
                                    Toast.makeText(MainActivity.this, "Une erreur est survenue lors de la récupération des détails du visiteur.", Toast.LENGTH_SHORT).show();
                                }
                            });
                        } else {
                            Toast.makeText(MainActivity.this, "Impossible de se connecter avec les informations fournies.", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<Visiteur> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "Une erreur est survenue lors de la connexion.", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });
    }

}
