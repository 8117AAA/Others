package study.online.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import study.online.Adapter.Study_main_list_Adapter;
import study.online.R;

public class Study_Main extends AppCompatActivity {
    private String work_url;
    private String atxt;
    private List<Study_Main_item> list = new ArrayList<>();
    private RecyclerView recyclerView;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.study_main);
        Intent intent = getIntent();
        TextView textView = findViewById(R.id.study_main_text);
        textView.setText(intent.getStringExtra("name"));
        ImageView imageView = findViewById(R.id.study_main_img);
        work_url = intent.getStringExtra("url");
        Glide.with(this).load(work_url+"a.png").into(imageView);
        recyclerView = findViewById(R.id.study_main_list);
        initList();
        initText();
    }
    private void initList() {
        // 开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL(work_url);
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
                            if (line.indexOf("a.txt")>0 || line.indexOf("a.png")>0){
                                continue;
                            }else {
                                String temp = line.substring(line.indexOf("\">")+2,line.lastIndexOf("</a>"));
                                list.add(new Study_Main_item(temp.substring(0,temp.length()-4),work_url+temp));
                            }
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
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                LinearLayoutManager layoutManager = new LinearLayoutManager(Study_Main.this);
                                layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
                                recyclerView.setLayoutManager(layoutManager);
                                Study_main_list_Adapter adapter = new Study_main_list_Adapter(list,Study_Main.this);
                                recyclerView.setAdapter(adapter);
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
    private void initText() {
        // 开启线程来发起网络请求
        new Thread(new Runnable() {
            @Override
            public void run() {
                HttpURLConnection connection = null;
                BufferedReader reader = null;
                try {
                    URL url = new URL(work_url+"a.txt");
                    connection = (HttpURLConnection) url.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setConnectTimeout(8000);
                    connection.setReadTimeout(8000);
                    InputStream in = connection.getInputStream();
                    // 下面对获取到的输入流进行读取
                    reader = new BufferedReader(new InputStreamReader(in));
                    StringBuilder response = new StringBuilder();
                    String line;
                    while ((line = reader.readLine()) != null) {
                        response.append(line);
                        response.append("\n");
                    }
                    atxt = response.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    if (reader != null) {
                        try {
                            reader.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                TextView textView = findViewById(R.id.atxt);
                                textView.setText(atxt);
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
}
