package exampleprilognew.ru.films.DI;

import android.content.Context;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;
import exampleprilognew.ru.films.model.database.DatabaseManager;


@Module
@Singleton
public class DaggerDatabaseModule {

    private Context context;

    public DaggerDatabaseModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public DatabaseManager provideDatabaseManager(){

        return new DatabaseManager(context);
    }
}
