package com.example.testhome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import android.animation.ArgbEvaluator;
import android.os.Bundle;
import android.widget.Toast;
import com.example.testhome.Interface.JSONPlaceHolderAPI;
import com.example.testhome.Modelo.ObjetoJSON;
import com.example.testhome.Modelo.Pelicula;
import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;




public class MainActivity extends AppCompatActivity {

    protected List <Pelicula> listaPeliculaTOP10;
    protected Adapter adapter;


    @BindView(R.id.viewPager) ViewPager viewPager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        listaPeliculaTOP10 = new ArrayList<>();

        getPost();


    }




    private void getPost(){

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/movie/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        JSONPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JSONPlaceHolderAPI.class);
        Call<ObjetoJSON> jsonCall = jsonPlaceHolderAPI.getObjJSON();



        jsonCall.enqueue(new Callback<ObjetoJSON>() {
            @Override
            public void onResponse(Call<ObjetoJSON> call, Response<ObjetoJSON> response) {
                List <Pelicula> listaPelicula;
                listaPelicula = response.body().getListaPelicula();

                int flag = 0;
                for (Pelicula item: listaPelicula) {

                    flag = flag+1;
                    if (flag <= 10) {

                        listaPeliculaTOP10.add(item);

                    }

                }

                adapter = new Adapter(listaPeliculaTOP10,getApplicationContext());
                viewPager.setAdapter(adapter);
                viewPager.setPadding(130,0,130,0);


              }

            @Override
            public void onFailure(Call<ObjetoJSON> call, Throwable t) {

            }
        });


    }






}
