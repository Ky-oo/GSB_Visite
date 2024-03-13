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

                    GSBServices service = RetrofitClientInstance.getRetrofitInstance().create(GSBServices.class);
                    Call<Visiteur> call = service.connectUser(email, password);

                    call.enqueue(new Callback<Visiteur>() {
                        @Override
                        public void onResponse(Call<Visiteur> call, Response<Visiteur>
                                response) {
                            response.body();
                        }

                        @Override
                        public void onFailure(Call<Visiteur> call, Throwable t) {
                            Toast.makeText(MainActivity.this, "Une erreur est survenue !",
                                    Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
    }
}