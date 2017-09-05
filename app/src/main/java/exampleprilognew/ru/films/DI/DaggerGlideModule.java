package exampleprilognew.ru.films.DI;

import android.content.Context;
import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import javax.inject.Singleton;
import dagger.Module;
import dagger.Provides;

@Module
@Singleton
public class DaggerGlideModule {

    private Context context;

    public DaggerGlideModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    public RequestManager provideGlideRequestManager(){

        return Glide.with(context);
    }

}
