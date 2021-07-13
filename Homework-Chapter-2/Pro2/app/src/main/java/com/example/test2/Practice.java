package com.example.test2;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

public class Practice extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);
        Log.i("Practice", "onCreate");

        ImageButton btn_ret = findViewById(R.id.ret);
        ImageButton btn_mic = findViewById(R.id.mic);
        Button btn_pre = findViewById(R.id.pre);

        btn_ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_mic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Practice.this,"读万卷书 行万里路……",Toast.LENGTH_SHORT).show();
            }
        });

        btn_pre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(Practice.this,"继续加油！",Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://www.dushu.com/"));
                startActivity(intent);
            }
        });

    }

    @Override
    protected void onStart(){
        super.onStart();
        Log.i("Practice", "onStart");
    }

    @Override
    protected void onResume(){
        super.onResume();
        Log.i("Practice", "onResume");
    }

    @Override
    protected void onPause(){
        super.onPause();
        Log.i("Practice", "onPause");
    }

    @Override
    protected void onStop(){
        super.onStop();
        Log.i("Practice", "onStop");
    }

    @Override
    protected void onDestroy(){
        super.onDestroy();
        Log.i("Practice", "onDestroy");
    }

}



