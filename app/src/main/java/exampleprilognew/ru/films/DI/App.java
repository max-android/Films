package exampleprilognew.ru.films.DI;

import android.app.Application;


public class App extends Application {

    private static AppComponent appComponent;

    public static AppComponent getAppComponent() {
        return appComponent;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        appComponent = DaggerAppComponent
                .builder()
                .daggerGlideModule(new DaggerGlideModule(this))
                .daggerCinemaModule(new DaggerCinemaModule())
                .daggerDatabaseModule(new DaggerDatabaseModule(this))
                .build();
    }

}
