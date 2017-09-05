package exampleprilognew.ru.films.model;


import io.reactivex.Single;
import retrofit2.http.GET;

public interface CinemaService {

    @GET("popular?api_key=befc7872520fd736c58948abb2f4a53c")
    public Single<Cinema> listFilms();

}
