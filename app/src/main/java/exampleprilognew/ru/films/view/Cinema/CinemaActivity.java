package exampleprilognew.ru.films.view.Cinema;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import com.bumptech.glide.RequestManager;
import com.facebook.stetho.Stetho;
import java.util.List;
import javax.inject.Inject;
import exampleprilognew.ru.films.R;
import exampleprilognew.ru.films.DI.App;
import exampleprilognew.ru.films.model.Cinema;
import exampleprilognew.ru.films.presenter.CinemaScreenPresenter;
import exampleprilognew.ru.films.view.CinemaDetail.CinemaDetailActivity;

public class CinemaActivity extends AppCompatActivity implements CinemaAdapter.CinemaClickListener, CinemaScreenPresenter.View{

    @Inject
    public CinemaScreenPresenter.Presenter cinemaPresenter;

    @Inject
    public RequestManager requestManager;

    private RecyclerView recyclerViewFilms;
    private CoordinatorLayout coordinatorLayout;
    private ProgressManager progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cinema);


        initComponent();

        Stetho.initializeWithDefaults(this);

        App.getAppComponent().injectCinemaActivity(this);

        cinemaPresenter.attach(this);

    }


    private void initComponent(){

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        coordinatorLayout =(CoordinatorLayout)findViewById(R.id.coordinatFilms);
        recyclerViewFilms=(RecyclerView)findViewById(R.id.recyclerView);
        recyclerViewFilms.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewFilms.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        progress=new ProgressManager(this);
        progress.showDialog();
    }


    private void showFilms(List<Cinema.Film> films){

        progress.dismissDialog();

        recyclerViewFilms.setAdapter(new CinemaAdapter(films,requestManager,CinemaActivity.this));

    }

    @Override
    public void showCinema(List<Cinema.Film> results) {

        showFilms(results);
    }

    @Override
    public void showErrorLoadingCinema(List<Cinema.Film> results) {

        if(results.size()==0) {
            showSnac();
        }

        showFilms(results);

        }


    @Override
    public void onCinemaClick(Cinema.Film film) {

        lounchDetailFilm(film);

    }


    private void lounchDetailFilm(Cinema.Film film){
        Intent intent=new Intent(this,CinemaDetailActivity.class);

        intent.putExtra("FILM",film);

        startActivity(intent);

    }

    private void showSnac(){

        Snackbar snackbar= Snackbar.make(coordinatorLayout,getResources().getString(R.string.connect_error),Snackbar.LENGTH_SHORT);
        snackbar.setDuration(5000);
        View snackbarView =snackbar.getView();
        snackbarView.setBackgroundColor(ContextCompat.getColor(this,R.color.snackbar));
        TextView snackTextView = (TextView)
                snackbarView.findViewById(android.support.design.R.id.snackbar_text);
        snackTextView.setTextColor(ContextCompat.getColor(this,R.color.colorAccent));
        snackTextView.setTextSize(getResources().getDimension(R.dimen.textSizeSnack));
        snackbar.show();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        progress.dismissDialog();

        cinemaPresenter.detach();

    }

}
