package Model;

import Controller.Banco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;

public abstract class Recurso {
    private int codigoDeId;
    private String nome;

    public Recurso(int id, String n) {
        this.codigoDeId = id;
        this.nome = n;
    }



    public int getIdentificacao() {
        return codigoDeId;
    }

    public String getNome() {
        return nome;
    }

    public boolean validaRecursoNome(){
        boolean validaNome = true;
        if(this.getNome().length() == 0){
            validaNome = false;
            System.out.println("Erro ! Nome inválido.");
        }
        return validaNome;
    }



}
