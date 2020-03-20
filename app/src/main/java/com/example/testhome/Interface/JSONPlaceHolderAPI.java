package com.example.testhome.Interface;


import com.example.testhome.Modelo.ObjetoJSON;

import retrofit2.Call;
import retrofit2.http.GET;

//Metodo para obvtener info
public interface JSONPlaceHolderAPI {

    @GET("top_rated?api_key=22ca2445747ab8ef473c89b05cb451cc")

    Call<ObjetoJSON> getObjJSON();
    //Objeto que tiene la lista de peliculas.

}
