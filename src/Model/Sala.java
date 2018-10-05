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
                PreparedStatement stm2 = bancoDeDados.getConexao().prepareStatement("INSERT INTO hpoa_recursos VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                stm2.setString(1, Integer.toString(this.getCodigoDeId()));
                stm2.setString(2, this.getNome()); //Nome
                stm2.setString(3, "1"); //tipo
                stm2.setString(4, null); //e_marca
                stm2.setString(5, null); //e_modelo
                stm2.setString(6, null); //e_garantia
                stm2.setString(7, Integer.toString(this.getAndar())); //sala_andar
                stm2.setString(8, Integer.toString(this.getCorredor())); //sala_corredor
                stm2.setString(9, Integer.toString(this.getSala())); //sala_numero
                stm2.setString(10,"1"); //recurso ativo
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
    public int sqlSalaDuplicada(){
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
        return numeroLinhas;
    }


    //--------------------------------------------------------[VALIDADORES]

    /**
     * VALIDA:
     * > Andar não pode ser um número negativo.
     * @return TRUE em caso de válido.
     */
    public boolean validaSalaAndar(){
        boolean andarValidado = true;

        if (this.getAndar() < 0){
            andarValidado = false;
            System.out.println("Erro ! Andar Inválido.");
        }

        return andarValidado;
    }

    /**
     * VALIDA:
     * > Corredor não pode ser um número negativo.
     * @return
     */
    public boolean validaSalaCorredor(){
        boolean validaCorredor = true;
        if(this.getCorredor() < 0){
            validaCorredor = false;
            System.out.println("Erro ! Número do corredor inválido.");
        }
        return validaCorredor;
    }

    /**
     * VALIDA:
     * > Número da sala não pode ser um valor negativo.
     * @return
     */
    public boolean validaSalaNumero(){
        boolean validaNumero = true;
        if(this.getSala() < 0){
            validaNumero = false;
            System.out.println("Erro ! Número da sala inválido.");
        }
        return validaNumero;
    }

    /**
     * VALIDA:
     * > Aplica a sqlSalaDuplicata que não pode ter um valor de retorno maior que zero.
     * @return
     */
    public boolean validaDuplicatas(){
        boolean semDuplicata = true;
        if(this.sqlSalaDuplicada() > 0){
            semDuplicata = false;
            System.out.println("Erro! Registro já existe no banco.");
        }
        return semDuplicata;
    }

}
