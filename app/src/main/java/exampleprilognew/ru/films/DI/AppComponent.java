package exampleprilognew.ru.films.DI;

import javax.inject.Singleton;
import dagger.Component;
import exampleprilognew.ru.films.view.Cinema.CinemaActivity;
import exampleprilognew.ru.films.view.CinemaDetail.CinemaDetailActivity;



@Singleton
@Component(modules = {DaggerCinemaModule.class,
                      DaggerGlideModule.class,
                      DaggerDatabaseModule.class,
})

 public interface AppComponent {

     void injectCinemaActivity(CinemaActivity activity);

     void injectCinemaDetailActivity(CinemaDetailActivity activity);

}
