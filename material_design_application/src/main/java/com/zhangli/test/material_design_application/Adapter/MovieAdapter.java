package com.zhangli.test.material_design_application.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.zhangli.test.material_design_application.Bean.MoviesBean;
import com.zhangli.test.material_design_application.R;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder>{
    private List<MoviesBean> moviesBeanList;
    private Context context;

    public MovieAdapter(List<MoviesBean> moviesList,Context mcontext){
        moviesBeanList = moviesList;
        context = mcontext;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView mv_name,mv_grade,mv_type,mv_director,mv_casts,mv_date;
        ImageView mv_poster;

        public ViewHolder(View view) {
            super(view);
            mv_name = view.findViewById(R.id.mv_name);
            mv_grade = view.findViewById(R.id.mv_grade);
            mv_type = view.findViewById(R.id.mv_type);
            mv_director = view.findViewById(R.id.mv_director);
            mv_casts = view.findViewById(R.id.mv_casts);
            mv_date = view.findViewById(R.id.mv_date);
            mv_poster  = view.findViewById(R.id.mv_poster);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.movie_items,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        MoviesBean movie = moviesBeanList.get(i);
        Glide.with(context).load(movie.getMovie_poster()).into(viewHolder.mv_poster);
        viewHolder.mv_name.setText(movie.getMovie_name());
        viewHolder.mv_grade.setText(movie.getMovie_grade());
        viewHolder.mv_type.setText(movie.getMovie_type());
        viewHolder.mv_director.setText(movie.getMovie_director());
        viewHolder.mv_casts.setText(movie.getMovie_casts());
        viewHolder.mv_date.setText(movie.getMovie_date());
    }

    @Override
    public int getItemCount() {
        return moviesBeanList.size();
    }
}
