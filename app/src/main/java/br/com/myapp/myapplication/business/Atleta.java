package br.com.myapp.myapplication.business;

import com.google.gson.Gson;

/**
 * Created by Gustavo on 06/10/2016.
 */

public class Atleta {
    // Serviço: /atletas/pontuados
    private Integer id;
    private String apelido;
    private String pontuacao;
    private Scout scout;
    private String foto;
    private Integer posicao_id;
    private Integer clube_id;

    private Atleta() {

    }
}
