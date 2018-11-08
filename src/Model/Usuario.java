package Model;

import Controller.Banco;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class Usuario {
    private int identificacao;
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    private int tipo;
    private ArrayList<Reserva> reservas;

    public Usuario(int id, String n, String sn, String eml, String pw, int tp) {
        this.identificacao = id;
        this.nome = n;
        this.reservas = new ArrayList<>();
        this.sobrenome = sn;
        this.email = eml;
        this.senha = pw;
        this.tipo = tp;
    }

    public String getNome() {
        return this.nome;
    }

    public ArrayList<Reserva> getReservas() {
        return this.reservas;
    }

    public int getIdentificacao() {
        return identificacao;
    }

    public String getSobrenome(){return this.sobrenome;}

    public String getEmail(){return this.email;}

    public String getSenha(){return this.senha;}

    public int getTipo(){return this.tipo;}

    /**
     * Verifica se o próprio usuário é administrador
     * @return
     */
    public boolean getTipoUsuario(){
        boolean tipoAdministrador = false;

        if(this.getTipo() == 1){
            tipoAdministrador = true;
        }

        return tipoAdministrador;
    }





}
