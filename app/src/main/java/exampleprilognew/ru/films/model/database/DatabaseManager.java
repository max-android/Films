package exampleprilognew.ru.films.model.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;
import exampleprilognew.ru.films.model.Cinema;

public class DatabaseManager {

    private SQLiteDatabase sqLiteDatabase;
    private CinemaDBHelper dbHelper;

    public DatabaseManager(Context context) {
        dbHelper=new CinemaDBHelper(context);
        this.sqLiteDatabase = dbHelper.getWritableDatabase();

    }

    public CinemaDBHelper getDbHelper() {
        return dbHelper;
    }

    public Cursor getCursor(){
        Cursor cursor=sqLiteDatabase.query(CinemaDBHelper.FILMS,null,null,null,null,null,null);
    return  cursor;
    }

    public  List<Cinema.Film> getDataFromBD(){

        sqLiteDatabase.beginTransaction();

        Cursor cursor=getCursor();

        List<Cinema.Film> cinemaList=new  ArrayList<Cinema.Film>();

        if(cursor!=null){

            if(cursor.moveToFirst()){

                int indexPosterPath = cursor.getColumnIndex("poster_path");
                int indexTitle = cursor.getColumnIndex("title");
                int indexReleased = cursor.getColumnIndex("released");
                int indexOverview = cursor.getColumnIndex("overview");
                int indexAverage = cursor.getColumnIndex("vote_average");
                int indexBackdropPath = cursor.getColumnIndex("backdrop_path");

                do{
                    cinemaList.add(new Cinema.Film(
                            cursor.getString(indexPosterPath),
                            cursor.getString(indexTitle),
                            cursor.getString(indexReleased),
                            cursor.getString(indexOverview),
                            cursor.getDouble(indexAverage),
                            cursor.getString(indexBackdropPath)
                    ));
                }while(cursor.moveToNext());

            }
        }
        sqLiteDatabase.setTransactionSuccessful();
        sqLiteDatabase.endTransaction();

        return  cinemaList;

    }


   public void writeDataIntoBD(List<Cinema.Film> cinemaList){

                 if (getCursor().getCount()==0) {

                     sqLiteDatabase.beginTransaction();

                     recordData(cinemaList);

                     sqLiteDatabase.setTransactionSuccessful();
                     sqLiteDatabase.endTransaction();

                 }else{

                     if(cinemaList.size()!=0){
                          deleteBD();
                         sqLiteDatabase.beginTransaction();
                     recordData(cinemaList);
                         sqLiteDatabase.setTransactionSuccessful();
                         sqLiteDatabase.endTransaction();
                     }

                 }
   }


   private void recordData(List<Cinema.Film> cinemaList){
       sqLiteDatabase.beginTransaction();
       ContentValues contentValues = new ContentValues();

       for (Cinema.Film film : cinemaList) {
           contentValues.put(CinemaDBHelper.KEY_POSTER_PATH, film.getPoster_path());
           contentValues.put(CinemaDBHelper.KEY_TITLE, film.getTitle());
           contentValues.put(CinemaDBHelper.KEY_RELEASED, film.getRelease_date());
           contentValues.put(CinemaDBHelper.KEY_OVERVIEW, film.getOverview());
           contentValues.put(CinemaDBHelper.KEY_VOTE_AVERAGE, film.getVote_average());
           contentValues.put(CinemaDBHelper.KEY_BACKDROP_PATH, film.getBackdrop_path());

           sqLiteDatabase.insert(CinemaDBHelper.FILMS, null, contentValues);
       }

       sqLiteDatabase.setTransactionSuccessful();
       sqLiteDatabase.endTransaction();

   }


   public void deleteBD(){

       sqLiteDatabase.beginTransaction();
       sqLiteDatabase.delete(CinemaDBHelper.FILMS,null,null);
       sqLiteDatabase.setTransactionSuccessful();
       sqLiteDatabase.endTransaction();

   }

}



