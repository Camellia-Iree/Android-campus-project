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
    // 用HttpUrlConnection实现获取留言列表数据，用Gson解析数据，更新UI（调用adapter.setData()方法）
    // 注意网络请求和UI更新分别应该放在哪个线程中
    private void getData(String studentId){
        // 新线程
        new Thread(new Runnable() {
            @Override
            public void run() {
                // baseGetReposFromRemote
                String urlStr = String.format(BASE_URL + "messages");
                try{
                    URL url = new URL(urlStr);
                    HttpURLConnection connect = (HttpURLConnection) url.openConnection();
                    connect.setConnectTimeout(6000);
                    connect.setRequestMethod("GET");        // 默认
                    // 设置header
                    connect.setRequestProperty("accept", "application/json");
                    if(connect.getResponseCode() == 200){   // success
                        InputStream in = connect.getInputStream();
                        // input是获取输出，即将获得数据输入指定变量
                        BufferedReader reader = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
                        // 从InputStream读取数据
                        // Gson解析
                        result = new Gson().fromJson(reader, new TypeToken<MessageListResponse>(){}.getType());
                        reader.close();
                        in.close();
                    }
                    else{
                        // 错误处理
                    }
                    connect.disconnect();
                }catch (Exception e){
                    e.printStackTrace();
                    // 会报错，子线程需要手动创建Looper？
                    // Toast.makeText(MainActivity.this, "网络异常 " + e.toString(), Toast.LENGTH_SHORT).show();
                }
                // return result 作为baseGetReposFromRemote的结果
                if (result != null){
                    // getMainLooper返回主线程中的Looper，以进行UI更新
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