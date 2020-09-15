package com.matheasy.meuprojeto.programacao.niveis;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.transition.Slide;
import android.transition.TransitionManager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;

import com.matheasy.meuprojeto.Activity_escolherDificuldade;
import com.matheasy.meuprojeto.R;
import com.matheasy.meuprojeto.programacao.Interface_menu;
import com.matheasy.meuprojeto.programacao.avancado.frag_prog_avancado_grupo_1;
import com.matheasy.meuprojeto.programacao.basica.frag_prog_basico_grupo_1;
import com.matheasy.meuprojeto.programacao.ensino.intermediario.frag_prog_intermediario_grupo_1;


public class frag_niveis extends Fragment implements Interface_menu {

    private Activity_escolherDificuldade activity;

    private Button
            basico,
            intermediario,
            avancado;


    private Animation scale;
    ConstraintLayout constraintLayout;


    public frag_niveis() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // obter a view do fragmento
        View view = inflater.inflate(R.layout.fragment_niveis, container, false);
        // linkar com o componente da view


        basico = (Button) view.findViewById(R.id.escolherDificuldade_botao_basico);
        intermediario = (Button) view.findViewById(R.id.escolherDificuldade_botao_intermediario);
        avancado = (Button) view.findViewById(R.id.escolherDificuldade_botao_avancado);


        constraintLayout = (ConstraintLayout) view.findViewById(R.id.frag_disciplinas_constraint);
        scale = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.scale_animation_button);

        animarBotao(basico);
        animarBotao(intermediario);
        animarBotao(avancado);


        basico.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                basico.startAnimation(scale);
                //  desativarBotoes();

                abrirGruposDeAulas(1);


            }
        });


        intermediario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intermediario.startAnimation(scale);
                //     desativarBotoes();
                abrirGruposDeAulas(2);

            }
        });


        avancado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                avancado.startAnimation(scale);
                abrirGruposDeAulas(3);
                //     desativarBotoes();

            }
        });


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                animarBotoesMenu(1);
            }
        }, 200);


        return view;
    }


    private void abrirGruposDeAulas(final int disciplina) {

        desativarBotoes(basico, intermediario, avancado);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                switch (disciplina) {

                    case 1:
                        ft.replace(R.id.escolherDificuldade_frameLayout, new frag_prog_basico_grupo_1(), "NewFragmentTag");
                        break;

                    case 2:
                        ft.replace(R.id.escolherDificuldade_frameLayout, new frag_prog_intermediario_grupo_1(), "NewFragmentTag");
                        break;

                    case 3:
                        ft.replace(R.id.escolherDificuldade_frameLayout, new frag_prog_avancado_grupo_1(), "NewFragmentTag");
                        break;


                }

                ft.commit();

            }
        }, 300);


    }


    private void animarBotoesMenu(int cima_baixo) {

        Slide slide = new Slide();

        if (cima_baixo == 2) {
            slide.setSlideEdge(Gravity.BOTTOM);
        } else if (cima_baixo == 1) {
            slide.setSlideEdge(Gravity.TOP);
        }

        TransitionManager.beginDelayedTransition(constraintLayout, slide);

        basico.setVisibility(View.VISIBLE);
        intermediario.setVisibility(View.VISIBLE);
        avancado.setVisibility(View.VISIBLE);

    }


}
