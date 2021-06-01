package study.online.fragment.Study;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import study.online.Activity.Study_Main;
import study.online.Adapter.Study_list_Adapter;
import study.online.R;

public class Study extends Fragment {
    private List<String> imageUrlData;
    private List<String> contentData;
    Banner myBanner;
    private HttpURLConnection connection;
    private List<Study_video_item> list1 = new ArrayList<>();
    private List<Study_video_item> list2 = new ArrayList<>();
    private String study_url;
    private View view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.study_fragment,container,false);
        study_url = getString(R.string.study_url);
        initList();
        return view;
    }


    private void initRecyclerView(View view){
//        RecyclerView recyclerView1 = view.findViewById(R.id.study_1_list);
//        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
//        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
//        recyclerView1.setLayoutManager(layoutManager);
//        Study_list_Adapter adapter1 = new Study_list_Adapter(list1,R.layout.study_video_item,getActivity());
//        recyclerView1.setAdapter(adapter1);

        RecyclerView recyclerView2 = view.findViewById(R.id.study_2_list);
        StaggeredGridLayoutManager layoutManager2 = new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
        recyclerView2.setLayoutManager(layoutManager2);
        Study_list_Adapter adapter2 = new Study_list_Adapter(list2,R.layout.study_video_item_2,getActivity());
        recyclerView2.setAdapter(adapter2);

    }

    private void initList() {
        imageUrlData = new ArrayList<>();
        contentData = new ArrayList<>();
        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        // 开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL(getString(R.string.study_url));
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    // 下面对获取到的输入流进行读取
                    reader = new BufferedReader(new InputStreamReader(in));
                    String line;
                    while ((line = reader.readLine()) != null) {
                        if (line.startsWith("<a href=\"")){
                            String img_url = line.substring(line.indexOf("\">")+2,line.lastIndexOf("</a>"));
                            Log.d("Study", "img_url = "+img_url);
                            String name = img_url.substring(0,img_url.length()-1);
                            Log.d("Study", "name = "+name);
                            imageUrlData.add(study_url+img_url+"a.png");
                            contentData.add(name);
                            list2.add(new Study_video_item(study_url+img_url+"a.png",name,study_url+img_url));
                            list1.add(new Study_video_item(study_url+img_url+"a.png",name,study_url+img_url));
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        getActivity().runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                initRecyclerView(view);
                                initBanner();
                            }
                        });
                    }
                    if (connection != null) {
                        connection.disconnect();
                    }
                }
            }
        }).start();
    }

    private void initBanner(){
        myBanner = view.findViewById(R.id.banner);
        myBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE_INSIDE);
        myBanner.setImageLoader(new MyLoader());
        myBanner.setImages(imageUrlData);
        myBanner.setBannerTitles(contentData);
        myBanner.setBannerAnimation(Transformer.Default);
        myBanner.setDelayTime(2000);
        myBanner.isAutoPlay(true);
        myBanner.setIndicatorGravity(BannerConfig.CENTER);
        myBanner.start();
        myBanner.setOnBannerListener(new OnBannerListener() {
            @Override
            public void OnBannerClick(int position) {
                Study_video_item item = list1.get(position);
                Intent intent = new Intent(getActivity(), Study_Main.class);
                intent.putExtra("url",item.getUrl());
                intent.putExtra("name",item.getText());
                getActivity().startActivity(intent);
            }
        });
    }
    private class MyLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView){
            Glide.with(getActivity()).load(path).into(imageView);
        }
    }
}
