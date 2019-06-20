package com.zhangli.test.material_design_application.Fragement;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.zhangli.test.material_design_application.Adapter.MovieAdapter;
import com.zhangli.test.material_design_application.Bean.MoviesBean;
import com.zhangli.test.material_design_application.MovieDetails_Activity;
import com.zhangli.test.material_design_application.R;
import com.zhangli.test.material_design_application.ShareActivity;
import com.zhangli.test.material_design_application.Utils.GlideImageLoader;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MovieFragment extends Fragment {
    private View view;
    private View movie_items;
    private List<MoviesBean> moviesBeanList = new ArrayList<>();
    private RecyclerView recyclerView;
    private ImageView mv_poster;

    private Banner banner;
    private List<String> list_title;
    private List<Object> list  = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_movie,null);
        sendRequestWithOkHttp("http://api.douban.com/v2/movie/top250?apikey=0df993c66c0c636e29ecbb5344252a4a");
        return view;

    }
    public void initView(final List<MoviesBean> moviesBeans){
        recyclerView = view.findViewById(R.id.mv_rv);
        LinearLayoutManager layoutManager = new LinearLayoutManager(view.getContext());
        recyclerView.setLayoutManager(layoutManager);
        MovieAdapter adapter = new MovieAdapter(moviesBeans,view.getContext());

        adapter.setOnItemClickListener(new MovieAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View v, int position) {
                Intent intent = new Intent(view.getContext(), MovieDetails_Activity.class);
                intent.putExtra("weburl",moviesBeans.get(position).getMovie_details());
                startActivity(intent);
            }
        });
        recyclerView.setAdapter(adapter);
        }



    private void sendRequestWithOkHttp(final String url){
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    OkHttpClient client = new OkHttpClient();
                    Request request = new Request.Builder().url(url).build();
                    Response response = client.newCall(request).execute();
                    String responseData = response.body().string();
                    Log.d("MovieFragment", "run: "+responseData);
                    showResponse(responseData);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

    }

    private void showResponse(final String responseData){
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try{
                    JSONObject json1 = new JSONObject(responseData);
                    JSONArray jsonArray = json1.getJSONArray("subjects");
                    for(int i=0;i<jsonArray.length();i++){
                        String mv_details = jsonArray.getJSONObject(i).getString("alt");
                        String mv_name = jsonArray.getJSONObject(i).getString("title");
                        String mv_garde = jsonArray.getJSONObject(i).getJSONObject("rating").getString("average");
                        String mv_type = "";
                        JSONArray type = jsonArray.getJSONObject(i).getJSONArray("genres");
                        for (int j=0; j<type.length(); j++) {
                            mv_type += type.getString(j) + "\u3000";
                        }
                        String mv_director = "";
                        JSONArray director = jsonArray.getJSONObject(i).getJSONArray("directors");
                        for (int j=0; j<director.length(); j++) {
                            mv_director += director.getJSONObject(j).getString("name") + "\u3000";
                        }
                        String mv_casts = "";
                        JSONArray casts = jsonArray.getJSONObject(i).getJSONArray("casts");
                        for(int j = 0;j<casts.length();j++){
                            mv_casts += casts.getJSONObject(j).getString("name") + "\u3000";
                        }
                        String mv_date = jsonArray.getJSONObject(i).getString("year");
                        String mv_poster = jsonArray.getJSONObject(i).getJSONObject("images").getString("small");
//                        final String weburl = jsonArray.getJSONObject(i).getString("weburl");

                        moviesBeanList.add(new MoviesBean(mv_name,mv_garde,mv_type,mv_director,mv_casts,mv_date,mv_poster,mv_details));
                        Log.d("NewsFragment", "run: "+i+moviesBeanList.toString());
                    }
                    initView(moviesBeanList);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });

    }

}
