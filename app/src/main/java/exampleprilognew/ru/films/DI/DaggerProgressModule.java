package exampleprilognew.ru.films.DI;

import android.app.ProgressDialog;
import android.content.Context;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
@Singleton
public class DaggerProgressModule {


    private Context context;

        public DaggerProgressModule(Context context) {
            this.context = context;
        }

        @Provides
        @Singleton
        public ProgressDialog provideProgress(){

        return new ProgressDialog(context);
    }

    }





