package com.matheasy.meuprojeto;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v7.content.res.AppCompatResources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;

public class PerfilAdapter extends ArrayAdapter<Perfil> {

    private final Context context;
    private final ArrayList<Perfil> elementos;
    private static FrameLayout frameLayout;
    private static int habilitar_desabilitar_excEdit ;
    private static int verificar;

    int[] imagensIds = {
            R.drawable.sem_perfil,
            R.drawable.escolher_imagem_rosto_1,
            R.drawable.escolher_imagem_rosto_2,





    };

    public PerfilAdapter(Context context, ArrayList<Perfil> elementos) {
        super(context, R.layout.linha, elementos);
        this.context = context;
        this.elementos = elementos;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View rowView = inflater.inflate(R.layout.linha, parent, false);
        TextView nome = (TextView) rowView.findViewById(R.id.perfis_txtNome);
        nome.setText(elementos.get(position).getNome());

        frameLayout = (FrameLayout) rowView.findViewById(R.id.layout_excluir_editar);

        if(getHabilitar_desabilitar_excEdit() == 1) {

            frameLayout.setVisibility(View.VISIBLE);


            if(verificar == 1){
                final ImageView excluirPerfil = (ImageView) rowView.findViewById(R.id.linha_excluirPerfil);
                frameLayout.setBackgroundResource(R.color.cinza_transparente);
                excluirPerfil.setBackgroundResource(R.drawable.botao_amarelo_redondo);
                Drawable drawable= AppCompatResources.getDrawable(context,R.mipmap.ic_white_round);
                excluirPerfil.setImageDrawable(drawable);
            }else if(verificar == 2){
                frameLayout.setBackgroundResource(R.color.preto_transparente);
                final ImageView excluirPerfil = (ImageView) rowView.findViewById(R.id.linha_excluirPerfil);
                excluirPerfil.setBackgroundResource(R.drawable.botao_vermelho_redondo);
            }

        }else{

            frameLayout.setVisibility(View.GONE);

        }


        CircleImageView img = (CircleImageView) rowView.findViewById(R.id.welcome_imagem_perfil);
        img.setImageResource(imagensIds[(elementos.get(position).getImagem())]);


        return rowView;
    }

    public static int getHabilitar_desabilitar_excEdit() {
        return habilitar_desabilitar_excEdit;
    }

    public static void setHabilitar_desabilitar_excEdit(int habilitar_desabilitar_excEdita, int ver) {
        habilitar_desabilitar_excEdit = habilitar_desabilitar_excEdita;
        verificar = ver;
    }



}






