package com.example.test;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.MediaController;
import android.widget.VideoView;

public class VideoActivity extends AppCompatActivity {

    String mockUrl = "https://stream7.iqilu.com/10339/upload_transcode/202002/18/20200218114723HDu3hhxqIT.mp4";
    private int star = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_video);

        final ImageButton star_empty = findViewById(R.id.star_empty);
        final ImageButton star_full = findViewById(R.id.star_full);
        final ImageView star_mov = findViewById(R.id.star_mov);

        VideoView videoView = findViewById(R.id.vv_detail);
        videoView.setVideoURI(Uri.parse(mockUrl));
        videoView.setMediaController(new MediaController(this));
        videoView.start();

        // star
        star_full.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(star == 0) {
                    star_empty.setAlpha((float) 0);
                    star_mov.setAlpha((float)1);
                    star = 1;

                    ObjectAnimator star_disx = ObjectAnimator.ofFloat(star_mov, "scaleX", 0, 20);
                    ObjectAnimator star_disy = ObjectAnimator.ofFloat(star_mov, "scaleY", 0, 20);
                    ObjectAnimator star_disa = ObjectAnimator.ofFloat(star_mov, "alpha", 1, 0);
                    ObjectAnimator star_ful = ObjectAnimator.ofFloat(star_full, "alpha", 0, 1);
                    AnimatorSet star_dis  = new AnimatorSet();
                    star_dis.playTogether(star_disx, star_disy, star_disa, star_ful);
                    star_dis.setDuration(1000);
                    star_dis.start();
                }
                else{
                    star_empty.setAlpha((float)0.3);
                    star_full.setAlpha((float)0);
                    star = 0;
                }
            }
        });
    }
}
