package com.ThechnoSk.testhome.Interface;

import com.ThechnoSk.testhome.Modelo.Pelicula;

import java.util.List;

public interface IMain {

    public void ByPassActivity();
    public void getDataByRetrofit();
    public void cargarImagenesCarrusel(List<Pelicula> list);


}
