package com.example.test;

import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;
import com.example.test.auxiliary.MyAdapter;
import com.example.test.auxiliary.Testdata;
import com.example.test.auxiliary.TestdataSet;
import java.util.ArrayList;
import java.util.List;
import android.os.Handler;
import android.widget.TextView;

public class Listdata extends AppCompatActivity {

    private List<Testdata> mDataset = new ArrayList<Testdata>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listdata);
        initData();
        MyAdapter adapter = new MyAdapter(Listdata.this, R.layout.activity_item, mDataset);
        ListView listview = findViewById(R.id.list);
        listview.setAdapter(adapter);
    }

    private void initData(){

        mDataset.add(new Testdata("小红", "你好  - 刚刚", R.drawable.head0));
        mDataset.add(new Testdata("小蓝", "www  - 15:03", R.drawable.head1));
        mDataset.add(new Testdata("小绿", "阿巴阿巴  -15.01", R.drawable.head2));
        mDataset.add(new Testdata("默默", "我密码学满了  -14:44", R.drawable.head2));
        mDataset.add(new Testdata("笑笑", "我的天  -14:23", R.drawable.head0));
        mDataset.add(new Testdata("江江", "我ADS满了  -14:03", R.drawable.head0));
        mDataset.add(new Testdata("小雪", "我均绩4.8了  -13:25", R.drawable.head1));
        mDataset.add(new Testdata("姐姐", "好好学习  -13:08", R.drawable.head2));
        mDataset.add(new Testdata("烈烈", "你的外卖到了  -13:00", R.drawable.head0));
        mDataset.add(new Testdata("妈妈", "加油  -12:45", R.drawable.head1));
        mDataset.add(new Testdata("爸爸", "努力  -12:44", R.drawable.head1));

        return;
    }
}


