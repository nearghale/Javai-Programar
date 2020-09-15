package com.matheasy.meuprojeto;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public abstract class animacoes_legais {





    //animação que faz o imageView desaparecer indo para uma direção
    public static void desaparecerImageView(ImageView imageView){
    final long animationDuration = 1000;

    ObjectAnimator animatorY = ObjectAnimator.ofFloat(imageView, "y", 300f);
    animatorY.setDuration(animationDuration);
    ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(imageView, View.ALPHA, 0.0f, 1.0f);
    alphaAnimation.setDuration(animationDuration);

    AnimatorSet animatorSet = new AnimatorSet();
    animatorSet.playTogether(animatorY, alphaAnimation);
    animatorSet.start();

    }



}
