package com.zhangli.test.material_design_application.Fragement;

import android.content.Intent;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.parkingwang.okhttp3.LogInterceptor.LogInterceptor;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhangli.test.material_design_application.Adapter.MusicAdapter;
import com.zhangli.test.material_design_application.Bean.MusicBean;
import com.zhangli.test.material_design_application.MusicDetails_Activity;
import com.zhangli.test.material_design_application.News_Details_Activity;
import com.zhangli.test.material_design_application.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;


public class MusicFragment extends Fragment {
    View view;
    private List<MusicBean> musicBeansList = new ArrayList<>();
    private RecyclerView recyclerView;
    private RefreshLayout refreshLayout;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_music, null);
        for(int i=0;i<20;i++){
            sendRequestWithOkHttp("https://api.han8.net/api/rand.music?type=json");
        }

        return view;
    }

    private void initView(final List<MusicBean> musicBeans) {
        recyclerView = (RecyclerView) view.findViewById(R.id.mu_rv);
        GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        MusicAdapter musicAdapter = new MusicAdapter(musicBeans,view.getContext());
        recyclerView.setAdapter(musicAdapter);

        musicAdapter.setOnItemClickListener(new MusicAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(view.getContext(),MusicDetails_Activity.class);
                intent.putExtra("weburl",musicBeans.get(position).getMusicUrl());
                startActivity(intent);
            }
        });

        refreshLayout = (RefreshLayout)view.findViewById(R.id.refreshLayout);
        refreshLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishRefresh(2000);
            }
        });
        refreshLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                refreshLayout.finishLoadMore(2000);
            }
        });
    }

    private  void sendRequestWithOkHttp(final String url){
        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new LogInterceptor())
                .build();
        Request request = new Request.Builder().url(url).build();
        Call call = client.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                String responseData = response.body().string();
                Log.d("MusicFragment", "run: "+responseData);
                showResponse(responseData);
            }
        });

    }
    private  void showResponse(final String responseData){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try{
                    JSONObject json1 = new JSONObject(responseData);
//                    JSONArray jsonArray = json1.getJSONArray("songlist");
//                    for(int i=0;i<jsonArray.length();i++){
                        String singername = "";
                        singername = json1.getString("author");
                        String musicname = json1.getString("name");
                        String album = json1.getString("picurl");
                        String musicUrl = json1.getString("mp3url");
                        MusicBean music = new MusicBean();
                        music.setSingerName(singername);
                        music.setMusicName(musicname);
                        music.setAlbum(album);
                        music.setMusicUrl(musicUrl);
                        musicBeansList.add(music);
                        initView(musicBeansList);
//                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
