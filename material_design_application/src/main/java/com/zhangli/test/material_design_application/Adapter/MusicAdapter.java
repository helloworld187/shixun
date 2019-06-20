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
import com.zhangli.test.material_design_application.Bean.MusicBean;
import com.zhangli.test.material_design_application.R;

import java.util.List;

public class MusicAdapter extends RecyclerView.Adapter<MusicAdapter.ViewHolder> {
    private List<MusicBean> musicBeancs;
    private Context context;

    public MusicAdapter(List<MusicBean> musicBeancs, Context context) {
        this.musicBeancs = musicBeancs;
        this.context = context;
    }

    public interface OnItemClickListener {
        void onItemClick(View v,int position);
    }

    public OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener){
        onItemClickListener= listener;
    }


    public static class  ViewHolder extends RecyclerView.ViewHolder{
        ImageView album;
        TextView musicname,singer;

        public ViewHolder(View itemView) {
            super(itemView);
            album = itemView.findViewById(R.id.album);
            musicname = itemView.findViewById(R.id.musicname);
            singer = itemView.findViewById(R.id.singer);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.music_item,viewGroup,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        MusicBean music = musicBeancs.get(i);
        Glide.with(context).load(music.getAlbum()).into(viewHolder.album);
        viewHolder.musicname.setText(music.getMusicName());
        viewHolder.singer.setText(music.getSingerName());
        final int position = i;
        if (onItemClickListener != null) {
            viewHolder.album.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onItemClickListener.onItemClick(v, position);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return musicBeancs.size();
    }
}
