package com.dell.waffar;



import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import gr.net.maroulis.library.EasySplashScreen;

public class SplashScreen extends AppCompatActivity {

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        EasySplashScreen easySplashScreen = new EasySplashScreen(SplashScreen.this).withFullScreen()
                .withTargetActivity(LoginActivity.class).
                        withSplashTimeOut(3000).
                        withBackgroundResource(R.drawable.bg).
                        withLogo(R.drawable.logob).
                        withFooterText("  Developed by \nCognitive team");


        easySplashScreen.getFooterTextView().setTextColor(Color.WHITE);
        easySplashScreen.getFooterTextView().setPaddingRelative(0,0,0,50);


        easySplashScreen.getLogo().setPaddingRelative(0,0,0,200);

        View view = easySplashScreen.create();

        setContentView(view);

    }
}
