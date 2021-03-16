package com.example.zein_calculator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;

public class startAct extends AppCompatActivity {

    ImageView back4;

    LottieAnimationView lottieView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        back4.findViewById(R.id.img);
        lottieView.findViewById(R.id.lottie);
        back4.animate().translationY(-1600).setDuration(1000).setStartDelay(4000);
        lottieView.animate().translationY(1400).setDuration(1000).setStartDelay(4000);
    }
}