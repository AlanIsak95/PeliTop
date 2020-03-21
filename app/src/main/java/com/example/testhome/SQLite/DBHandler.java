package com.example.testhome.SQLite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import androidx.annotation.Nullable;

import com.example.testhome.Interface.IDBHandler;
import com.example.testhome.Modelo.Pelicula;
import com.example.testhome.Util.Constants;
import java.util.ArrayList;
import java.util.List;


//Clase para crear la DB y el CRUD (Create and Read)
public class DBHandler extends SQLiteOpenHelper implements IDBHandler {



    private final Context context;


    //usamos la palabra Super para pasar los datos para SQLiteOpenHelper ( el contexto, nombre de la DB y version)
    public DBHandler(@Nullable Context context) {
        super(context, Constants.DB_NAME,null,Constants.DB_VERSION);
        this.context = context;
    }


    //se crea la tabla
    //db.execSQL (QUERY)
    @Override
    public void onCreate(SQLiteDatabase db) {

        String CREATE_BABY_TABLE = "CREATE TABLE " + Constants.DB_TABLE_NAME + "("
                + Constants.KEY_ID + " INTEGER PRIMARY KEY,"
                + Constants.KEY_TITLE + " TEXT,"
                + Constants.KEY_OVERVIEW + " TEXT,"
                + Constants.KEY_VOTE_AVG + " REAL,"
                + Constants.KEY_RELEASE + " TEXT,"
                + Constants.KEY_POSTER_PATH + " TEXT,"
                + Constants.KEY_BACKDROP_PATH + " TEXT)";

        db.execSQL(CREATE_BABY_TABLE);

    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

        db.execSQL(" Drop table if exists " + Constants.DB_TABLE_NAME);
        onCreate(db);

    }




    //CRUD (Create,Read)

    //ADD
    @Override
    public void addItem(Pelicula item){

        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues peliculaValues= new ContentValues();



        peliculaValues.put(Constants.KEY_ID,item.getID_PRODUCTO());
        peliculaValues.put(Constants.KEY_TITLE,item.getTitle());
        peliculaValues.put(Constants.KEY_VOTE_AVG,item.getVote_average());
        peliculaValues.put(Constants.KEY_OVERVIEW,item.getOverview());
        peliculaValues.put(Constants.KEY_RELEASE,item.getRelease_date());
        peliculaValues.put(Constants.KEY_POSTER_PATH, item.getPoster_path());
        peliculaValues.put(Constants.KEY_BACKDROP_PATH, item.getBackdrop_path());




        db.insert(Constants.DB_TABLE_NAME,null,peliculaValues);

        db.close();

        Log.d("InsertDB", "addItem: se añadio el item");



    }

    //regresa la Pelicula pasandole el ID
    @Override
    public Pelicula getItemById(int id){

        id=id+1;
        SQLiteDatabase db = this.getReadableDatabase();
        Pelicula peliculaItem = new Pelicula();

        Cursor cursor = db.query(Constants.DB_TABLE_NAME,//name Table
                new String[]{Constants.KEY_ID,
                             Constants.KEY_TITLE,
                             Constants.KEY_OVERVIEW,
                             Constants.KEY_VOTE_AVG,
                             Constants.KEY_RELEASE,
                             Constants.KEY_POSTER_PATH,
                             Constants.KEY_BACKDROP_PATH},//columnas

                Constants.KEY_ID + "=?",
                new String [] {String.valueOf(id)}//where id =? id
                ,null,null,null,null);


        //añadir info al item pelicula
        if (cursor != null){
            cursor.moveToNext();


            peliculaItem.setID_PRODUCTO(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
            peliculaItem.setTitle(cursor.getString(cursor.getColumnIndex(Constants.KEY_TITLE)));
            peliculaItem.setOverview(cursor.getString(cursor.getColumnIndex(Constants.KEY_OVERVIEW)));
            peliculaItem.setVote_average(Float.parseFloat(cursor.getString(cursor.getColumnIndex(Constants.KEY_VOTE_AVG))));
            peliculaItem.setRelease_date(cursor.getString(cursor.getColumnIndex(Constants.KEY_RELEASE)));
            peliculaItem.setPoster_path(cursor.getString(cursor.getColumnIndex(Constants.KEY_POSTER_PATH)));
            peliculaItem.setBackdrop_path(cursor.getString(cursor.getColumnIndex(Constants.KEY_BACKDROP_PATH)));

        }

        db.close();

        return peliculaItem ;

    }

    //Regresa la lista de Peliculas
    @Override
    public List<Pelicula> getAll(){

        SQLiteDatabase db = this.getReadableDatabase();

        List <Pelicula> peliculaList = new ArrayList<>();


        Cursor cursor = db.query(Constants.DB_TABLE_NAME,//name Table
                new String[]{Constants.KEY_ID,
                        Constants.KEY_TITLE,
                        Constants.KEY_OVERVIEW,
                        Constants.KEY_VOTE_AVG,
                        Constants.KEY_RELEASE,
                        Constants.KEY_POSTER_PATH,
                        Constants.KEY_BACKDROP_PATH},//columnas

                null,null,null,null,Constants.KEY_ID+" ASC ");



        if (cursor.moveToFirst()){

            do {
                Pelicula peliculaItem = new Pelicula();

                peliculaItem.setID_PRODUCTO(Integer.parseInt(cursor.getString(cursor.getColumnIndex(Constants.KEY_ID))));
                peliculaItem.setTitle(cursor.getString(cursor.getColumnIndex(Constants.KEY_TITLE)));
                peliculaItem.setOverview(cursor.getString(cursor.getColumnIndex(Constants.KEY_OVERVIEW)));
                peliculaItem.setVote_average(Float.parseFloat(cursor.getString(cursor.getColumnIndex(Constants.KEY_VOTE_AVG))));
                peliculaItem.setRelease_date(cursor.getString(cursor.getColumnIndex(Constants.KEY_RELEASE)));
                peliculaItem.setPoster_path(cursor.getString(cursor.getColumnIndex(Constants.KEY_POSTER_PATH)));
                peliculaItem.setBackdrop_path(cursor.getString(cursor.getColumnIndex(Constants.KEY_BACKDROP_PATH)));


                peliculaList.add(peliculaItem);

            }while (cursor.moveToNext());


        }

        return peliculaList;

    }

    //regresa la cantidad de resultados de la DB
    @Override
    public int getItemCount(){

        String conteo ="select * from "+ Constants.DB_TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.rawQuery(conteo,null);


        return cursor.getCount();


    }



}
