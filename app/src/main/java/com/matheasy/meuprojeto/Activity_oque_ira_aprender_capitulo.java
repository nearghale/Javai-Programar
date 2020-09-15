package com.matheasy.meuprojeto;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import com.matheasy.meuprojeto.programacao.Conteudo;

public class Activity_oque_ira_aprender_capitulo extends AppCompatActivity {


    private TextSwitcher textSwitcher;
    private Button button;
    private ImageView nextButton;
    private int stringIndex = 1;
    public static int capitulo ;
    private TextView textView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_oque_ira_aprender_capitulo);

        Toolbar toolbar = findViewById(R.id.toolbar_oque_ira_aprender);
        setSupportActionBar(toolbar);

        Intent it = getIntent();
        capitulo = it.getIntExtra("CONTEUDO", 0);
        Toolbar toolbar1 = (Toolbar) findViewById(R.id.toolbar_oque_ira_aprender);
        toolbar.setTitle(it.getStringExtra("TITULO"));

        Conteudo cap = new Conteudo(capitulo);

        //cria um objeto Typeface que recebe uma fonte exteriolizada
        final Typeface font = Typeface.createFromAsset(getAssets(), "Fontes/aispec.ttf");

        textSwitcher = findViewById(R.id.oque_aprender_capitulo_textSwitcher);
        nextButton = (ImageView) findViewById(R.id.oque_ira_aprender_botao_next);

        button = findViewById(R.id.oque_aprender_capitulo_btn_continuar);


        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stringIndex == capitulo ){

                    overridePendingTransition(R.anim.slide_in_right_short, R.anim.slide_out_left_short);

                    cap.setConteudoPagina2();


                    textSwitcher.setText(cap.getConteudo() );


                }else{

                    textSwitcher.setText(cap.getConteudo() );
                }

                nextButton.setVisibility(View.GONE);
                button.setVisibility(View.VISIBLE);
            }
        });
        textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                textView = new TextView(Activity_oque_ira_aprender_capitulo.this);
                textView.setTextColor(Color.WHITE);
                textView.setTypeface(font);
                textView.setTextSize(17);
                textView.setGravity(Gravity.CENTER);
                return textView;

            }
        });

        textSwitcher.setText(cap.getConteudo());




    }
}
