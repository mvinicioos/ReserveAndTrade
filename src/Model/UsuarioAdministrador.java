package Model;

import Controller.Banco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import Controller.BancoDAO;
import Model.Recurso;

public class UsuarioAdministrador extends Usuario {
    Banco bancoDeDados = new Banco();


    public UsuarioAdministrador(int id, String n, String sn, String eml, String pw, int tp) {
        super(id, n, sn, eml, pw, tp);
    }





    //----------------------------------------------- [PRIVILÉGIOS SOBRE RECURSOS]

    /***
     * Valida os parâmetros e insere o cadastro da sala no banco.
     * @param nome
     * @param andar
     * @param corredor
     * @param numeroSala
     * @return
     */
    public String cadastrarSala(String nome, int andar, int corredor, int numeroSala){
        BancoDAO bancoDAO = new BancoDAO();
        String mensagemRetorno;

        mensagemRetorno = bancoDAO.insereSala(nome,andar,corredor,numeroSala);

        return mensagemRetorno;
    }

    /**
     * Valida os parâmetros e insere o parâmetro no banco de dados.
     * Retorna TRUE para o caso de o cadastro estar ok.
     * @param nome
     * @param marca
     * @param modelo
     * @return
     */
    public String cadastrarEquipamento(String nome, String marca, String modelo){
        String msgRetorno = "";
        BancoDAO bancoDAO = new BancoDAO();
        msgRetorno = bancoDAO.insereEquipamento(nome, marca, modelo);


        return msgRetorno;
    }

    public String atualizarSala(int id, String nome, int andar, int corredor, int numeroSala){
        String msgRetorno = "";
        BancoDAO bancoDAO = new BancoDAO();
        if(bancoDAO.atualizaSala(id, nome, andar, corredor, numeroSala)){
            msgRetorno = "Dados Atualizados";
        }else{
            msgRetorno = "Falha ao atualizar dados";
        }

        return msgRetorno;
    }

    public String atualizarEquipamento(int id, String nome, String marca, String modelo){
        String msgRetorno = "";
        BancoDAO bancoDAO = new BancoDAO();
        if(bancoDAO.atualizaEquipamento(id, nome,marca,modelo)){
            msgRetorno = "Dados Atualizados";
        }else{
            msgRetorno = "Falha ao atualizar dados";
        }

        return msgRetorno;
    }

    public String removerRecurso(int id){
        String msgRetorno = "";
        System.out.println("user remover rec");
        BancoDAO bancoDAO = new BancoDAO();

        if(bancoDAO.inativarRecurso(id)){
            msgRetorno = "Recurso removido";
        }else{
            msgRetorno = "Falha ao remover recurso";
        }
        return msgRetorno;

    }

}


