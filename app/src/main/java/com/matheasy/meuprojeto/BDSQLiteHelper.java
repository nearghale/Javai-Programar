package com.matheasy.meuprojeto;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.matheasy.meuprojeto.Perfil;

import java.util.ArrayList;

public class BDSQLiteHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "PerfilDB";
    private static final String TABELA_PERFIS = "perfis";
    private static final String NOME = "nome";
    private static final String IMAGEM = "imagem";
    private static final String OPCAO = "opcao";
    private static final String ID = "id";

    private static final String[] COLUNAS = {ID, NOME, IMAGEM, OPCAO};



    public BDSQLiteHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {


        String CREATE_TABLE = "CREATE TABLE perfis ("+
                "id INTEGER PRIMARY KEY AUTOINCREMENT,"+
                "Nome TEXT,"+
                "Imagem TEXT,"+
                "Opcao TEXT)";

        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS perfis");
        this.onCreate(db);

    }

    public void addPerfil(Perfil perfil) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOME, perfil.getNome());
        values.put(IMAGEM, perfil.getImagem());
        values.put(OPCAO, perfil.getOpcao());
        db.insert(TABELA_PERFIS, null, values);
        db.close();
    }

    public Perfil getPerfil(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABELA_PERFIS, // a. tabela
                COLUNAS, // b. colunas
                " id = ?", // c. colunas para comparar
                new String[] { String.valueOf(id) }, // d. parâmetros
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit
        if (cursor == null) {
            return null;
        } else {
            cursor.moveToFirst();
            Perfil perfil = cursorToPerfil(cursor);
            return perfil;
        }
    }

    private Perfil cursorToPerfil(Cursor cursor) {
        Perfil perfil = new Perfil();
        perfil.setId(Integer.parseInt(cursor.getString(0)));
        perfil.setNome(cursor.getString(1));
        perfil.setImagem(Integer.parseInt(cursor.getString(2)));
        perfil.setOpcao(cursor.getString(3));
        return perfil;
    }

    public ArrayList<Perfil> getAllPerfis() {
        ArrayList<Perfil> listaPerfis = new ArrayList<Perfil>();

        String query = "SELECT * FROM " + TABELA_PERFIS;

        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()) {
            do {
                Perfil perfil = cursorToPerfil(cursor);
                listaPerfis.add(perfil);
            } while (cursor.moveToNext());
        }
        return listaPerfis;
    }

    public int updatePerfil(Perfil perfil) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NOME, perfil.getNome());
        values.put(IMAGEM, perfil.getImagem());
        values.put(OPCAO, perfil.getOpcao());

        int i = db.update(TABELA_PERFIS, //tabela
                values, // valores
                ID+" = ?", // colunas para comparar
                new String[] { String.valueOf(perfil.getId()) }); //parâmetros
        db.close();
        return i; // número de linhas modificadas
    }

    public int deletePerfil(Perfil perfil) {
        SQLiteDatabase db = this.getWritableDatabase();
        int i = db.delete(TABELA_PERFIS, //tabela
                ID+" = ?", // colunas para comparar
                new String[] { String.valueOf(perfil.getId()) });
        db.close();
        return i; // número de linhas excluídas
    }


}
