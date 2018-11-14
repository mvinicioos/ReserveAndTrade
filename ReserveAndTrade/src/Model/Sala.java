package Model;

import Controller.Banco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Sala extends Recurso {
    private int andar, corredor, sala;

    public Sala(int id, String n, int a, int c, int s) {
        super(id, n,1,1);
        this.andar = a;
        this.corredor = c;
        this.sala = s;
    }

    public Sala(int id, String n, int a, int c, int s,int tipo, int situacaoSala){
        super(id, n, tipo, situacaoSala);
        this.andar = a;
        this.corredor = c;
        this.sala = s;
    }


    public int getAndar() {
        return andar;
    }

    public int getCorredor() {
        return corredor;
    }

    public int getSala() {
        return sala;
    }


    //--------------------------------------------------------[SQL]






}
