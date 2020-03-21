package com.example.testhome.Util;

//Constantes de la aplicacion
public class Constants {

    //DATOS DE LA DB y version de la SQLite
    public static final int DB_VERSION = 1;
    public static final String DB_NAME = "DBHost";


    public static final String DB_TABLE_NAME ="pelicula";

    //Columnas de la tabla
    public static final String KEY_ID = "id_pelicula";
    public static final String KEY_TITLE = "title";
    public static final String KEY_VOTE_AVG = "vote_average";
    public static final String KEY_OVERVIEW = "overview";
    public static final String KEY_RELEASE = "release_date";
    public static final String KEY_POSTER_PATH = "poster_path";
    public static final String KEY_BACKDROP_PATH= "backdrop_path";



    //Datos de variable de Extras
    public static final String ID_EXTRA ="ID_Pelicula_Extra";

    //URL base para imagenes
    public static final String URL_PICASO = "https://image.tmdb.org/t/p/w200/";

    //Datos para la API de Peliculas
    public static final String BASE_API_MOVIES = "https://api.themoviedb.org/3/movie/";
    public static final String API_POINT = "top_rated";
    public static final String API_KEY = "22ca2445747ab8ef473c89b05cb451cc";
    public static final String API_LENG = "es";
    public static final String API_VAR_KEY="?api_key=";
    public static final String API_VAR_LENG="&language=";




}
