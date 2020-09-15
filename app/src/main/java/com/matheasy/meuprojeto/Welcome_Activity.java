package com.matheasy.meuprojeto;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Handler;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatDelegate;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.hitomi.cmlibrary.CircleMenu;
import com.hitomi.cmlibrary.OnMenuSelectedListener;

import java.io.File;

import de.hdodenhof.circleimageview.CircleImageView;

public class Welcome_Activity extends AppCompatActivity {

    private ConstraintLayout l1, l2;
    private Button btnAprender, btnCalcular;
    private Animation uptodown, downtoup;
    private static String nome;
    private TextView txt1, txt2;
    private BDSQLiteHelper bd;
    private ImageView aprender, treinar;
    Perfil perfil = new Perfil();

    int[] imagensIds = {
            R.drawable.sem_perfil,
            R.drawable.escolher_imagem_rosto_1,
            R.drawable.escolher_imagem_rosto_2,

    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome_);
        AppCompatDelegate.setCompatVectorFromResourcesEnabled(true);
        Intent intent = getIntent();
        final int id = intent.getIntExtra("ID", 0);


        try {
            bd = new BDSQLiteHelper(this);
            perfil = bd.getPerfil(id);
        } catch (Exception e) {

            Intent it = new Intent(Welcome_Activity.this, PerfisActivity.class);
            startActivity(it);
            finish();

        }


        //cria um objeto Typeface que recebe uma fonte exteriolizada
        final Typeface font = Typeface.createFromAsset(getAssets(), "Fontes/aispec.ttf");

        final ConstraintLayout constraintLayout = (ConstraintLayout) findViewById(R.id.welcome_background);

        ImageView button = (ImageView) findViewById(R.id.welcome_continuarBtn);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {

                constraintLayout.setBackgroundResource(R.drawable.background_welcome_azul_luz_final);

            }
        }, 800);

        ;

        aprender = (ImageView) findViewById(R.id.welcome_opcao_aprender);

        aprender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent it = new Intent(Welcome_Activity.this, Activity_escolherDificuldade.class);
                startActivity(it);
                finish();

            }
        });


        l1 = (ConstraintLayout) findViewById(R.id.welcome_frameLayoutTop);
        l2 = (ConstraintLayout) findViewById(R.id.welcome_frameLayoutButtom);
        TextView nome = (TextView) findViewById(R.id.welcome_txtNome);
        CircleImageView img = (CircleImageView) findViewById(R.id.welcome_imagem_perfil);
        img.setImageResource(imagensIds[perfil.getImagem()]);
        nome.setText(perfil.getNome());

        uptodown = AnimationUtils.loadAnimation(this, R.anim.uptodown);
        downtoup = AnimationUtils.loadAnimation(this, R.anim.downtoup);
        l1.setAnimation(uptodown);
        l2.setAnimation(downtoup);


        final ImageView livro = (ImageView) findViewById(R.id.welcome_livro);


        animarLivro(livro);


    }


    private void animarLivro(final ImageView livro) {

        Animation animUpDown;

        animUpDown = AnimationUtils.loadAnimation(getApplicationContext(),
                R.anim.up_down);

        livro.startAnimation(animUpDown);


    }


    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("ATENÇÃO");
        builder.setMessage("O QUE VOCÊ DESEJA FAZER?");


        builder.setNeutralButton("MUDAR DE PERFIL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent it = new Intent(Welcome_Activity.this, PerfisActivity.class);
                startActivity(it);
                finish();
            }
        });

        builder.setPositiveButton("SAIR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finishAffinity();
            }
        });

        builder.setNegativeButton("FECHAR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }

        });

        AlertDialog dialog = builder.show();
    }

    public static String getNome() {
        return nome;
    }

    public static void setNome(String nome) {
        Welcome_Activity.nome = nome;
    }
}
