package Model;

import Controller.Banco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class Sala extends Recurso {
    private int andar, corredor, sala;

    public Sala(int id, String n, int a, int c, int s) {
        super(id, n);
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

    /**
     * Apenas insere no banco de dados, sem validar dados.
     * @return TRUE em caso de inserção válida.
     */
    public boolean sqlInsereSala() {
        Banco bancoDeDados = new Banco();
        boolean equipamentoInserido = true;

        try{
            if(bancoDeDados.iniciaConexaoComBanco()) {
                PreparedStatement stm2 = bancoDeDados.getConexao().prepareStatement("INSERT INTO hpoa_recursos VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                //stm2.setString(1, null); //AUTO_INCREMENT
                stm2.setString(1, this.getNome()); //Nome
                stm2.setString(2, "1"); //tipo
                stm2.setString(3, null); //e_marca
                stm2.setString(4, null); //e_modelo
                stm2.setString(5, null); //e_garantia
                stm2.setString(6, Integer.toString(this.getAndar())); //sala_andar
                stm2.setString(7, Integer.toString(this.getCorredor())); //sala_corredor
                stm2.setString(8, Integer.toString(this.getSala())); //sala_numero
                stm2.setString(9,"1"); //recurso ativo
                stm2.executeUpdate();
                stm2.close();
            }
            bancoDeDados.encerraConexao();
        }catch (Exception e) {
            equipamentoInserido = false;
            e.printStackTrace();
        }

        return equipamentoInserido;
    }

    /**
     * Uma sala duplicada é definida quando há uma igualdade em
     * NOME, ANDAR, CORREDOR,
     * @return O número de salas duplicadas.
     */
    private boolean sqlSalaDuplicada(){
        boolean salaDuplicada = false;

        Banco bancoDeDados = new Banco();
        String sql = "SELECT * FROM hpoa_recursos WHERE rec_nome = ? AND sala_andar = ? AND sala_corredor = ? AND sala_numero = ?";
        ResultSet result = null;
        int numeroLinhas = 0;
        try {
            if (bancoDeDados.iniciaConexaoComBanco()) {
                PreparedStatement stm = bancoDeDados.getConexao().prepareStatement(sql);

                stm.setString(1, this.getNome());
                stm.setString(2, Integer.toString(this.getAndar()));
                stm.setString(3, Integer.toString(this.getCorredor()));
                stm.setString(4, Integer.toString(this.getSala()));
                result = stm.executeQuery();
            }
            while (result.next()){
                numeroLinhas++;
            }
            bancoDeDados.encerraConexao();
        }catch (Exception e){
            e.printStackTrace();
        }

        if(numeroLinhas > 0){
            salaDuplicada = true;
        }

        return salaDuplicada;
    }



    //Aplica todos validadores anteriores
    public boolean validaSala(){
        boolean cadastroAutorizado = true;
        if(this.sqlSalaDuplicada()){
            cadastroAutorizado = false;
        }
        return cadastroAutorizado;
    }



}
