package com.example.testhome;

import android.os.Bundle;

import com.example.testhome.Modelo.Pelicula;
import com.example.testhome.SQLite.DBHandler;
import com.example.testhome.Util.Constants;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class DetallePelicula extends AppCompatActivity {

    //Declaramos componentes para utilizar con ButterKnife
    @BindView(R.id.imagen_pelicula_detalle) ImageView contenedor_pelicula_detalle;
    @BindView(R.id.titulo_pelicula_detalle) TextView titulo_pelicula_detalle;
    @BindView(R.id.rating_bar_pelicula_detalle) RatingBar rating_bar_pelicula_detalle;
    @BindView(R.id.valoracion_pelicula_detalle) TextView valoracion_pelicula_detalle;
    @BindView(R.id.fecha_lanzamiento_pelicula_detalle) TextView fecha_pelicula_detalle;
    @BindView(R.id.resumen_pelicula_detalle) TextView resumen_pelicula_detalle;

    //Metodo onClick en el componente btn_ok_pelicula_detalle --> Cierra la vista actual
    @OnClick(R.id.btn_ok_pelicula_detalle)
    public void clicked() {
        finish();
    }


    //DB
    private DBHandler dbMaster;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_pelicula);

        //Inyeccion de vista al controlador
        ButterKnife.bind(this);

        //Creacion de Base de datos
        dbMaster = new DBHandler(this);

        //obtener el ID de al pelicula
        int id_extra = getIntent().getIntExtra(Constants.ID_EXTRA,0);

        //obtener la pelicula por ID
        Pelicula pelicula = dbMaster.getItemById(id_extra);

        //Cargamos los datos de al pelicula en sus componentes
        titulo_pelicula_detalle.setText(pelicula.getTitle());
        valoracion_pelicula_detalle.append(" : "+pelicula.getVote_average());
        rating_bar_pelicula_detalle.setRating(pelicula.getVote_average());
        fecha_pelicula_detalle.append(" : "+pelicula.getRelease_date());
        resumen_pelicula_detalle.setText(pelicula.getOverview());

        //Cargamos la imagen dentro del contenedor.
        Picasso.with(getApplicationContext()).load(Constants.URL_PICASO+pelicula.getBackdrop_path()).into(contenedor_pelicula_detalle);



    }
}
