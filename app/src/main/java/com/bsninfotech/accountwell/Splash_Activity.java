package com.bsninfotech.accountwell;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityOptionsCompat;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class Splash_Activity extends AppCompatActivity {

    ImageView imagelogo;


    ApplicationControllerAdmin applicationController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        View decorView = getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        setContentView(R.layout.activity_splash);
        overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink);
        animation.setInterpolator(new LinearInterpolator());
        animation.setRepeatCount(Animation.INFINITE);
        animation.setDuration(700);
        applicationController=(ApplicationControllerAdmin)getApplication();
        getSupportActionBar().hide();
        imagelogo=findViewById(R.id.image_logo);



        Intent intent = getIntent();
        String notify= intent.getStringExtra("My_notification");
        if(notify != null && !notify.isEmpty()){
            if(notify.equals("1")){
                applicationController.setNotify("1");
            }else{
                applicationController.setNotify("0");
            }
        }else{
            applicationController.setNotify("0");
        }



        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash_Activity.this, Login_Actitivty.class);

                    startActivity(intent);
                overridePendingTransition(android.R.anim.slide_in_left, android.R.anim.slide_out_right);
                finish();

            }
        }, 2800);
    }
}
