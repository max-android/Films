package exampleprilognew.ru.films.presenter;

import javax.inject.Inject;
import exampleprilognew.ru.films.model.CinemaService;
import exampleprilognew.ru.films.model.database.DatabaseManager;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;


public class CinemaPresenter implements CinemaScreenPresenter.Presenter  {

    @Inject
    public CinemaService cinemaService;

    @Inject
    public DatabaseManager databaseManager;


    @Inject
    public CinemaPresenter(CinemaService cinemaService, DatabaseManager databaseManager) {
        this.cinemaService = cinemaService;
        this.databaseManager = databaseManager;
    }

    private CompositeDisposable subscrition=new CompositeDisposable();

    @Override
    public void attach(CinemaScreenPresenter.View view) {

        subscrition.add(cinemaService.listFilms()
                .doOnSuccess((cinema) ->databaseManager.writeDataIntoBD(cinema.getResults()))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                        .subscribe ( (cinema) -> view.showCinema(cinema.getResults())
                        ,(error) -> view.showErrorLoadingCinema(databaseManager.getDataFromBD())));

    }


    @Override
    public void detach() {

        subscrition.dispose();

    }
}
