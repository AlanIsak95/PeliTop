package com.ThechnoSk.testhome.Interface;


import com.ThechnoSk.testhome.Modelo.ObjetoJSON;
import com.ThechnoSk.testhome.Util.Constants;

import retrofit2.Call;
import retrofit2.http.GET;

public interface JSONPlaceHolderAPI {

    //Con retrofit utilizamos la anotacion GET
    @GET(Constants.API_POINT+Constants.API_VAR_KEY+Constants.API_KEY+Constants.API_VAR_LENG+Constants.API_LENG)

    //metodo que regresa el objeto con la lista de peliculas dentro
    Call<ObjetoJSON> getObjJSON();

}
