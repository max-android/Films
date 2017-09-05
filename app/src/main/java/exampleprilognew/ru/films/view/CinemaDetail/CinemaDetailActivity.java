package exampleprilognew.ru.films.view.CinemaDetail;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.RequestManager;
import javax.inject.Inject;
import exampleprilognew.ru.films.R;
import exampleprilognew.ru.films.DI.App;
import exampleprilognew.ru.films.model.Cinema;

public class CinemaDetailActivity extends AppCompatActivity {

    @Inject
    public RequestManager imageRequestManager;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema_detail);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        App.getAppComponent().injectCinemaDetailActivity(this);

        loadDetailDataForFilm();

    }


    private void loadDetailDataForFilm(){

        Intent intentFilm = getIntent();

        Cinema.Film film = (Cinema.Film)intentFilm.getSerializableExtra("FILM");


        ImageView imageView=(ImageView)findViewById(R.id.image);
        TextView title=(TextView)findViewById(R.id.tvTitle);
        TextView release=(TextView)findViewById(R.id.tvRelease);
        TextView description=(TextView)findViewById(R.id.tvDescription);
        TextView rating=(TextView)findViewById(R.id.tvRating);

        title.setText(film.getTitle());
        release.setText(film.getRelease_date());
        description.setText(film.getOverview());
        rating.setText(String.valueOf(film.getVote_average()));

        imageRequestManager.load("https://image.tmdb.org/t/p/w500/"+film.getBackdrop_path()).into(imageView);

    }

}
