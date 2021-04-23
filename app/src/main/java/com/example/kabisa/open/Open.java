package com.example.kabisa.open;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.Animatable2;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.example.kabisa.MainActivity;
import com.example.kabisa.R;


public class Open extends AppCompatActivity {
    CharSequence charSequence;
    int index ;
    long delay = 200;
    Handler handler = new Handler();
    private TextView textView;
    //private ImageView img;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.open);
        textView = findViewById(R.id.text_health);
        /*
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN
                        , WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Animation animation = AnimationUtils.loadAnimation(this,
                (R.anim.top_wave));
        animatIonText("Kabisa");
        img.setAnimation(animation);
        Glide.with(this).load("")
                .asGif()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(img);
        Animation animation2 = AnimationUtils.loadAnimation(this,
                (R.anim.bottom_wave));
        img.setAnimation(animation2);

         */
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
               startActivity(new Intent(Open.this, MainActivity.class));
               finish();
            }
        },4000);
    }
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            textView.setText(charSequence.subSequence(0,index++));
            if (index <= charSequence.length()){
                handler.postDelayed(runnable, delay);
            }
        }
    };
    /*
    public void animatIonText(CharSequence cs){
        charSequence = cs;
        index = 0;
        textView.setText("");
        handler.removeCallbacks(runnable);
        handler.postDelayed(runnable,delay);
    }
    */
}
