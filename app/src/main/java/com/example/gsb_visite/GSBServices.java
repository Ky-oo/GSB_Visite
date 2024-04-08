package com.example.gsb_visite;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GSBServices {
    @POST("auth/login")
    Call<Visiteur> connectUser(@Body Visiteur visiteur);

    @GET("visiteur/{id}")
    Call<Visiteur> getVisiteur(@Header("Authorization") String authorization, @Path("id") String id);

    @GET("praticien")
    Call<List<Praticien>> getPraticiens(@Header("Authorization") String authorization);

    @GET("visite/praticien/{id}")
    Call<List<Visite>> getVisiteByPraticien(@Header("Authorization") String authorization, @Path("id") String id);

    @POST("visite")
    Call<Visite> createVisite(@Header("Authorization") String authorization, @Body String date_visite, @Body String commentaire, @Body String visiteur, @Body String praticien, @Body String motif);
}
