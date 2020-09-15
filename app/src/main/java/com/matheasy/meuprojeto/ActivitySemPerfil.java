package com.matheasy.meuprojeto;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
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

public class ActivitySemPerfil extends AppCompatActivity {

     private Button button;
     private ImageView personagem;
     private TextSwitcher textSwitcher;
     private ImageView nextButton;
     private int stringIndex = 0;
     private String[] row =
             {"OLÁ AMIGO(A)!\n"+
             "Seja muito bem vindo!\n" +
             " aqui você irá aprender como programar em java\n" +
             " de forma simples e fácil",


             "Mas antes de começar o seu aprendizado\n" +
             "vamos criar um novo perfil para você.\n" +
             "Clique no botão logo abaixo\n"};

      private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity__sem_perfil);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        //cria um objeto Typeface que recebe uma fonte exteriolizada
        final Typeface font = Typeface.createFromAsset(getAssets(), "Fontes/aispec.ttf");

        textSwitcher = findViewById(R.id.semPerfil_mensagem);
        nextButton = findViewById(R.id.semPerfil_botaoNext);
        button = findViewById(R.id.btn_adicionarPerfil);
        personagem = findViewById(R.id.sem_perfil_mascote);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stringIndex == row.length -1){
                    
                    overridePendingTransition(R.anim.slide_in_right_short, R.anim.slide_out_left_short);

                    stringIndex = 0;
                    textSwitcher.setText(row[stringIndex]);


                }else{
                    personagem.setImageResource(R.drawable.sem_perfil_personagem_2);
                    textSwitcher.setText(row[++stringIndex]);
                }

                nextButton.setVisibility(View.GONE);
                button.setVisibility(View.VISIBLE);
            }
        });
        textSwitcher.setFactory(new ViewSwitcher.ViewFactory() {
            @Override
            public View makeView() {
                textView = new TextView(ActivitySemPerfil.this);
                textView.setTextColor(Color.WHITE);
                textView.setTypeface(font);
                textView.setTextSize(18);
                textView.setGravity(Gravity.CENTER);
                return textView;

            }
        });

       textSwitcher.setText(row[stringIndex]);

    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Sair");
        builder.setMessage("Você deseja realmente sair?");

        builder.setPositiveButton("SAIR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });

        builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        AlertDialog dialog = builder.show();
    }

    public void open_criarPerfil(View view) {

        //salva uma confirmação, para dizer ao sistema que nunca foi criado um perfil
        SharedPreferences prefs = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed= prefs.edit();
        ed.putInt("SEM_PERFIL", 1);
        ed.apply();

        final Intent it = new Intent(this, AddPerfilActivity.class);
        activity_escolherImagem.setNomeImg(0);
        startActivity(it);

        finish();


    }



}
