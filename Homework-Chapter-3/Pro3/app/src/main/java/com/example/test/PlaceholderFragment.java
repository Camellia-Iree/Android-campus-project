package com.example.test;


import android.animation.ObjectAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.LottieAnimationView;

public class PlaceholderFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // TODO ex3-3: 修改 fragment_placeholder，添加 loading 控件和列表视图控件

        return inflater.inflate(R.layout.fragment_placeholder, container, false);

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


}
