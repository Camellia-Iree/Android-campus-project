package com.example.test1;

import androidx.appcompat.app.AppCompatActivity;

import android.media.Rating;
import android.os.Bundle;
import android.provider.Telephony;
import android.util.Log;
import android.view.SurfaceControl;
import android.view.View;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.logging.LoggingPermission;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button btn1 = findViewById(R.id.btn1);
        btn1.setOnClickListener(new View.OnClickListener(){

            ImageView iv1 = findViewById(R.id.iv1);
            TextView txt1 = findViewById(R.id.txt1);
            ProgressBar pb1 = findViewById(R.id.pb1);
            SeekBar sb1 = findViewById(R.id.sb1);
            RatingBar rb1 = findViewById(R.id.rb1);

            @Override
            public void onClick(View v) {
                Log.d("yes", "good");

                txt1.setText("WELCOME");
                iv1.clearColorFilter();
                int i = pb1.getProgress();
                i += 10;
                if(i == 110) i = 0;
                pb1.setProgress(i);
                float l = rb1.getRating();
                l += 0.5;
                if(l == 5.5) l = 0;
                rb1.setRating(l);
            }
        });

    }


}