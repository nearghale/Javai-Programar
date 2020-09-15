package com.matheasy.meuprojeto;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SplashActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final int id;
        SharedPreferences prefs= getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        id = (prefs.getInt("ID", -1));


        //cria um objeto Typeface que recebe uma fonte exteriolizada
        Typeface font = Typeface.createFromAsset(getAssets(), "Fontes/aispec.ttf");

        //cria um objeto Intent que recebe uma refêrencia a uma nova classe
        final Intent it = new Intent(this, PerfisActivity.class);


        ConstraintLayout constraintLayout = findViewById(R.id.splash_screen_layout);

        AnimationDrawable animationDrawable = (AnimationDrawable) constraintLayout.getBackground();
        animationDrawable.setEnterFadeDuration(1800);
        animationDrawable.setExitFadeDuration(1800);
        animationDrawable.start();


                new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {


                if(id >= 0){
                    Intent intent = new Intent(SplashActivity.this, Welcome_Activity.class);
                    intent.putExtra("ID", id);
                    startActivity(intent);
                    finish();
                }else {

                    startActivity(it);
                    finish();
                }



            }
        }, 5000);

    }


}
