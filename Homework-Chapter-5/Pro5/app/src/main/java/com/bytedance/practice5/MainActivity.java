package com.bytedance.practice5;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import com.bytedance.practice5.model.MessageListResponse;
import com.bytedance.practice5.socket.SocketActivity;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;

import com.bytedance.practice5.model.Message;
import com.bytedance.practice5.model.MessageListResponse;

import static com.bytedance.practice5.Constants.BASE_URL;
import static com.bytedance.practice5.Constants.token;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "chapter5";
    private FeedAdapter adapter = new FeedAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Fresco.initialize(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RecyclerView recyclerView = findViewById(R.id.rv_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
        findViewById(R.id.btn_upload).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,UploadActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.btn_mine).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(Constants.STUDENT_ID);
            }
        });

        findViewById(R.id.btn_all).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getData(null);
            }
        });
        findViewById(R.id.btn_socket).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, SocketActivity.class);
                startActivity(intent);
            }
        });
    }

    private MessageListResponse result = null;

    //TODO 2
    // ???HttpUrlConnection????????????????????????????????????Gson?????????????????????UI?????????adapter.setData()?????????
    // ?????????????????????UI???????????????????????????????????????
    private void getData(String studentId){
        // ?????????
        new Thread(new Runnable() {
            @Override
            public void run() {
                // baseGetReposFromRemote
                String urlStr = String.format(BASE_URL + "messages");
                try{
                    URL url = new URL(urlStr);
                    HttpURLConnection connect = (HttpURLConnection) url.openConnection();
                    connect.setConnectTimeout(6000);
                    connect.setRequestMethod("GET");        // ??????
                    // ??????header
                    connect.setRequestProperty("accept", "application/json");
                    if(connect.getResponseCode() == 200){   // success
                        InputStream in = connect.getInputStream();
                        // input??????????????????????????????????????????????????????
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
                        // ???InputStream????????????
                        // Gson??????
                        result = new Gson().fromJson(reader, new TypeToken<MessageListResponse>(){}.getType());
                        reader.close();
                        in.close();
                    }
                    else{
                        // ????????????
                    }
                    connect.disconnect();
                }catch (Exception e){
                    e.printStackTrace();
                    // ???????????????????????????????????????Looper???
                    // Toast.makeText(MainActivity.this, "???????????? " + e.toString(), Toast.LENGTH_SHORT).show();
                }
                // return result ??????baseGetReposFromRemote?????????
                if (result != null){
                    // getMainLooper?????????????????????Looper????????????UI??????
                    new Handler(getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            if (studentId == null) adapter.setData(result.feeds);
                            else {
                                List<Message> tem = new LinkedList<>();
                                int i;
                                for (i = 0; i < result.feeds.size(); i++) {
                                    if (result.feeds.get(i).getStudentId().equals(studentId))
                                        tem.add(result.feeds.get(i));
                                }
                                adapter.setData(tem);
                            }
                        }
                    });
                }
            }
        }).start();
    }

}