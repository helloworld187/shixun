package com.zhangli.test.material_design_application.Fragement;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhangli.test.material_design_application.Adapter.MusicAdapter;
import com.zhangli.test.material_design_application.Bean.MusicBean;
import com.zhangli.test.material_design_application.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class PeopleFragment extends Fragment {
    View view;
    private List<MusicBean> musicBeansList = new ArrayList<>();
    private RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_people, null);
        sendRequestWithOkHttp("https://c.y.qq.com/v8/fcg-bin/fcg_v8_toplist_cp.fcg?g_tk=5381&uin=0&format=json&inCharset=utf-8&outCharset=utf-8¬ice=0&platform=h5&needNewCode=1&tpl=3&page=detail&type=top&topid=36&_=1520777874472");
        return view;
    }

    private void initView(List<MusicBean> musicBeans) {
        recyclerView = (RecyclerView) view.findViewById(R.id.mu_rv);
        GridLayoutManager layoutManager = new GridLayoutManager(view.getContext(), 2);
        recyclerView.setLayoutManager(layoutManager);
        MusicAdapter musicAdapter = new MusicAdapter(musicBeans,view.getContext());
        recyclerView.setAdapter(musicAdapter);
    }

    private  void sendRequestWithOkHttp(final  String url){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url(url).build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    Log.d("StarFragment", "run: "+responseData);
                    showResponse(responseData);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();

    }
    private  void showResponse(final String responseData){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try{
                    JSONObject json1 = new JSONObject(responseData);
                    JSONArray jsonArray = json1.getJSONArray("songlist");
                    for(int i=0;i<jsonArray.length();i++){
                        String singername = "";
                        JSONArray singerArray = jsonArray.getJSONObject(i).getJSONObject("data").getJSONArray("singer");
                        for (int j=0; j<singerArray.length(); j++) {
                          singername  += singerArray.getJSONObject(j).getString("name") + "\u3000";
                        }
                        String musicname = jsonArray.getJSONObject(i).getJSONObject("data").getString("albumname");
                        String album = jsonArray.getJSONObject(i).getJSONObject("data").getString("albumid");
                        int albumid = Integer.valueOf(album);
                        String album_pic="http://imgcache.qq.com/music/photo/album_300/"+albumid%100+"/300_albumpic_"+albumid+"_0.jpg";
                        MusicBean music = new MusicBean();
                        music.setSingerName(singername);
                        music.setMusicName(musicname);
                        music.setAlbum(album_pic);
                        musicBeansList.add(music);
                        initView(musicBeansList);
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
    }
}
