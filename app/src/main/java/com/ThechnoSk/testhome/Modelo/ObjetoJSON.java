package com.ThechnoSk.testhome.Modelo;

import java.util.List;

//El Objeto JSON en su atributo results contiene la lista de Peliculas
public class ObjetoJSON {

    //Declaramos la variable que nos interesa recuperar
    private List <Pelicula> results;

    //Getter de la lista
    public List<Pelicula> getListaPelicula() {
        return results;
    }
}
