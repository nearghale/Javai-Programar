package com.matheasy.meuprojeto.programacao.avancado;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.matheasy.meuprojeto.R;
import com.matheasy.meuprojeto.programacao.Interface_menu;
import com.matheasy.meuprojeto.programacao.ensino.intermediario.frag_prog_intermediario_grupo_1;
import com.matheasy.meuprojeto.programacao.ensino.intermediario.frag_prog_intermediario_grupo_2;
import com.matheasy.meuprojeto.programacao.niveis.frag_niveis;


public class frag_prog_avancado_grupo_2 extends Fragment implements Interface_menu {

    private Button
            aula01,
            aula02,
            aula03,
            aula04;

    private ImageView btn_prev;
    private TextView home;

    private Animation scale;
    ConstraintLayout constraintLayout;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_prog_avancado_grupo_2, container, false);

        aula01 = (Button) view.findViewById(R.id.avancado_estrutura_dados_genericas_personalizadas);
        aula02 = (Button) view.findViewById(R.id.avancado_componentes_gui_parte_2);
        aula03 = (Button) view.findViewById(R.id.avancado_concorrencia);
        aula04 = (Button) view.findViewById(R.id.avancado_acesso_banco_dados_jdbc);

        home = (TextView) view.findViewById(R.id.home_avancado_grupo_2);

        btn_prev = (ImageView) view.findViewById(R.id.avancado_btn_prev_grupo_2);

        constraintLayout = (ConstraintLayout) view.findViewById(R.id.frag_avancado_grupo_2_constraint);
        scale = AnimationUtils.loadAnimation(getActivity().getApplicationContext(), R.anim.scale_animation_button);


        animarBotao(aula01);
        animarBotao(aula02);
        animarBotao(aula03);
        animarBotao(aula04);

        aula01.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aula01.startAnimation(scale);
                //   desativarBotoes(aula01, aula02, aula03, aula04, btn_prev, btn_next);
            }
        });

        aula02.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aula02.startAnimation(scale);
                //   desativarBotoes(aula01, aula02, aula03, aula04, btn_prev, btn_next);
            }
        });

        aula03.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aula03.startAnimation(scale);
                //     desativarBotoes(aula01, aula02, aula03, aula04, btn_prev, btn_next);
            }
        });

        aula04.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aula04.startAnimation(scale);
                //   desativarBotoes(aula01, aula02, aula03, aula04, btn_prev, btn_next);
            }
        });

        btn_prev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                chamarFragment(0);
                //   desativarBotoes(aula01, aula02, aula03, aula04, btn_prev, btn_next);
            }
        });

        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                desativarBotoes(aula01, aula02, aula03, aula04, btn_prev);
                chamarFragment(-1);
            }
        });


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                animarBotoes(2, aula01, aula02, aula03, aula04, constraintLayout);
            }
        }, 100);

        return view;
    }

    private void chamarFragment(final int fragment) {

        desativarBotoes(aula01, aula02, aula03, aula04, btn_prev);

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                final FragmentTransaction ft = getFragmentManager().beginTransaction();
                switch (fragment) {

                    case 0:
                        ft.replace(R.id.escolherDificuldade_frameLayout, new frag_prog_avancado_grupo_1(), "NewFragmentTag");
                        break;

                    case -1:
                        ft.replace(R.id.escolherDificuldade_frameLayout, new frag_niveis(), "NewFragmentTag");
                        break;


                }

                ft.commit();

            }
        }, 300);


    }


}
