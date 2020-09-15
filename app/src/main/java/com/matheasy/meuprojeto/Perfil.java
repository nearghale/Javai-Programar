package com.matheasy.meuprojeto;

import android.graphics.Bitmap;

import io.realm.RealmObject;

public class Perfil extends RealmObject {

    private int id;
    private String nome;
    private String opcao;
    private int imagem;


    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getOpcao() {
        return opcao;
    }

    public void setOpcao(String opcao) {
        this.opcao = opcao;
    }
}
