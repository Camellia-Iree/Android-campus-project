package com.bytedance.camp.chapter4;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.graphics.Color;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());
        int Hour = calendar.get(Calendar.HOUR_OF_DAY);
        int Minute = calendar.get(Calendar.MINUTE);
        int Second = calendar.get(Calendar.SECOND);

        int tem = (Hour * 15 + Minute / 2) % 360;
        ImageView image = findViewById(R.id.background);
        image.setRotation((float)tem);

        ObjectAnimator anim = ObjectAnimator.ofFloat(image, "rotation", 360 + tem, tem);

        anim.setDuration(24 * 60 * 60 * 1000);    // 24 * 60 * 60 * 1000
        anim.setRepeatCount(ValueAnimator.INFINITE);
        anim.setInterpolator(new LinearInterpolator());
        anim.start();

    }
}
