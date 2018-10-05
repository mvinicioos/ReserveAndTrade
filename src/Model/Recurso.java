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


    //------------------------------------------------------ [GETS e SETS]

    /**
     * @return ID do objeto.
     */
    public int getCodigoDeId() {
        return codigoDeId;
    }

    /**
     *
     * @return String com o nome do objeto.
     */
    public String getNome() {
        return nome;
    }


    //------------------------------------------------------ [SQL]

    /**
     * O método verifica se o id do objeto já consta no banco de dados.
     * @return Número de id's já repetidos.
     */
    public int sqlContaIdRepetido(){
        Banco bancoDeDados = new Banco();
        String sql = "SELECT * FROM hpoa_recursos WHERE rec_id = ?";
        ResultSet result = null;
        int numeroLinhas = 0;

        try {
            if (bancoDeDados.iniciaConexaoComBanco()) {
                PreparedStatement stm = bancoDeDados.getConexao().prepareStatement(sql);

                stm.setString(1, Integer.toString(this.getCodigoDeId()));
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


    //------------------------------------------------------ [VALIDADORES]

    /**
     * Valida o nome do recurso.
     * @return TRUE em caso de válido
     */
    public boolean validaRecursoNome(){
        boolean validaNome = true;
        if(this.getNome().length() == 0){
            validaNome = false;
            System.out.println("Erro ! Nome inválido.");
        }
        return validaNome;
    }


    /**
     * @return TRUE em caso de ID válido
     */
    public boolean validaRecursoId(){
        boolean idValidado = true;
        if(this.sqlContaIdRepetido() > 0){
            idValidado = false;
            System.out.println("Erro ! Id já existe.");
        }
        return idValidado;
    }





}
