package br.com.myapp.myapplication;

import com.google.gson.Gson;

/**
 * Created by Gustavo on 06/10/2016.
 */

public class Atleta {
//    private Integer rodada;
//    private Integer total_atletas;

    private Integer rodada_atual;
    private Integer status_mercado;

    private Atleta() {

    }

//    private Integer mId;
//    private String mApelido;
//    private String mPontuacao;
////    private String[] mScout;
//    private String mFoto;
//    private Integer mPosicao_Id;
//    private Integer mClube_Id;

    public String toString() {
        return "rodada_atual: " + rodada_atual + ", status_mercado: " + status_mercado;
    }
}
