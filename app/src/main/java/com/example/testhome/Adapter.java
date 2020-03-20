package com.example.testhome;

import android.content.Context;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.testhome.Modelo.Pelicula;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Adapter extends PagerAdapter {

    private List <Pelicula> peliculaList;
    private LayoutInflater layoutInflater;
    private Context context;


    //Butter Knife
    @BindView(R.id.imagenCardView) ImageView imageView;
    @BindView(R.id.titleCardView) TextView textView;
    @BindView(R.id.cardViewID) CardView cardView;




    //Constructor
    public Adapter(List<Pelicula> peliculaList, Context context) {
        this.peliculaList = peliculaList;
        this.context = context;
    }




    //metodo que crea cada Item del Carrusel
    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {

        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item, container,false);
        //inyeccion de vista con ButterKnife
        ButterKnife.bind(this, view);

        //Carga de imagenes con Picasso
        Picasso.with(context)
                .load("https://image.tmdb.org/t/p/w200/"+peliculaList.get(position).getPoster_path()).
                into(imageView);


        //titulo de la pelicula
        textView.setText(peliculaList.get(position).getTitle());


        //OnClick
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context, "Calificacion Media: "+peliculaList.get(position).getVote_average(), Toast.LENGTH_SHORT).show();
            }
        });

        //Agregamos el cardView al contenedor
        container.addView(view);


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
