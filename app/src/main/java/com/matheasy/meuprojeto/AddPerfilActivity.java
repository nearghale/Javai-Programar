package com.matheasy.meuprojeto;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import de.hdodenhof.circleimageview.CircleImageView;

public class AddPerfilActivity extends AppCompatActivity {

    private BDSQLiteHelper bd;
    private final int GALERIA_IMAGENS = 1;
    private final int PERMISSAO_REQUEST = 2;
    private final int TIRAR_FOTO = 1;
    private ImageView imagem;
    private Bitmap imageBitMap;
    private static String backupNome;
    private SharedPreferences prefs;
    private EditText nome;


    int[] imagensIds = {
            R.drawable.sem_perfil,
            R.drawable.escolher_imagem_rosto_1,
            R.drawable.escolher_imagem_rosto_2,

    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bd = new BDSQLiteHelper(this); //instancia do objeto de banco de dados
        setContentView(R.layout.activity_add_perfil);

        final CircleImageView img = (CircleImageView) findViewById(R.id.addPerfil_imagem);
        img.setImageResource(imagensIds[(activity_escolherImagem.getnomeImg())]);

        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirActivityEscolherImg();
            }
        });


        //cria um objeto Typeface que recebe uma fonte exteriolizada
        Typeface font = Typeface.createFromAsset(getAssets(), "Fontes/aispec.ttf");

        //cria um objeto Intent que recebe uma refêrencia a uma nova classe
        final Intent it = new Intent(this, AddPerfilActivity.class);

        //Cria um objeto TextView Que busca o recurso de string pelo id
        TextView mensagem = (TextView) findViewById(R.id.addPerfil_txtNome);

        //seleciona o metódo setTypeFace que atribui a fonte criada pelo objeto "font" para o objeto de texto
        mensagem.setTypeface(font);

        prefs = getSharedPreferences("preferencias", Context.MODE_PRIVATE);


        //declaração de objetos e ação para o botão acessar a galeria//
        imagem = (ImageView) findViewById(R.id.addPerfil_imagem);

        nome = (EditText) findViewById(R.id.addPerfil_txtNome);
        nome.setText(activity_escolherImagem.getBackupNome());
        Button adicionar = (Button) findViewById(R.id.addPerfil_btnCriarPerfil);

        FloatingActionButton galeria = (FloatingActionButton) findViewById(R.id.addPerfil_btnGaleria);
        galeria.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                abrirActivityEscolherImg();

            }
        });






        adicionar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){

                try {

                    if (nome.getText().toString().equals("")) {
                        AlertDialog.Builder builder = new AlertDialog.Builder(AddPerfilActivity.this);

                        builder.setTitle("ERRO");
                        builder.setMessage("Insira um nome antes de criar um perfil");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        });
                        AlertDialog dialog = builder.show();


                    }
                    else if(activity_escolherImagem.getnomeImg() == 0){

                        final AlertDialog.Builder builder = new AlertDialog.Builder(AddPerfilActivity.this);

                        builder.setTitle("ERRO");
                        builder.setMessage("Escolha uma imagem de perfil");
                        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                             dialog.dismiss();
                            }
                        });
                        AlertDialog dialog = builder.show();




                    }else{

                        Perfil perfil = new Perfil();
                        perfil.setNome(nome.getText().toString());
                        perfil.setImagem(activity_escolherImagem.getnomeImg());
                        perfil.setOpcao("opcao");




                        bd.addPerfil(perfil);
                        Toast.makeText(getBaseContext(), "Perfil inserido com sucesso.",
                                Toast.LENGTH_SHORT).show();

                        activity_escolherImagem.setBackupNome("");

                            Intent it = new Intent(AddPerfilActivity.this, PerfisActivity.class);
                            startActivity(it);
                            finish();





                    }

                   }
                catch (Exception e){

                    e.printStackTrace();

                }






            }


        });

    }

    public void abrirActivityEscolherImg(){

        activity_escolherImagem.setBackupNome(nome.getText().toString());
        Intent intent = new Intent(AddPerfilActivity.this, activity_escolherImagem.class);
        activity_escolherImagem.verificarIntent(1);
        startActivity(intent);
        finishTwo();

    }



    @Override
    public void onBackPressed() {

        Intent it = new Intent(AddPerfilActivity.this, PerfisActivity.class);
        startActivity(it);
        activity_escolherImagem.setNomeImg(0);
        activity_escolherImagem.setBackupNome("");
        finish();

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);

    }
    public void finishTwo(){
        super.finish();
    }

    public void verificarIntent(int verificar){

    }

    public static String getBackupNome() {
        return backupNome;
    }

    public static void setBackupNome(String backupNome) {
        AddPerfilActivity.backupNome = backupNome;
    }
}
