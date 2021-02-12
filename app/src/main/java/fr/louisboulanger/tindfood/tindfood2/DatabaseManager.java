package fr.louisboulanger.tindfood.tindfood2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;


public class DatabaseManager extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Recettes.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseManager( Context context ){
        super( context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Création de la BDD à l'installation sur le téléphone
        String strSql = "create table recette_ingredient ("
                + " id integer primary key autoincrement,"
                + " namerecette text not null,"
                + " ingredient text not null"
                + ")";

        db.execSQL( strSql );
        Log.i( "DATABASE", "onCreate Invoked" );

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Quand je met à jour ma base de données (passage de V1 de l'appli à V2 - pour ça je dois changer l.11 DATABASE_VERSION)
        //// Documentation : https://www.sqlite.org/docs.html
        //// Possibilité de réinvoquer le onCreate avec : this.onCreate( db );
        String strSql = "drop table recette_ingredient";
        db.execSQL( strSql );
        this.onCreate( db );
        Log.i( "DATABASE", "onUpgrade invoked");
    }

    public void insertingredient( String namerecette, String ingredient ){
       namerecette = namerecette.replace( "'", "''");
        String strSql = "insert into recette_ingredient (namerecette, ingredient) values ('"
                + namerecette + "', '" + ingredient + "')";

        this.getWritableDatabase().execSQL( strSql );

        Log.i( "DATABASE", "Insert invoked");

    }

    public List<Data> read(){
        List<Data> ingredients = new ArrayList<>();

        String strSql = "select * from recette_ingredient";
        @SuppressLint("Recycle") Cursor cursor = this.getReadableDatabase().rawQuery( strSql, null );
        //Cursor cursor = this.getReadableDatabase().query("recette_ingredient", new String[] {"id","namerecette", "ingredient"}, null, null, null, null, null);
        cursor.moveToFirst();
        while (! cursor.isAfterLast() ){
            Data ingredient = new Data( cursor.getInt( 0 ), cursor.getString( 1 ), cursor.getString( 2 ));
            ingredients.add (ingredient);
            cursor.moveToNext();
        }

        return ingredients;
    }
}
