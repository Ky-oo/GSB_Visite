package com.example.gsb_visite;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface GSBServices {
    @GET("motif")
    Call<Motif> getMotif();

    @FormUrlEncoded
    @POST("auth/login")
    Call<Visiteur> connectUser(@Field("email") String email, @Field("password") String password);
}
