package com.matheasy.meuprojeto;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.michaldrabik.tapbarmenulib.TapBarMenu;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;
import io.realm.Realm;

public class PerfisActivity extends AppCompatActivity {


    private BDSQLiteHelper bd;
    ArrayList<Perfil> listaPerfis;
    private ListView lista;
    private static int verificarStatus = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfis);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        bd = new BDSQLiteHelper(this);







        final TapBarMenu tapBarMenu = (TapBarMenu) findViewById(R.id.tapBarMenu);

        tapBarMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tapBarMenu.toggle();
            }
        });

        final ImageView btnExcluir = (ImageView) findViewById(R.id.actPerfis_item_excluir);

        final ImageView btnEditar = (ImageView) findViewById(R.id.actPerfis_item_edt);

        final ImageView excluirPerfil = (ImageView) findViewById(R.id.linha_excluirPerfil);




        btnEditar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {




                if (verificarStatus == 0 || verificarStatus == 20){

                    btnExcluir.setImageResource(R.mipmap.ic_excluir_round);

                    btnEditar.setImageResource(R.mipmap.ic_editar_verde_round);

                    PerfilAdapter.setHabilitar_desabilitar_excEdit(1, 1);



                    PerfilAdapter adapter = new PerfilAdapter(PerfisActivity.this, listaPerfis);
                    lista.setAdapter(adapter);

                    verificarStatus = 10;



                }else{

                    PerfilAdapter adapter = new PerfilAdapter(PerfisActivity.this, listaPerfis);
                    lista.setAdapter(adapter);

                    verificarStatus = 0;


                    PerfilAdapter.setHabilitar_desabilitar_excEdit(0,2);

                    btnEditar.setImageResource(R.mipmap.ic_editar_2_round);



                }

            }
        });




        btnExcluir.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("ResourceAsColor")
            @Override
            public void onClick(View v) {



                if (verificarStatus == 0 || verificarStatus == 10){

                    btnEditar.setImageResource(R.mipmap.ic_editar_2_round);

                    btnExcluir.setImageResource(R.mipmap.ic_excluir_verde_round);

                    PerfilAdapter.setHabilitar_desabilitar_excEdit(1,2);

                    PerfilAdapter adapter = new PerfilAdapter(PerfisActivity.this, listaPerfis);
                    lista.setAdapter(adapter);

                    verificarStatus = 20;



                }else{

                    PerfilAdapter adapter = new PerfilAdapter(PerfisActivity.this, listaPerfis);
                    lista.setAdapter(adapter);


                    PerfilAdapter.setHabilitar_desabilitar_excEdit(0, 2);
                    btnExcluir.setImageResource(R.mipmap.ic_excluir_round);

                    verificarStatus = 0;

                }


            }
        });


    }



    public void abrirAddPerfil(View view){

        activity_escolherImagem.setNomeImg(0);

        ImageView imageView = (ImageView) findViewById(R.id.actPerfis_item_add);
        imageView.setImageResource(R.mipmap.ic_add_verde_round);

        verificarStatus = 0;
        Intent it = new Intent(this, AddPerfilActivity.class);
        startActivity(it);
        PerfilAdapter.setHabilitar_desabilitar_excEdit(0,0);
        finish();

        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

    }



    public void excluirPerfil(View view){ }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);

        builder.setTitle("Sair");
        builder.setMessage("Você deseja realmente sair?");

        builder.setPositiveButton("SAIR", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                PerfilAdapter.setHabilitar_desabilitar_excEdit(0,0);
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



    @Override
    protected void onStart() {
        super.onStart();
        lista = (ListView) findViewById(R.id.lvPerfis);
        listaPerfis = bd.getAllPerfis();
        PerfilAdapter adapter = new PerfilAdapter(this, listaPerfis);
        lista.setAdapter(adapter);





        if(listaPerfis.isEmpty()){

            Intent it = new Intent(PerfisActivity.this, ActivitySemPerfil.class);
            startActivity(it);
            finish();

        }

        lista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {


                if(verificarStatus == 0){

                    ImageView img = (ImageView) findViewById(R.id.activity_perfis_atualizando);
                    img.setVisibility(View.VISIBLE);

                    Glide.with(PerfisActivity.this).load(R.raw.atualizando).into(img);

                    FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frame_layout_actPerfis);
                    frameLayout.setBackgroundResource(R.color.cinza_transparente);


                    final Intent intent = new Intent(PerfisActivity.this, activity_escolher_opcao.class);
                    intent.putExtra("ID", listaPerfis.get(position).getId());

                    //salva o ID como preferencia
                    SharedPreferences prefs = getSharedPreferences("preferencias",Context.MODE_PRIVATE);
                    SharedPreferences.Editor ed= prefs.edit();
                    ed.putInt("ID", listaPerfis.get(position).getId());
                    ed.apply();



                    new Handler().postDelayed(new Runnable() {

                        @Override
                        public void run() {

                            startActivity(intent);
                            finish();
                        }
                    }, 400);

                    ;









                }else if(verificarStatus == 10) {
                    Intent it = new Intent(PerfisActivity.this, EdtPerfilActivity.class);
                    it.putExtra("ID", listaPerfis.get(position).getId());
                    startActivity(it);
                    PerfilAdapter.setHabilitar_desabilitar_excEdit(0, 0);
                    verificarStatus = 0;
                    finish();

                }else if(verificarStatus == 20){

                    AlertDialog.Builder builder = new AlertDialog.Builder(PerfisActivity.this);

                    builder.setTitle("Confirmação");
                    builder.setMessage("Você deseja realmente apagar esse perfil?");

                    builder.setPositiveButton("CONFIRMAR", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            final Perfil perfil = bd.getPerfil(listaPerfis.get(position).getId());
                            bd.deletePerfil(perfil);
                            Intent it = new Intent(PerfisActivity.this, PerfisActivity.class);
                            startActivity(it);
                            finish();
                            PerfilAdapter.setHabilitar_desabilitar_excEdit(0,0);
                            verificarStatus = 0;
                            overridePendingTransition(R.anim.slide_in_left_short, R.anim.slide_out_left_short);



                        }
                    });

                    builder.setNegativeButton("CANCELAR", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });

                    AlertDialog dialog = builder.show();



                }
            }
        });

    }

}
