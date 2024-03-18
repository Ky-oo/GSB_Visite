package com.example.gsb_visite;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface GSBServices {
    @GET("motif")
    Call<Motif> getMotif();
    @POST("auth/login")
    Call<Visiteur> connectUser(@Body Visiteur visiteur);

    @POST("visiteur/{id}")
    Call<Visiteur> getVisiteur(@Header("Authorization") String authorization, @Path("id") String id);
}
