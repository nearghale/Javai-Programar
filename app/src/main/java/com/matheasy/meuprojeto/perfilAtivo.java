package com.matheasy.meuprojeto;

public class perfilAtivo {

    private String nome, pontuacao, nivel;
    private int imagem;

    public perfilAtivo(String nome, String pontuacao, String nivel){

        this.nome = nome;
        this.pontuacao = pontuacao;
        this.nivel = nivel;

    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getPontuacao() {
        return pontuacao;
    }

    public void setPontuacao(String pontuacao) {
        this.pontuacao = pontuacao;
    }

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    public int getImagem() {
        return imagem;
    }

    public void setImagem(int imagem) {
        this.imagem = imagem;
    }
}
