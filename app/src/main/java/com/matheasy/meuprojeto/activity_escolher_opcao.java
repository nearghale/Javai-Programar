package com.matheasy.meuprojeto;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class activity_escolher_opcao extends AppCompatActivity {

    private BDSQLiteHelper bd;
    Perfil perfil = new Perfil();
    private String[] textos =
            {"                             PRIMEIRA OPÇÃO\n"+
             "        1º) Você começa do básico e vai progredindo.\n"+
             "        2º) Você só poderá ter acesso as aulas\n"+
             "        que foram desbloqueadas ao passar de nível.\n",


             "                             SEGUNDA OPÇÃO\n"+
             "        1°) Você poderá acessar qualquer aula.\n"+
             "        2º) Você poderá acessar diretamente a \n."+
             "        opção de treinamento.",

             "Não Clique em mim, eu não sou  uma opção. \n " +
             "Seu tonto(a)!",

             "Escolha a opção que você achar melhor para seus estudos"

            };

    ImageView primeiraOpcao;
    ImageView segundaOpcao;
    ImageView personagem;


    TextView primeiroTexto;
    TextView segundoTexto;
    TextView fala;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolher_opcao);

        Intent intent = getIntent();
        final int id = intent.getIntExtra("ID", 0);
        bd = new BDSQLiteHelper(this);
        perfil = bd.getPerfil(id);
        String opcao = perfil.getOpcao();

        if (!opcao.equals("opcao")){
            finish();
            Intent it = new Intent(activity_escolher_opcao.this, Welcome_Activity.class);
            it.putExtra("ID", perfil.getId());
            startActivity(it);
        }else{

            SharedPreferences prefs = getSharedPreferences("preferencias",Context.MODE_PRIVATE);
            SharedPreferences.Editor ed= prefs.edit();
            ed.putInt("ID", -1);
            ed.apply();

        }



        //cria um objeto Typeface que recebe uma fonte exteriolizada
        final Typeface font = Typeface.createFromAsset(getAssets(), "Fontes/aispec.ttf");

        final TextView primeiroTexto = (TextView) findViewById(R.id.escolher_opcao_texto1);
        final TextView segundoTexto = (TextView) findViewById(R.id.escolher_opcao_texto2);
        final TextView falaTexto = (TextView) findViewById(R.id.escolher_opcao_fala);

        primeiraOpcao = (ImageView) findViewById(R.id.escolher_opcao_primeira);
        segundaOpcao = (ImageView) findViewById(R.id.escolher_opcao_segunda);
        personagem = (ImageView) findViewById(R.id.escolher_opcao_personagem);

        aparecerImageView(1, primeiraOpcao, primeiroTexto);
        aparecerImageView(2, segundaOpcao, segundoTexto);




        primeiroTexto.setTypeface(font);
        segundoTexto.setTypeface(font);


        primeiroTexto.setTextSize(12);
        segundoTexto.setTextSize(12);

        primeiroTexto.setText(textos[0]);
        segundoTexto.setText(textos[1]);


        primeiraOpcao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                primeiraOpcao.setImageResource(R.drawable.primeira_opcao_click);
                chamarActivity(1);
            }
        });

        segundaOpcao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                segundaOpcao.setImageResource(R.drawable.segunda_opcao_click);
                chamarActivity(2);

            }
        });

        personagem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                falaTexto.setText(textos[2]);
                personagem.setImageResource(R.drawable.personagem_nervoso);


                new Handler().postDelayed(new Runnable() {

                    @Override
                    public void run() {

                        falaTexto.setText(textos[3]);
                        personagem.setImageResource(R.drawable.personagem_sem_oculos);

                    }
                }, 3000);


            }
        });


    }

    @Override
    public void onBackPressed() {

        Intent it = new Intent(activity_escolher_opcao.this, PerfisActivity.class);
        startActivity(it);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        finish();
    }

    public void chamarActivity(final int image){






        AlertDialog.Builder builder = new AlertDialog.Builder(activity_escolher_opcao.this);

        builder.setTitle("Confirmar");
        builder.setMessage("Você deseja realmente deseja essa metodologia de ensino?");

        builder.setPositiveButton("SIM", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {



                Intent it = new Intent(activity_escolher_opcao.this, Welcome_Activity.class);
                String novaOpcao;
                    switch (image) {

                        case 1:

                            novaOpcao = "primeira";
                            perfil.setOpcao(novaOpcao);
                            bd.updatePerfil(perfil);
                            Toast.makeText(getBaseContext(), "primeira Métodologia escolhida com sucesso!",
                                    Toast.LENGTH_SHORT).show();
                            it.putExtra("ID", perfil.getId());

                            salvarPrerencia();


                            startActivity(it);
                            finish();
                            break;

                        case 2:

                            novaOpcao = "segunda";
                            perfil.setOpcao(novaOpcao);
                            bd.updatePerfil(perfil);
                            Toast.makeText(getBaseContext(), "segunda Métodologia escolhida com sucesso!",
                                    Toast.LENGTH_SHORT).show();
                            it.putExtra("ID", perfil.getId());

                            salvarPrerencia();

                            startActivity(it);
                            finish();
                            break;




                    }






            }
        });





        builder.setNegativeButton("NÃO", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                switch (image){

                    case 1:
                    primeiraOpcao.setImageResource(R.drawable.primeira_opcao);
                    break;

                    case 2:
                    segundaOpcao.setImageResource(R.drawable.segunda_opcao);
                    break;



                }



            }
        });

        AlertDialog dialog = builder.show();





    }

    public static void aparecerImageView( int identificao, ImageView imageView,final TextView textView){

        textView.setVisibility(View.INVISIBLE);
        final long animationDuration = 1000;
        ObjectAnimator animatorY;
        AnimatorSet animatorSet = new AnimatorSet();
        ObjectAnimator alphaAnimation = ObjectAnimator.ofFloat(imageView, View.ALPHA, 0.0f, 1.0f);
        alphaAnimation.setDuration(animationDuration);

        switch (identificao){
            case 1:
                animatorY = ObjectAnimator.ofFloat(imageView, "y", 205f);
                animatorY.setDuration(animationDuration);
                animatorSet.playTogether(animatorY, alphaAnimation);
                break;

            case 2:

                animatorY = ObjectAnimator.ofFloat(imageView, "y", 613f);
                animatorY.setDuration(animationDuration);
                animatorSet.playTogether(animatorY, alphaAnimation);
                break;



        }



        imageView.setVisibility(View.INVISIBLE);
        imageView.setVisibility(View.VISIBLE);

        animatorSet.start();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                textView.setVisibility(View.VISIBLE);

            }
        }, 1000);

        ;


    }

    public void  salvarPrerencia(){

        //salva o ID como preferencia
        Intent intent = getIntent();
        final int id = intent.getIntExtra("ID", 0);
        SharedPreferences prefs = getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        SharedPreferences.Editor ed= prefs.edit();
        ed.putInt("ID", id);
        ed.apply();


    }




}
