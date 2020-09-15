package com.matheasy.meuprojeto;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.matheasy.meuprojeto.programacao.niveis.frag_niveis;
import java.util.List;

public class Activity_escolherDificuldade extends AppCompatActivity {




    private EditText edtPesquisar;
    private int id;
    private Button btnPesquisar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escolher_dificuldade);


        if (savedInstanceState == null) {
          // adicionar o fragmento inicial
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.escolherDificuldade_frameLayout, new frag_niveis())
                    .commit();
        }


        SharedPreferences prefs= getSharedPreferences("preferencias", Context.MODE_PRIVATE);
        id = (prefs.getInt("ID", -1));



        edtPesquisar = (EditText) findViewById(R.id.activity_dificuldade_txtPesquisar);
        btnPesquisar = (Button) findViewById(R.id.escolherDificuldade_btnPesquisar);
        pesquisarAula(edtPesquisar);

        btnPesquisar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });







    }








    private void pesquisarAula(final EditText editText){

        editText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText.setFocusable(true);
                editText.setFocusable(View.FOCUSABLE);
                editText.setFocusable(View.FOCUSABLE_AUTO);
            }
        });

    }


    public void onBackPressed() {

        Intent intent = new Intent(Activity_escolherDificuldade.this, Welcome_Activity.class);
        intent.putExtra("ID", id);
        startActivity(intent);
        finish();

    }





}
