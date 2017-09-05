package exampleprilognew.ru.films.model.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CinemaDBHelper extends SQLiteOpenHelper {


    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "DB";
    public static final String FILMS ="films";
    private static final String KEY_ID="_ID";
    protected static final String KEY_POSTER_PATH="poster_path";
    protected static final String KEY_TITLE ="title";
    protected static final String KEY_RELEASED ="released";
    protected static final String KEY_OVERVIEW ="overview";
    protected static final String KEY_VOTE_AVERAGE ="vote_average";
    protected static final String KEY_BACKDROP_PATH ="backdrop_path";


    public CinemaDBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table "+ FILMS +
                "("+
                KEY_ID + " integer primary key,"+
                KEY_POSTER_PATH +" text,"+
                KEY_TITLE +" text,"+
                KEY_RELEASED +" text,"+
                KEY_OVERVIEW +" text,"+
                KEY_VOTE_AVERAGE +" real,"+
                KEY_BACKDROP_PATH +" text"+
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+ FILMS);

        onCreate(db);
    }
}
