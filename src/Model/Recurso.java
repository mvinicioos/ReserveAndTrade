package Model;

import Controller.Banco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class Recurso {
    private final int recursoAtivo = 1;

    private int codigoDeId;
    private String nome;
    private int situacao;
    private int tipo;
    public Recurso(int id, String n, int tipoRec, int situacaoRecurso) {
        this.codigoDeId = id;
        this.nome = n;
        this.tipo = tipoRec;
        this.situacao = situacaoRecurso;

    }


    public int getId(){return this.codigoDeId;}
    public int getIdentificacao() {
        return codigoDeId;
    }

    public String getNome() {
        return nome;
    }

    public int getTipo(){return this.tipo;}
    public int getSituacao(){return this.situacao;}




}
