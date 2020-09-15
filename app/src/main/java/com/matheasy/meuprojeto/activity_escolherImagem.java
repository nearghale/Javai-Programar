package com.matheasy.meuprojeto;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import de.hdodenhof.circleimageview.CircleImageView;

public class activity_escolherImagem extends AppCompatActivity {

    private static int nomeImg;
    private static int verificarInt;
    private static String backupNome = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolher_imagem);


        ImageView rosto1 = (ImageView) findViewById(R.id.escolher_imagem_rosto1);
        ImageView rosto2 = (ImageView) findViewById(R.id.escolher_imagem_rosto2);



        salvar(rosto1,1);
        salvar(rosto2,2);


    }

    public void salvar(ImageView imageview, final int img){

        imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                salvarImg(img);
            }
        });

    }

    public void salvarImg(int nome){

            if(verificarInt == 1) {
                Intent it = new Intent(activity_escolherImagem.this, AddPerfilActivity.class);
                nomeImg = nome;
                AddPerfilActivity.setBackupNome(getBackupNome());
                startActivity(it);
                finish();
            }else if (verificarInt == 2){

                EdtPerfilActivity.setReceberImagem(nome);
                EdtPerfilActivity.img.setImageResource(EdtPerfilActivity.imagensIds[nome]);
                finish();
            }


    }



    public static int getnomeImg() {
        return nomeImg;
    }

    public static void setNomeImg(int nome){

        nomeImg = nome;

    }

    @Override
    public void finish(){
        super.finish();
    }

   public static void verificarIntent(int intent){

        verificarInt = intent;

   }

    public static String getBackupNome() {
        return backupNome;
    }

    public static void setBackupNome(String backupNome) {
        activity_escolherImagem.backupNome = backupNome;
    }

    @Override
    public void onBackPressed() {

        if(verificarInt == 1) {
            Intent it  = new Intent(activity_escolherImagem.this, AddPerfilActivity.class);
            startActivity(it);

            finish();
        }else if (verificarInt == 2){
            Intent it  = new Intent(activity_escolherImagem.this, EdtPerfilActivity.class);
            startActivity(it);
            finish();
        }

    }

}
