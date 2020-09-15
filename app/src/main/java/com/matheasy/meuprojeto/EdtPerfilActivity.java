package com.matheasy.meuprojeto;

import android.content.Intent;
import android.graphics.Typeface;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class EdtPerfilActivity extends AppCompatActivity {

    private BDSQLiteHelper bd;
    private static int receberImagem;
    public static CircleImageView img;

    static int[] imagensIds = {
            R.drawable.sem_perfil,
            R.drawable.escolher_imagem_rosto_1,
            R.drawable.escolher_imagem_rosto_2


    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edt_perfil);


        //cria um objeto Typeface que recebe uma fonte exteriolizada
        Typeface font = Typeface.createFromAsset(getAssets(), "Fontes/aispec.ttf");

        //cria um objeto Intent que recebe uma refêrencia a uma nova classe
        final Intent it = new Intent(this, AddPerfilActivity.class);

        //Cria um objeto TextView Que busca o recurso de string pelo id
        TextView mensagem = (TextView) findViewById(R.id.edtPerfil_txtNome);

        //seleciona o metódo setTypeFace que atribui a fonte criada pelo objeto "font" para o objeto de texto
        mensagem.setTypeface(font);



        Intent intent = getIntent();
        final int id = intent.getIntExtra("ID", 0);
        bd = new BDSQLiteHelper(this);
        final Perfil perfil = bd.getPerfil(id);
        receberImagem = perfil.getImagem();

         img = (CircleImageView) findViewById(R.id.edtPerfil_imagem);
         img.setImageResource(imagensIds[receberImagem]);


        FloatingActionButton galeria = (FloatingActionButton) findViewById(R.id.edtPerfil_btnGaleria);
        galeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EdtPerfilActivity.this, activity_escolherImagem.class);
                onPause();
                activity_escolherImagem.verificarIntent(2);
                startActivity(intent);



            }
        });




        final EditText nome = (EditText) findViewById(R.id.edtPerfil_txtNome);

        nome.setText(perfil.getNome());

        Button confirmar = (Button) findViewById(R.id.edtPerfil_btnConfirmar);
        confirmar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                perfil.setNome(nome.getText().toString());
                perfil.setImagem(receberImagem);
                bd.updatePerfil(perfil);
                Toast.makeText(getBaseContext(), "Perfil editado com sucesso.",
                        Toast.LENGTH_SHORT).show();

                Intent it = new Intent(EdtPerfilActivity.this, PerfisActivity.class);
                startActivity(it);
                finish();

            }
        });

        Button cancelar = (Button) findViewById(R.id.edtPerfil_btnCancelar);

        cancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent it = new Intent(EdtPerfilActivity.this, PerfisActivity.class);
                startActivity(it);
                overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

            }
        });



    }

    @Override
    public void onBackPressed() {

        Intent it = new Intent(EdtPerfilActivity.this, PerfisActivity.class);
        startActivity(it);
        finish();

    }

    public static void setReceberImagem(int receberImagem) {
        EdtPerfilActivity.receberImagem = receberImagem;
    }

    public static int getReceberImagem() {
        return receberImagem;
    }

    public void atualizarIntent(){
        onRestart();
    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

    }

    public void finishTwo(){
        super.finish();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }



}
