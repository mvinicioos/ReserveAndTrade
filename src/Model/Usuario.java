package Model;

import java.util.ArrayList;

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
}
