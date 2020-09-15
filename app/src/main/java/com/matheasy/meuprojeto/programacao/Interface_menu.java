package com.matheasy.meuprojeto.programacao;

import android.graphics.drawable.AnimationDrawable;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.matheasy.meuprojeto.R;
import com.matheasy.meuprojeto.programacao.basica.frag_prog_basico_grupo_2;
import com.matheasy.meuprojeto.programacao.niveis.frag_niveis;

public interface Interface_menu {


    default void animarBotoes(int esquerda_direita, Button aula01, Button aula02, Button aula03, Button aula04, ConstraintLayout constraintLayout) {


        Slide slide = new Slide();
        if (esquerda_direita == 1)
            slide.setSlideEdge(Gravity.LEFT);
        else if (esquerda_direita == 2)
            slide.setSlideEdge(Gravity.RIGHT);

        TransitionManager.beginDelayedTransition(constraintLayout, slide);

        aula01.setVisibility(View.VISIBLE);
        aula02.setVisibility(View.VISIBLE);
        aula03.setVisibility(View.VISIBLE);
        aula04.setVisibility(View.VISIBLE);

    }


    default void animarBotao(Button button) {

        AnimationDrawable animationDrawable = (AnimationDrawable) button.getBackground();
        animationDrawable.setEnterFadeDuration(1000);
        animationDrawable.setExitFadeDuration(1000);
        animationDrawable.start();

    }

    default void desativarBotoes(Button aula01, Button aula02, Button aula03){

        aula01.setEnabled(false);
        aula02.setEnabled(false);
        aula03.setEnabled(false);

    }



    default void desativarBotoes(Button aula01, Button aula02, Button aula03, Button aula04, ImageView img, ImageView img2){

        aula01.setEnabled(false);
        aula02.setEnabled(false);
        aula03.setEnabled(false);
        aula04.setEnabled(false);
        img.setEnabled(false);
        img2.setEnabled(false);
    }

    default void desativarBotoes(Button aula01, Button aula02, Button aula03, Button aula04, ImageView img){

        aula01.setEnabled(false);
        aula02.setEnabled(false);
        aula03.setEnabled(false);
        aula04.setEnabled(false);
        img.setEnabled(false);
    }




}
