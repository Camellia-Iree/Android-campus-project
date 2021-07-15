package com.example.test;


import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.airbnb.lottie.LottieAnimationView;
import com.example.test.auxiliary.MyAdapter;
import com.example.test.auxiliary.Testdata;
import com.example.test.auxiliary.TestdataSet;

import java.util.ArrayList;
import java.util.List;

import com.example.test.R;

public class PlaceholderFragment extends Fragment {

    private List<Testdata> mDataset = new ArrayList<Testdata>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件
        initData();
        View view = inflater.inflate(R.layout.fragment_placeholder, container, false);
        MyAdapter adapter = new MyAdapter(getContext(), R.layout.activity_item, mDataset);
        ListView list_view = view.findViewById(R.id.list_view);
        list_view.setAdapter(adapter);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        getView().postDelayed(new Runnable() {
            @Override
            public void run() {
                // 这里会在 5s 后执行
                // TODO ex3-4：实现动画，将 lottie 控件淡出，列表数据淡入
                LottieAnimationView anim = getView().findViewById(R.id.anim);
                TextView text = getView().findViewById(R.id.text);
                ImageView back = getView().findViewById(R.id.back);

                ObjectAnimator animator1 = ObjectAnimator.ofFloat(anim, "Alpha", 1, 0);
                animator1.setDuration(1000);
                animator1.setRepeatCount(0);
                animator1.start();

                ObjectAnimator animator2 = ObjectAnimator.ofFloat(text, "Alpha", 1, 0);
                animator2.setDuration(1000);
                animator2.setRepeatCount(0);
                animator2.start();

                ObjectAnimator animator3 = ObjectAnimator.ofFloat(back, "Alpha", 1, 0);
                animator3.setDuration(1000);
                animator3.setRepeatCount(0);
                animator3.start();
            }
        }, 5000);

    }

    public void initData(){

        mDataset.clear();
        mDataset.add(new Testdata("小红", "你好  - 刚刚", R.drawable.head0));
        mDataset.add(new Testdata("小蓝", "www  - 15:03", R.drawable.head1));
        mDataset.add(new Testdata("小绿", "阿巴阿巴  -15.01", R.drawable.head2));
        mDataset.add(new Testdata("默默", "我密码学满了  -14:44", R.drawable.peace));
        mDataset.add(new Testdata("笑笑", "我的天  -14:23", R.drawable.head0));
        mDataset.add(new Testdata("江江", "我ADS满了  -14:03", R.drawable.peace));
        mDataset.add(new Testdata("小雪", "我均绩4.8了  -13:25", R.drawable.head1));
        mDataset.add(new Testdata("姐姐", "好好学习  -13:08", R.drawable.head2));
        mDataset.add(new Testdata("烈烈", "你的外卖到了  -13:00", R.drawable.head0));
        mDataset.add(new Testdata("妈妈", "加油  -12:45", R.drawable.head1));
        mDataset.add(new Testdata("爸爸", "努力  -12:44", R.drawable.peace));

    }

}
