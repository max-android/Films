package exampleprilognew.ru.films.presenter;

import java.util.List;
import exampleprilognew.ru.films.model.Cinema;


public interface CinemaScreenPresenter {

     interface View{

    void showCinema(List<Cinema.Film> results);
    void showErrorLoadingCinema(List<Cinema.Film> results);

}

     interface Presenter{
        //экран создан
        void attach(View view);
        //экран уничтожается
        void detach();
    }
}
