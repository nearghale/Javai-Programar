package com.matheasy.meuprojeto.programacao;

public class Conteudo {

    private double conteudo;

    public Conteudo(double conteudo){
        this.conteudo = conteudo;
    }

    public void setConteudoPagina2(){

        this.conteudo /= 2;
    }

    public String getConteudo(){

        if (conteudo == 1) {

            return String.format("Neste Capítulo, você verá:\n" +
                    "Os emocionantes desenvolvimentos na área de T.I\n" +
                    "Conhecer a hierarquia de dados.\n");

        }else if (conteudo == 0.5){

            return String.format("Conhecerá alguns tipos de \n" +
                    "linguagem  de programação.\n" +
                    "Entender Como o Java e outras linguagens são importantes.\n"+
                    "Programação orientada a objetos.");


        }

        return String.format("");

    }

}
