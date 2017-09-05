package exampleprilognew.ru.films.DI;



import android.support.annotation.NonNull;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import exampleprilognew.ru.films.model.CinemaService;
import exampleprilognew.ru.films.model.database.DatabaseManager;
import exampleprilognew.ru.films.presenter.CinemaPresenter;
import exampleprilognew.ru.films.presenter.CinemaScreenPresenter;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


@Module
@Singleton
public class DaggerCinemaModule {


    @Provides
    @Singleton
    public Retrofit provideRetrofit(){


        return new Retrofit.Builder()
                .baseUrl("https://api.themoviedb.org/3/movie/")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    @Provides
    @Singleton
    public CinemaService provideCinemaService(@NonNull Retrofit retrofit){

        return retrofit.create(CinemaService.class);

    }


    @Provides
    @Singleton
    public CinemaScreenPresenter.Presenter provideCinemaPresenter(@NonNull CinemaService cinemaService, @NonNull DatabaseManager databaseManager){

        return  new CinemaPresenter(cinemaService,databaseManager);

    }

}
