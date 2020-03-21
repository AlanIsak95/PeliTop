package com.example.testhome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


import android.os.Bundle;
import android.widget.Toast;
import com.example.testhome.Interface.JSONPlaceHolderAPI;
import com.example.testhome.Modelo.ObjetoJSON;
import com.example.testhome.Modelo.Pelicula;
import com.example.testhome.SQLite.DBHandler;
import com.example.testhome.Util.Constants;

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

    //Declaracion de componentes
    protected List <Pelicula> listaPeliculaTOP10;
    protected Adapter adapter;
    protected DBHandler db = new DBHandler(this);
    private   DBHandler dbMaster;

    //Declaracion de componentes para ButterKnife
    @BindView(R.id.viewPager) ViewPager viewPager;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //Inyeccion de vista mediante ButterKnife
        ButterKnife.bind(this);

        listaPeliculaTOP10 = new ArrayList<>();

        //instancia de la BD
        dbMaster = new DBHandler(this);


        ByPassActivity();

    }




    //Revisa si existen datos en SQLite y si es asi carga el carrusel con la lista existente, de lo contrario manda a llamarlos con Retrofit
    private void ByPassActivity() {

        if (dbMaster.getItemCount() > 0){

            listaPeliculaTOP10 = dbMaster.getAll();
            cargarImagenesCarrusel(listaPeliculaTOP10);
            Toast.makeText(this, "Datos desde SQLite", Toast.LENGTH_SHORT).show();

        }else {
            Toast.makeText(this, "Datos desde Retrofit", Toast.LENGTH_SHORT).show();
            getDataByRetrofit();
        }



    }

    //Recupera una lista de peliculas mediante Retrofit
    private void getDataByRetrofit(){

        //Obj retrofit donde declaramos la URL base y a donde apuntaremos esta en la Interface
        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl(Constants.BASE_API_MOVIES)
                .addConverterFactory(GsonConverterFactory.create())//Gson para Parsear los datos, para poder ser leidos facilemnte
                .build();

        //utilizamos la interfaz para obtener el Objeto Call de tipo ObjetoJson (que tiene la lista dentro)
        JSONPlaceHolderAPI jsonPlaceHolderAPI = retrofit.create(JSONPlaceHolderAPI.class);
        Call<ObjetoJSON> jsonCall = jsonPlaceHolderAPI.getObjJSON();


        //Hace un recorrido en todos los objetos que sean del tipo ObjetoJson que contenga la respuesta de retrofit y
        //manda a llamar el metodo cargarCarrusel una vez que ya tenga el list de peliculas completo
        jsonCall.enqueue(new Callback<ObjetoJSON>() {

            @Override
            public void onResponse(Call<ObjetoJSON> call, Response<ObjetoJSON> response) {

                List <Pelicula> listaPelicula;
                listaPelicula = response.body().getListaPelicula();

                //flag para ID de las peliculas
                int flag = 0;
                for (Pelicula item: listaPelicula) {

                    flag = flag+1;
                    if (flag <= 10) {

                        listaPeliculaTOP10.add(item);
                        item.setID_PRODUCTO(flag);
                        db.addItem(item);

                    }

                }

                //Cuando termine el for y tengamos la lista llena, cargamos el carrusel
                cargarImagenesCarrusel(listaPeliculaTOP10);

              }

            @Override
            public void onFailure(Call<ObjetoJSON> call, Throwable t) {

            }
        });


    }

    //Carga las imagenes en el carrusel
    private void cargarImagenesCarrusel(List <Pelicula> listtop10) {

        //creamos un adaptador para el viewPager, pasandole la lista de peliculas y el contexto actual
        adapter = new Adapter(listtop10,getApplicationContext());

        //colocamos un adaptador para el contenedor del carrusel
        viewPager.setAdapter(adapter);

        //Creamos un padding
        viewPager.setPadding(130,0,130,0);


    }


}
