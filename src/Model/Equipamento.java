package Model;

import Controller.Banco;

import java.sql.PreparedStatement;
import java.util.Date;

public class Equipamento extends Recurso {
    private String marca, modelo;

    public Equipamento(int id, String n, String m, String mo) {
        super(id, n, 0, 1);
        this.marca = m;
        this.modelo = mo;


    }

    public Equipamento(int id, String n, String m, String mo, int tipo, int situacaoEquipamento) {
        super(id, n, tipo, situacaoEquipamento);
        this.marca = m;
        this.modelo = mo;


    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }



    //---------------------------------------------- [SQL]



}
