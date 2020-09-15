package com.matheasy.meuprojeto.programacao.basica;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.matheasy.meuprojeto.Activity_oque_ira_aprender_capitulo;
import com.matheasy.meuprojeto.R;
import com.matheasy.meuprojeto.programacao.Interface_menu;
import com.matheasy.meuprojeto.programacao.niveis.frag_niveis;


public class frag_prog_basico_grupo_1 extends Fragment implements Interface_menu {

    private Button
            aula01,
            aula02,
            aula03,
            aula04;

    private ImageView btn_prev, btn_next;
    private TextView home;

    private Animation scale;
    ConstraintLayout constraintLayout;


    public frag_prog_basico_grupo_1() {

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // obter a view do fragmento
        View view = inflater.inflate(R.layout.fragment_prog_basico_grupo_1, container, false);
        // linkar com o componente da view


        aula01 = (Button) view.findViewById(R.id.basico_introducao);
        aula02 = (Button) view.findViewById(R.id.basico_introducao_java);
        aula03 = (Button) view.findViewById(R.id.basico_introducao_classes_objetos_metodos_strings);
        aula04 = (Button) view.findViewById(R.id.basico_instrucoes_controle_parte_1);

        btn_prev = (ImageView) view.findViewById(R.id.basico_btn_prev_grupo_1);
        btn_next = (ImageView) view.findViewById(R.id.basico_btn_next_grupo_1);

        home = (TextView) view.findViewById(R.id.home_basico_grupo_1);


        constraintLayout = (ConstraintLayout) view.findViewById(R.id.frag_disciplinas_constraint);
        scale = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.scale_animation_button);


        animarBotao(aula01);
        animarBotao(aula02);
        animarBotao(aula03);
        animarBotao(aula04);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                animarBotoes(1, aula01, aula02, aula03, aula04, constraintLayout);
            }
        }, 100);


        aula01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aula01.startAnimation(scale);

                Intent it = new Intent(getActivity(), Activity_oque_ira_aprender_capitulo.class);
                it.putExtra("CONTEUDO", 1);
                it.putExtra("TITULO", "CAPITÚLO 1");
                startActivity(it);


                //      desativarBotoes(aula01, aula02, aula03, aula04, btn_prev, btn_next);
            }
        });

        aula02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aula02.startAnimation(scale);
                //        desativarBotoes(aula01, aula02, aula03, aula04, btn_prev, btn_next);
            }
        });

        aula03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aula03.startAnimation(scale);
                //       desativarBotoes(aula01, aula02, aula03, aula04, btn_prev, btn_next);
            }
        });

        aula04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aula04.startAnimation(scale);
                //      desativarBotoes(aula01, aula02, aula03, aula04, btn_prev, btn_next);
            }
        });


        //--------------botões de voltar e avançar---------------

        btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamarFragment(0);
            }
        });

        btn_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                chamarFragment(1);

            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desativarBotoes(aula01, aula02, aula03, aula04, btn_prev, btn_next);
                chamarFragment(-1);
            }
        });


        return view;
    }


    private void chamarFragment(final int fragment) {

        desativarBotoes(aula01, aula02, aula03, aula04, btn_prev, btn_next);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                switch (fragment) {

                    case 0:

                        ft.replace(R.id.escolherDificuldade_frameLayout, new frag_niveis(), "NewFragmentTag");
                        break;

                    case 1:

                        ft.replace(R.id.escolherDificuldade_frameLayout, new frag_prog_basico_grupo_2(), "NewFragmentTag");
                        break;

                    case -1:
                        ft.replace(R.id.escolherDificuldade_frameLayout, new frag_niveis(), "NewFragmentTag");
                        break;


                }

                ft.commit();

            }
        }, 100);
    }


}
