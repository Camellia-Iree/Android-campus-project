package com.example.test2;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.test2.R;

public class Inews extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inews);

        Button btn = findViewById(R.id.btn);
        TextView news = findViewById(R.id.news);

        int extra = getIntent().getIntExtra("news", -1);
        if(extra != -1){
            news.setText("这是第"+extra+"条新闻的内容");
        }

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}
