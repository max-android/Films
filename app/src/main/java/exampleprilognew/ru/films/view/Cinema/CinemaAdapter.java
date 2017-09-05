package exampleprilognew.ru.films.view.Cinema;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.RequestManager;
import java.util.List;
import exampleprilognew.ru.films.R;
import exampleprilognew.ru.films.model.Cinema;



public class CinemaAdapter extends RecyclerView.Adapter<CinemaAdapter.ViewHolder> {

    private List<Cinema.Film> results;

    private final  RequestManager requestManager;

    private final CinemaClickListener listener;


    public CinemaAdapter(List<Cinema.Film> results, RequestManager requestManager, CinemaClickListener listener) {
        this.results = results;
        this.requestManager = requestManager;
        this.listener = listener;
    }

    @Override
    public CinemaAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater= LayoutInflater.from(parent.getContext());

        View view=inflater.inflate(R.layout.cinema_item,parent,false);

        return  new ViewHolder(view,listener);
    }

    @Override
    public void onBindViewHolder(CinemaAdapter.ViewHolder holder, int position) {

        Cinema.Film film=results.get(position);
        holder.bindTo(film);

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView imageView;
        private final TextView title;
        private Cinema.Film film;

        public ViewHolder(View itemView,final CinemaClickListener listener) {
            super(itemView);
            imageView=(ImageView)itemView.findViewById(R.id.imageFilm);
           title=(TextView)itemView.findViewById(R.id.tvTitleMain);

            itemView.setOnClickListener(this::launchFilm);

        }


        private void launchFilm(View view){

            listener.onCinemaClick(film);

        }

        public  void bindTo(Cinema.Film film){

            this.film=film;
            title.setText(film.getTitle());

            requestManager.load("https://image.tmdb.org/t/p/w500/"+film.getPoster_path()).into(imageView);
        }
    }


public interface CinemaClickListener{


    void onCinemaClick(Cinema.Film film);
}

}
