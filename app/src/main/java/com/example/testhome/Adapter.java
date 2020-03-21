package com.example.testhome;

import android.content.Context;
import android.content.Intent;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.testhome.Modelo.Pelicula;
import com.example.testhome.Util.Constants;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static android.widget.ImageView.ScaleType.CENTER_CROP;


//Heredamos de pagerAdapter para tener sus metodos.
public class Adapter extends PagerAdapter {

    //Declaraciobn de componentes
    private List <Pelicula> peliculaList;
    private LayoutInflater layoutInflater;
    private Context context;


    //Declaracion de componentes para utilizar con ButterKnife
    @BindView(R.id.imagenCardView) ImageView imageView;
    @BindView(R.id.cardViewID) CardView cardView;





    //Constructor que solicita una lista en este caso de peliculas y el contexto
    public Adapter(List<Pelicula> peliculaList, Context context) {
        this.peliculaList = peliculaList;
        this.context = context;
    }





    //metodo que crea cada Item del Carrusel, este metodo se ejecuta por cada item de la lista
    @NonNull
    @Override
    public View instantiateItem(@NonNull ViewGroup container, final int position) {

        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item, container,false);

        //inyeccion de vista con ButterKnife
        ButterKnife.bind(this, view);




        imageView.setScaleType(CENTER_CROP);
        imageView.getLayoutParams().width = ViewGroup.LayoutParams.MATCH_PARENT;


        //Carga de imagenes con Picasso
        Picasso.with(context)
               .load(Constants.URL_PICASO+peliculaList.get(position).getPoster_path())
               .into(imageView);



        //Agregamos el cardView al contenedor principal
        container.addView(view);


        //Metodo para abrir nuevo intent de pelicula detalle
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent i = new Intent(context,DetallePelicula.class);
                i.putExtra(Constants.ID_EXTRA,position);
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.getApplicationContext().startActivity(i);

            }
        });

        //regresa la vista con todos los cardview cargados
        return view;

    }





    //destruir los items que salen de la vista
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
       container.removeView((View) object);
    }



    //Obtener el tamaño de la lista
    @Override
    public int getCount() {
        return peliculaList.size();
    }



    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }
}
