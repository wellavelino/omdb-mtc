package pao.de.queijo.omdbmtc.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import pao.de.queijo.omdbmtc.R;
import pao.de.queijo.omdbmtc.data.model.Movie;
import pao.de.queijo.omdbmtc.ui.activity.MainActivity;

/**
 * @author Lucas Campos
 * @since 1.0.0
 */

public class FavoriteAdapter extends RecyclerView.Adapter<FavoriteAdapter.ViewHolder> {

    private final List<Movie> favorites;
    private final MainActivity.OnSelectItem onSelectItem;

    public FavoriteAdapter(List<Movie> favorites, MainActivity.OnSelectItem onSelectItem) {
        this.favorites = favorites;
        this.onSelectItem = onSelectItem;
    }

    @Override
    public FavoriteAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.adapter_movie, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(FavoriteAdapter.ViewHolder holder, int position) {
        Movie movie = favorites.get(position);

        holder.title.setText(movie.getTitle());
        holder.year.setText(movie.getYear());

        if (favorites.contains(movie)) {
            holder.favorite.setImageResource(R.drawable.ic_favorite);
        } else {
            holder.favorite.setImageResource(R.drawable.ic_favorite_border);
        }

        holder.favorite.setOnClickListener(view -> {
            if (favorites.contains(movie)) {
                favorites.remove(movie);
                holder.favorite.setImageResource(R.drawable.ic_favorite_border);
            } else {
                favorites.add(movie);
                holder.favorite.setImageResource(R.drawable.ic_favorite);
            }
        });

        holder.itemView.setOnClickListener(v -> onSelectItem.onItemSelected(movie));
    }

    @Override
    public int getItemCount() {
        return favorites != null ? favorites.size() : 0;
    }

    static class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.title)
        TextView title;

        @BindView(R.id.year)
        TextView year;

        @BindView(R.id.favorite)
        ImageView favorite;

        ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
