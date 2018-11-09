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

    /**
     * Verifica se um usuário qualquer é administrador ou não
     * @param id
     * @return
     */
    public boolean verificaPermissoes(int id){
        Banco bancoDeDados        = new Banco();
        boolean tipoAdministrador = false;
        String sql = "SELECT *" +
                "       FROM hpoa_usuarios " +
                "       WHERE usuario_id = ?";
        ResultSet result = null;

        try {
            if (bancoDeDados.iniciaConexaoComBanco()) {
                PreparedStatement stm = bancoDeDados.getConexao().prepareStatement(sql);

                stm.setString(1, Integer.toString(id));
                result = stm.executeQuery();


                //Cria a instancia do objeto de acordo com o id selecionado
                if (result.next()) {
                    if (Integer.parseInt(result.getString("usuario_situacao")) == 1) {
                        if (Integer.parseInt(result.getString("usuario_tipo")) == 1) {

                            tipoAdministrador = true;

                        }
                    }
                }
            }
            result.close();
            bancoDeDados.encerraConexao();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tipoAdministrador;
    }

}


