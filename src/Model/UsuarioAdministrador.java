package Model;

import Controller.Banco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Date;

import Model.Recurso;

public class UsuarioAdministrador extends Usuario {
    Banco bancoDeDados = new Banco();


    public UsuarioAdministrador(int id, String n, String sn, String eml, String pw, int tp) {
        super(id, n, sn, eml, pw, tp);
    }



    //----------------------------------------------- [MODELOS]
    private void cabecalhoTabelaRecursos(){
        System.out.println("ID\tNOME\t\tTIPO\tMARCA\tMODELO\tANDAR\tCORREDOR\tNUMERO\tSITUACAO");
    }
    private void divisoria(){
        System.out.println("--------------------------------------" +
                "---------------------------------------");
    }

    //----------------------------------------------- [SQL]
    //BUSCA

    /***
     * Realiza busca de um recurso pelo nome e imprime seu resultado.
     * @param nomeRecurso
     */
    public void sqlPesquisaRecurso(String nomeRecurso){
        Banco bancoDeDados = new Banco();
        String sql = "SELECT rec_id, rec_nome, tipo, equip_marca, equip_modelo, sala_andar, sala_corredor, sala_numero, recurso_ativo FROM hpoa_recursos WHERE rec_nome = ? ";
        ResultSet result = null;
        int numeroLinhas = 0;
        String dadoTemporario;
        try {
            if (bancoDeDados.iniciaConexaoComBanco()) {
                PreparedStatement stm = bancoDeDados.getConexao().prepareStatement(sql);

                stm.setString(1, nomeRecurso);
                result = stm.executeQuery();
            }
            //
            this.divisoria();
            this.cabecalhoTabelaRecursos();
            this.divisoria();

            while (result.next()) {
                if (Integer.parseInt(result.getString("tipo")) == 1) {
                    System.out.print(result.getString("rec_id") + "\t");
                    System.out.print(result.getString("rec_nome") + "\t\t");
                    System.out.print(result.getString("tipo") + "\t\t");
                    System.out.print("-" + "\t\t");
                    System.out.print("-" + "\t\t");
                    System.out.print(result.getString("sala_andar") + "\t\t");
                    System.out.print(result.getString("sala_corredor") + "\t\t\t");
                    System.out.print(result.getString("sala_numero") + "\t\t");
                    System.out.println(result.getString("recurso_ativo") + "\t");

                }else{
                    System.out.print(result.getString("rec_id") + "\t");
                    System.out.print(result.getString("rec_nome") + "\t\t");
                    System.out.print(result.getString("tipo") + "\t\t");
                    System.out.print(result.getString("equip_marca") + "\t");
                    System.out.print(result.getString("equip_modelo") + "\t");
                    System.out.print("-\t\t");
                    System.out.print("-\t\t\t");
                    System.out.print("-\t\t");
                    System.out.println(result.getString("recurso_ativo") + "\t");

                }
            }
            this.divisoria();
            bancoDeDados.encerraConexao();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /***
     * Realiza a busca de todos recursos na tabela e imprime em texto.
     */
    public void sqlPesquisaRecurso(){
        Banco bancoDeDados = new Banco();
        String sql = "SELECT rec_id, rec_nome, tipo, equip_marca, equip_modelo, sala_andar, sala_corredor, sala_numero, recurso_ativo FROM hpoa_recursos ";
        ResultSet result = null;
        int numeroLinhas = 0;
        String dadoTemporario;
        try {
            if (bancoDeDados.iniciaConexaoComBanco()) {
                PreparedStatement stm = bancoDeDados.getConexao().prepareStatement(sql);

                result = stm.executeQuery();
            }
            //
            this.divisoria();
            this.cabecalhoTabelaRecursos();
            this.divisoria();

            while (result.next()) {
                if (Integer.parseInt(result.getString("tipo")) == 1) {
                    System.out.print(result.getString("rec_id") + "\t");
                    System.out.print(result.getString("rec_nome") + "\t\t");
                    System.out.print(result.getString("tipo") + "\t\t");
                    System.out.print("-" + "\t\t");
                    System.out.print("-" + "\t\t");
                    System.out.print(result.getString("sala_andar") + "\t\t");
                    System.out.print(result.getString("sala_corredor") + "\t\t\t");
                    System.out.print(result.getString("sala_numero") + "\t\t");
                    System.out.println(result.getString("recurso_ativo") + "\t");

                }else{
                    System.out.print(result.getString("rec_id") + "\t");
                    System.out.print(result.getString("rec_nome") + "\t\t");
                    System.out.print(result.getString("tipo") + "\t\t");
                    System.out.print(result.getString("equip_marca") + "\t");
                    System.out.print(result.getString("equip_modelo") + "\t");
                    System.out.print("-\t\t");
                    System.out.print("-\t\t\t");
                    System.out.print("-\t\t");
                    System.out.println(result.getString("recurso_ativo") + "\t");

                }
            }
            this.divisoria();
            bancoDeDados.encerraConexao();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    //INSERÇÃO
    /**
     * SQL para inserir os dados no banco de dados.
     * @param novoUsuario
     * @return TRUE em caso de sucesso
     */
    private boolean sqlInsereUsuario(Usuario novoUsuario){
        boolean usuarioInserido = true;
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

    //----------------------------------------------- [VALIDAÇÃO DOS DADOS E INSERÇÃO NO BANCO]

    /***
     * Valida os parâmetros e insere o cadastro da sala no banco.
     * @param id
     * @param nome
     * @param andar
     * @param corredor
     * @param numeroSala
     * @return
     */
    public boolean cadastrarSala(int id, String nome, int andar, int corredor, int numeroSala){

        Sala novaSala = new Sala(id, nome, andar, corredor, numeroSala);
        boolean cadastroAutorizado = true;

        if(!novaSala.validaRecursoId()){
            cadastroAutorizado = false;
        }else if(!novaSala.validaRecursoNome()){
            cadastroAutorizado = false;
        }else if(!novaSala.validaSalaAndar()){
            cadastroAutorizado = false;
        }else if (!novaSala.validaSalaCorredor()){
            cadastroAutorizado = false;
        }else if (!novaSala.validaSalaNumero()){
            cadastroAutorizado = false;
        }else if(!novaSala.validaDuplicatas()){
            cadastroAutorizado = false;
        }

        //Insere no banco
        if(cadastroAutorizado == true){
            novaSala.sqlInsereSala();
            System.out.println("Sala cadastrada com sucesso !");

        }

        return cadastroAutorizado;
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

        if(!novoEquipamento.validaRecursoId()){
            cadastroAutorizado = false;
        }else if(!novoEquipamento.validaRecursoNome()){
            cadastroAutorizado = false;
        }else if(!novoEquipamento.validaMarcaEquipamento()){
            cadastroAutorizado = false;
        }else if(!novoEquipamento.validaModeloEquipamento()){
            cadastroAutorizado = false;
        }

        //Insere no banco
        if(cadastroAutorizado == true){
            novoEquipamento.sqlInsereEquipamento();
            System.out.println("Equipamento cadastrado com sucesso !");

        }

        return cadastroAutorizado;
    }


    /**
     * @apiNote Realiza o cadastro de um usuário. O parâmetro tipo define se é administrador
     * ou usuário comum.
     * @param id
     * @param nome
     * @param sobrenome
     * @param email
     * @param senha
     * @param tipo
     * @return
     */
    public boolean cadastrarUsuario(int id, String nome, String sobrenome, String email, String senha, int tipo){
        boolean usuarioCadastrado = true;
        if(tipo == 1){
            UsuarioAdministrador novoAdministrador = new UsuarioAdministrador(id, nome, sobrenome, email, senha, tipo);
            if(this.sqlInsereUsuario(novoAdministrador) == false){
                usuarioCadastrado = false;
            }
        }else if(tipo == 0){
            //Instancia um novo usuario
            Usuario novoUsuario = new Usuario(id, nome, sobrenome, email, senha, tipo);
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


