package com.example.covid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashScreenActivity extends AppCompatActivity {
    ImageView ivv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_splash_screen );

        ivv = findViewById( R.id.iv );

        Handler handler = new  Handler(  );
        handler.postDelayed( new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent( SplashScreenActivity.this,MainActivity.class );
                startActivity( intent );
                finish();

            }
        },2000 );

        Animation animation = AnimationUtils.loadAnimation( this,R.anim.splashscreenanimation );
        ivv.startAnimation( animation );

    }

}
