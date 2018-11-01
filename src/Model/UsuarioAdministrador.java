package Model;

import Controller.Banco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Date;

import Model.Recurso;

public class UsuarioAdministrador extends Usuario {
    Banco bancoDeDados = new Banco();


    public UsuarioAdministrador(int id, String n, String sn, String eml, String pw, int tp) {
        super(id, n, sn, eml, pw, tp);
    }





    //----------------------------------------------- [PRIVILÉGIOS SOBRE RECURSOS]

    /***
     * Valida os parâmetros e insere o cadastro da sala no banco.
     * @param id
     * @param nome
     * @param andar
     * @param corredor
     * @param numeroSala
     * @return
     */
    public String cadastrarSala(int id, String nome, int andar, int corredor, int numeroSala){

        Sala novaSala = new Sala(id, nome, andar, corredor, numeroSala);
        boolean cadastroAutorizado = novaSala.validaSala();
        String mensagemRetorno = "Cadastro realizado com sucesso !";

        //Insere no banco
        if(cadastroAutorizado == true){
            novaSala.sqlInsereSala();
        }else{
            mensagemRetorno = "Sala duplicada !";
        }

        return mensagemRetorno;
    }

    /**
     * Valida os parâmetros e insere o parâmetro no banco de dados.
     * Retorna TRUE para o caso de o cadastro estar ok.
     * @param id
     * @param nome
     * @param marca
     * @param modelo
     * @return
     */
    public boolean cadastrarEquipamento(int id, String nome, String marca, String modelo){

        Equipamento novoEquipamento = new Equipamento(id, nome, marca, modelo, null);
        boolean cadastroAutorizado = true;

        if(!novoEquipamento.validaRecursoNome()){
            cadastroAutorizado = false;
        }else if(!novoEquipamento.validaMarca()){
            cadastroAutorizado = false;
        }else if(!novoEquipamento.validaModelo()){
            cadastroAutorizado = false;
        }

        //Insere no banco
        if(cadastroAutorizado == true){
            novoEquipamento.sqlInsereEquipamento();
            System.out.println("Equipamento cadastrado com sucesso !");

        }

        return cadastroAutorizado;
    }

    public boolean inativarRecurso(int id){
        boolean equipamentoInativado = false;
        Banco bancoDeDados = new Banco();
        //Verifica existência

        //Inativa
        try{
            if(bancoDeDados.iniciaConexaoComBanco()) {
                PreparedStatement stm2 = bancoDeDados.getConexao().prepareStatement("" +
                        "UPDATE hpoa_recursos " +
                        "SET recurso_ativo = 0 " +
                        "WHERE rec_id = ? ,");
                stm2.setInt(1, id);

                stm2.executeUpdate();
                stm2.close();
                equipamentoInativado = true;
            }
            bancoDeDados.encerraConexao();
        }catch (Exception e) {
            e.printStackTrace();
        }

        return equipamentoInativado;
    }


    //----------------------------------------------- [PRIVILÉGIOS SOBRE USUÁRIOS]
    /**
     * SQL para inserir um novo usuário
     * @param novoUsuario
     * @return TRUE em caso de sucesso
     */
    private boolean sqlInsereUsuario(Usuario novoUsuario){
        boolean usuarioInserido = true;
        Banco bancoDeDados = new Banco();

        try{
            if(bancoDeDados.iniciaConexaoComBanco()) {
                PreparedStatement stm2 = bancoDeDados.getConexao().prepareStatement("INSERT INTO hpoa_usuarios VALUES (?, ?, ?, ?, ?, ?, ?)");
                stm2.setString(1, null);
                stm2.setString(2, novoUsuario.getNome());
                stm2.setString(3, novoUsuario.getSobrenome());
                stm2.setString(4, novoUsuario.getEmail());
                stm2.setString(5, novoUsuario.getSenha());
                stm2.setString(6, Integer.toString(novoUsuario.getTipo()));
                stm2.setString(7, "1"); //Garante que usuário já estará ativo no sistema.
                stm2.executeUpdate();
                stm2.close();
            }
            bancoDeDados.encerraConexao();
        }catch (Exception e) {
            usuarioInserido = false;
            e.printStackTrace();
        }

        return usuarioInserido;
    }

    /**
     *
     * @param nome
     * @param sobrenome
     * @param email
     * @param senha
     * @param tipo
     * @return
     */
    public boolean cadastrarUsuario(String nome, String sobrenome, String email, String senha, int tipo){
        boolean usuarioCadastrado = true;
        final int idSimbolico = 0;

        if(tipo == 1){
            UsuarioAdministrador novoAdministrador = new UsuarioAdministrador(idSimbolico, nome, sobrenome, email, senha, tipo);
            if(this.sqlInsereUsuario(novoAdministrador) == false){
                usuarioCadastrado = false;
            }
        }else if(tipo == 0){
            //Instancia um novo usuario
            Usuario novoUsuario = new Usuario(idSimbolico, nome, sobrenome, email, senha, tipo);
            if(this.sqlInsereUsuario(novoUsuario) == false){
                usuarioCadastrado = false;
            }
        }else{
            usuarioCadastrado = false;
            System.out.println("Tipo de usuario invalido !");
        }

        return usuarioCadastrado;
    }

}


