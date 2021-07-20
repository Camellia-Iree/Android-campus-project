package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.bumptech.glide.TransitionOptions;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.transition.DrawableCrossFadeFactory;

import static com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions.withCrossFade;

public class PictureActivity extends AppCompatActivity {

    String mockUrl = "https://lf1-cdn-tos.bytescm.com/obj/static/ies/bytedance_official_cn/_next/static/images/0-390b5def140dc370854c98b8e82ad394.png";
    String mockErrorUrl = "https://upload.wikimedia.org/wikipedia/commons/thumb/6/64/Android_logo_2019_%28stacked%29.svg/400px-Android_logo_2019_%28stacked%29.svg.png";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_picture);

        final ImageView imageView = findViewById(R.id.iv_detail);
        Button btnSuccess = findViewById(R.id.btn_load_success);
        Button btnFail = findViewById(R.id.btn_load_fail);
        Button btnRoundedCorners = findViewById(R.id.btn_rounded_corners);

        btnSuccess.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(PictureActivity.this).load(mockUrl)
                     .placeholder(R.color.loading)
                     .error(R.color.error)
                     .transition(withCrossFade())
                     .into(imageView);
            }
        });

        btnFail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Glide.with(PictureActivity.this).load(mockErrorUrl)
                     .placeholder(R.color.loading)
                     .error(R.color.error)
                     .transition(withCrossFade())
                     .into(imageView);
            }
        });

        btnRoundedCorners.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DrawableCrossFadeFactory draw = new DrawableCrossFadeFactory.Builder(300).setCrossFadeEnabled(true).build();
                Glide.with(PictureActivity.this).load(mockUrl)
                     .placeholder(R.color.loading)
                     .error(R.color.error)
                     .apply(RequestOptions.bitmapTransform(new RoundedCorners(50)))
                     .transition(DrawableTransitionOptions.with(draw))
                     .into(imageView);
            }
        });

    }
}
