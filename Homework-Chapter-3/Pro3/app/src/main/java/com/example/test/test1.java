package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.SeekBar;

import com.airbnb.lottie.LottieAnimationView;

public class test1 extends AppCompatActivity {

    private CheckBox box;
    private LottieAnimationView anim;
    private SeekBar bar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test1);

        ObjectAnimator animatorX = ObjectAnimator.ofFloat(findViewById(R.id.btn_ret), "scaleX", 1.1f, 0.9f);
        animatorX.setDuration(1000);
        animatorX.setRepeatMode(ValueAnimator.REVERSE);
        animatorX.setRepeatCount(ValueAnimator.INFINITE);
        animatorX.setInterpolator(new LinearInterpolator());
        animatorX.start();

        ObjectAnimator animatorY = ObjectAnimator.ofFloat(findViewById(R.id.btn_ret), "scaleY", 1.1f, 0.9f);
        animatorY.setDuration(1000);
        animatorY.setRepeatMode(ValueAnimator.REVERSE);
        animatorY.setRepeatCount(ValueAnimator.INFINITE);
        animatorY.setInterpolator(new LinearInterpolator());
        animatorY.start();

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playTogether(animatorX, animatorY);
        animatorSet.start();

        Button btn_ret = findViewById(R.id.btn_ret);
        btn_ret.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        box = findViewById(R.id.box);
        bar = findViewById(R.id.bar);
        bar.setEnabled(false);              // 初始化为不可选中，不然似乎会崩溃orz

        box.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                anim = findViewById(R.id.anim);
                bar = findViewById(R.id.bar);
                if(isChecked){
                    anim.playAnimation();
                    bar.setEnabled(false);
                    // 读取当前seekbar并设置动画progress
                    float pro = (float)bar.getProgress() / 100;
                    anim.setProgress(pro);
                }
                else{
                    anim.pauseAnimation();
                    bar.setEnabled(true);
                    // 读取当前进度并显示到seekbar上
                    float pro = anim.getProgress();
                    pro = pro * 100;
                    bar.setProgress((int)pro);
                }
            }
        });

        bar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                float pro = (float)progress / 100;
                anim.setProgress(pro);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

}
