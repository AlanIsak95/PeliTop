package com.example.testhome.Interface;

import com.example.testhome.Modelo.Pelicula;

import java.util.List;

public interface IDBHandler {


    public void addItem(Pelicula item);
    public Pelicula getItemById(int id);
    public List<Pelicula> getAll();
    public int getItemCount();

}
