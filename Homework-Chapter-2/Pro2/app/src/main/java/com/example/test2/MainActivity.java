package com.example.test2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn_pra = findViewById(R.id.btn_pra);
        Button btn_ret = findViewById(R.id.btn_ret);
        Button btn_bai = findViewById(R.id.btn_bai);
        Button btn_cal = findViewById(R.id.btn_cal);
        Button btn_bit = findViewById(R.id.btn_bit);

        btn_pra.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Practice.class);
                startActivity(intent);
            }
        });

        btn_ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "我退出啦！", Toast.LENGTH_SHORT).show();
                finish();
            }
        });

        btn_bai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW); // 根据类型打开相应内容
                intent.setData(Uri.parse("http://www.baidu.com"));
                startActivity(intent);
            }
        });

        btn_cal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("tel:"));
                startActivity(intent);
            }
        });

        btn_bit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, List.class);
                startActivity(intent);
            }
        });
    }



}
