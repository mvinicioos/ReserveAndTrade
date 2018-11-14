package Controller;

import Model.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BancoDAO {

    /**
     * Retorna um objeto do tipo Sala, ou Equipamento.
     * @param id
     * @return
     */
    public List<Recurso> pesquisaRecurso(int id){
        Banco bancoDeDados = new Banco();
        bancoDeDados.iniciaConexaoComBanco();
        List<Recurso> recursos = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement stm = null;
        String sql = "SELECT * FROM hpoa_recursos WHERE rec_id = ? AND recurso_ativo = 1";

        try {
            stm = bancoDeDados.getConexao().prepareStatement(sql);
            stm.setString(1, Integer.toString(id));
            rs = stm.executeQuery();
            while (rs.next()){
                if(rs.getInt("tipo") == 1){
                    Sala sala = new Sala(
                            rs.getInt("rec_id"),
                            rs.getString("rec_nome"),
                            rs.getInt("sala_andar"),
                            rs.getInt("sala_corredor"),
                            rs.getInt("sala_numero"),
                            rs.getInt("tipo"),
                            rs.getInt("recurso_ativo"));

                    recursos.add(sala);
                }else{
                    Equipamento equipamento = new Equipamento(
                            rs.getInt("rec_id"),
                            rs.getString("rec_nome"),
                            rs.getString("equip_marca"),
                            rs.getString("equip_modelo"),
                            rs.getInt("tipo"),
                            rs.getInt("recurso_ativo"));
                    recursos.add(equipamento);
                }
            }
            stm.close();
            rs.close();
            bancoDeDados.encerraConexao();
        }catch (Exception e){
            e.printStackTrace();
        }

        return recursos;
    }
    public List<Recurso> pesquisaRecurso(String nome){
        Banco bancoDeDados = new Banco();
        bancoDeDados.iniciaConexaoComBanco();
        List<Recurso> recursos = new ArrayList<>();
        ResultSet rs = null;
        PreparedStatement stm = null;
        String sql;
        if(nome.length() == 0) {
            sql = "SELECT * FROM hpoa_recursos WHERE recurso_ativo = 1";

        }else{
            sql = "SELECT * FROM hpoa_recursos WHERE rec_nome = ? AND recurso_ativo = 1";

        }
        try {
            stm = bancoDeDados.getConexao().prepareStatement(sql);

            if(nome.length() != 0) {
                stm.setString(1, nome);
            }
            rs = stm.executeQuery();

            while (rs.next()){
                if(rs.getInt("tipo") == 1){
                    Sala sala = new Sala(
                            rs.getInt("rec_id"),
                            rs.getString("rec_nome"),
                            rs.getInt("sala_andar"),
                            rs.getInt("sala_corredor"),
                            rs.getInt("sala_numero"));
                    recursos.add(sala);
                }else{
                    Equipamento equipamento = new Equipamento(
                            rs.getInt("rec_id"),
                            rs.getString("rec_nome"),
                            rs.getString("equip_marca"),
                            rs.getString("equip_modelo"));
                    recursos.add(equipamento);
                }
            }
            stm.close();
            rs.close();
            bancoDeDados.encerraConexao();
        }catch (Exception e){
            e.printStackTrace();
        }

        return recursos;
    }
    public boolean inativarRecurso(int id){
        Banco bancoDeDados = new Banco();
        PreparedStatement stm = null;
        boolean alteracaoFeita = false;
        String sql = "UPDATE hpoa_recursos SET recurso_ativo = 0 WHERE rec_id = ?";

        try {

            bancoDeDados.iniciaConexaoComBanco();
            stm = bancoDeDados.getConexao().prepareStatement(sql);
            stm.setInt(1, id);
            stm.executeUpdate();
            alteracaoFeita = true;
            stm.close();
            bancoDeDados.encerraConexao();

        }catch (Exception e){
            e.printStackTrace();
        }

        return alteracaoFeita;

    }
    public boolean atualizaEquipamento(int id, String nome, String marca, String modelo){

        Banco bancoDeDados = new Banco();
        bancoDeDados.iniciaConexaoComBanco();
        PreparedStatement stm = null;
        boolean alteracaoFeita = false;
        String sql = "UPDATE hpoa_recursos SET rec_nome = ? , equip_marca = ? , equip_modelo = ? WHERE rec_id = ?";

        try {
            stm = bancoDeDados.getConexao().prepareStatement(sql);

            stm.setString(1, nome);
            stm.setString(2, marca);
            stm.setString(3, modelo);
            stm.setInt(4, id);

            stm.executeUpdate();

            alteracaoFeita = true;
            stm.close();

            bancoDeDados.encerraConexao();
        }catch (Exception e){
            e.printStackTrace();
        }

        return alteracaoFeita;
    }

    public boolean atualizaSala(int id, String nome, int andar, int corredor, int numeroSala){

        Banco bancoDeDados = new Banco();
        PreparedStatement stm = null;
        boolean alteracaoFeita = false;
        String sql = "UPDATE hpoa_recursos SET rec_nome = ? , sala_andar = ? , sala_corredor = ? , sala_numero = ? WHERE rec_id = ?";

        try {
            bancoDeDados.iniciaConexaoComBanco();
            stm = bancoDeDados.getConexao().prepareStatement(sql);
            stm.setString(1, nome);
            stm.setInt(2, andar);
            stm.setInt(3, corredor);
            stm.setInt(4, numeroSala);
            stm.setInt(5, id);

            stm.executeUpdate();
            alteracaoFeita = true;
            stm.close();
            bancoDeDados.encerraConexao();
        }catch (Exception e){
            e.printStackTrace();
        }

        return alteracaoFeita;
    }

    /**
     * Apenas insere os dados no banco sem fazer validação.
     * 1º Instanciar um objeto Equiamento
     * 2º executar o método sqlInsereEquipamento()
     * @return TRUE caso dados inseridos
     */
    public String insereEquipamento(String nome, String marca, String modelo) {
        Banco bancoDeDados = new Banco();
        String txtEquipamentoInserido = "";

        try{
            if(bancoDeDados.iniciaConexaoComBanco()) {
                PreparedStatement stm2 = bancoDeDados.getConexao().prepareStatement("INSERT INTO hpoa_recursos VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                stm2.setString(1, nome); //Nome
                stm2.setString(2, "0"); //tipo
                stm2.setString(3, marca); //e_marca
                stm2.setString(4, modelo); //e_modelo
                stm2.setString(5, null); //e_garantia
                stm2.setString(6, null); //sala_andar
                stm2.setString(7, null); //sala_corredor
                stm2.setString(8, null); //sala_numero
                stm2.setString(9,"1"); //recurso ativo
                stm2.executeUpdate();
                stm2.close();
                txtEquipamentoInserido = "Equipamento cadastrado com sucesso !";
            }
            bancoDeDados.encerraConexao();
        }catch (Exception e) {
            txtEquipamentoInserido = "Problema com o banco de dados";
            e.printStackTrace();
        }

        return txtEquipamentoInserido;
    }


    /**
     * Uma sala duplicada é definida quando há uma igualdade em
     * NOME, ANDAR, CORREDOR,
     * @return O número de salas duplicadas.
     */
    public boolean salaDuplicada(String nome, int andar, int corredor, int numeroSala){
        boolean salaDuplicada = false;

        Banco bancoDeDados = new Banco();
        String sql = "SELECT * FROM hpoa_recursos WHERE rec_nome = ? AND sala_andar = ? AND sala_corredor = ? AND sala_numero = ?";
        ResultSet result = null;
        int numeroLinhas = 0;
        try {
            if (bancoDeDados.iniciaConexaoComBanco()) {
                PreparedStatement stm = bancoDeDados.getConexao().prepareStatement(sql);

                stm.setString(1, nome);
                stm.setString(2, Integer.toString(andar));
                stm.setString(3, Integer.toString(corredor));
                stm.setString(4, Integer.toString(numeroSala));
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

    /**
     * Apenas insere no banco de dados, sem validar dados.
     * @return TRUE em caso de inserção válida.
     */
    public String insereSala(String nome, int andar, int corredor, int numeroSala) {
        Banco bancoDeDados = new Banco();
        String txtEquipamentoInserido = "";
        Boolean salaDuplicada = salaDuplicada(nome,andar,corredor,numeroSala);
        if(salaDuplicada == false) {
            try {
                if (bancoDeDados.iniciaConexaoComBanco()) {
                    PreparedStatement stm2 = bancoDeDados.getConexao().prepareStatement("INSERT INTO hpoa_recursos VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                    stm2.setString(1, nome); //Nome
                    stm2.setString(2, "1"); //tipo
                    stm2.setString(3, null); //e_marca
                    stm2.setString(4, null); //e_modelo
                    stm2.setString(5, null); //e_garantia
                    stm2.setString(6, Integer.toString(andar)); //sala_andar
                    stm2.setString(7, Integer.toString(corredor)); //sala_corredor
                    stm2.setString(8, Integer.toString(numeroSala)); //sala_numero
                    stm2.setString(9, "1"); //recurso ativo
                    stm2.executeUpdate();
                    stm2.close();
                    txtEquipamentoInserido = "Sala cadastrada com sucesso";
                }
                bancoDeDados.encerraConexao();
            } catch (Exception e) {
                txtEquipamentoInserido = "Problema com o banco de dados";
                e.printStackTrace();
            }
        }else{
            txtEquipamentoInserido = "Sala já existe no banco de dados";
        }
        return txtEquipamentoInserido;
    }


    public UsuarioAdministrador login(String email, String senha){
        Banco bancoDeDados = new Banco();
        bancoDeDados.iniciaConexaoComBanco();
        ResultSet rs = null;
        PreparedStatement stm = null;
        String sql;
        sql = "SELECT * FROM hpoa_usuarios WHERE usuario_email = ? AND usuario_senha = ?";
        UsuarioAdministrador usuarioAdministrador;
        int idU = 0;
        String nomeU = "";
        String sobrenomeU = "";
        String emailU = "";
        String senhaU = "";
        int tipoU = 0;
        int contador = 0;

        try {
            stm = bancoDeDados.getConexao().prepareStatement(sql);

            stm.setString(1, email);
            stm.setString(2, senha);
            rs = stm.executeQuery();



            while (rs.next()){
                        contador++;
                        idU = rs.getInt("usuario_id");
                        nomeU = rs.getString("usuario_nome");
                        sobrenomeU = rs.getString("usuario_sobrenome");
                        emailU = rs.getString("usuario_email");
                        senhaU = rs.getString("usuario_senha");
                        tipoU = rs.getInt("usuario_tipo");

            }

            stm.close();
            rs.close();
            bancoDeDados.encerraConexao();
            if(contador > 0) {
                return usuarioAdministrador = new UsuarioAdministrador(idU, nomeU, sobrenomeU, emailU, senhaU, tipoU);
            }
        }catch (Exception e){
            e.printStackTrace();

        }

        return null;

    }



}
