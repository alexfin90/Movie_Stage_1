package adapters;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.afinocchiaro.popularmovie.DetailsScreenActivity;
import com.example.afinocchiaro.popularmovie.MainActivity;
import com.example.afinocchiaro.popularmovie.R;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import network.NetworkUtils;

/**
 * Created by afinocchiaro on 12/02/17.
 */

public class GridViewAdapter extends RecyclerView.Adapter<GridViewAdapter.GridViewHolder> {


    List<String> ImagesUrl;
    Context context;


    public GridViewAdapter(Context cxt) {
        this.context = cxt;
        this.ImagesUrl = new ArrayList<>();
    }

    @Override
    public GridViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        int layoutIdForListItem = R.layout.movie_grid_item;
        LayoutInflater inflater = LayoutInflater.from(context);
        boolean shouldAttachToParentImmediately = false;
        View view = inflater.inflate(layoutIdForListItem, parent, shouldAttachToParentImmediately);
        return new GridViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final GridViewHolder holder, final int position) {

        //Load Image Url
        URL url = NetworkUtils.buildUriPosterPath(ImagesUrl.get(position), false);
        Picasso.with(context).load(url.toString()).into(holder.imageView);

        holder.imageView.animate().alpha(0).translationY(-200).setDuration(0).setInterpolator(new DecelerateInterpolator()).withEndAction(new Runnable() {
            @Override
            public void run() {
                holder.imageView.animate().translationY(0).alpha(1).setDuration(1000).setInterpolator(new AccelerateInterpolator()).start();
            }
        }).start();

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //// TODO: lancio l'activity detail.
                String title = ( (MainActivity) context ).getListTitles().get(position);
                Intent detailActivity = new Intent(context.getApplicationContext(),DetailsScreenActivity.class);
                detailActivity.putExtra("title",title);
                context.startActivity(detailActivity);
            }
        });

    }

    @Override
    public int getItemCount() {
        if (null == ImagesUrl) return 0;
        return ImagesUrl.size();
    }

    public void setImagesUrl(List<String> list) {
        ImagesUrl = list;
        notifyDataSetChanged();
    }

    public class GridViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;


        public GridViewHolder(View itemView) {
            super(itemView);
            imageView = (ImageView) itemView.findViewById(R.id.iv_movie);

        }



    }



}
