package com.zhangli.test.material_design_application.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.zhangli.test.material_design_application.Bean.WallPaperBean;
import com.zhangli.test.material_design_application.R;

import java.util.List;

public class WallPaperAdapter extends RecyclerView.Adapter<WallPaperAdapter.ViewHolder> {
    private List<WallPaperBean> wallPaperBeans;
    private Context context;

    public WallPaperAdapter(List<WallPaperBean> wallPaperBeans, Context context) {
        this.wallPaperBeans = wallPaperBeans;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView image_url;

        public ViewHolder(View view) {
            super(view);
            image_url = view.findViewById(R.id.bizhi);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.wallpaper_item, viewGroup, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        WallPaperBean wallPaper = wallPaperBeans.get(i);
        Glide.with(context).load(wallPaper.getImage_url()).into(viewHolder.image_url);
    }



    @Override
    public int getItemCount() {
        return wallPaperBeans.size();
    }
}
