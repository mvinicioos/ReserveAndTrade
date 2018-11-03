package Model;

import Controller.Banco;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class Usuario {
    private int identificacao;
    private String nome;
    private String sobrenome;
    private String email;
    private String senha;
    private int tipo;
    private ArrayList<Reserva> reservas;

    public Usuario(int id, String n, String sn, String eml, String pw, int tp) {
        this.identificacao = id;
        this.nome = n;
        this.reservas = new ArrayList<>();
        this.sobrenome = sn;
        this.email = eml;
        this.senha = pw;
        this.tipo = tp;
    }

    public String getNome() {
        return this.nome;
    }

    public ArrayList<Reserva> getReservas() {
        return this.reservas;
    }

    public int getIdentificacao() {
        return identificacao;
    }

    public String getSobrenome(){return this.sobrenome;}

    public String getEmail(){return this.email;}

    public String getSenha(){return this.senha;}

    public int getTipo(){return this.tipo;}

    /**
     * Verifica se o próprio usuário é administrador
     * @return
     */
    public boolean getTipoUsuario(){
        boolean tipoAdministrador = false;

        if(this.getTipo() == 1){
            tipoAdministrador = true;
        }

        return tipoAdministrador;
    }


    /***
     * Realiza busca de um recurso pelo nome e imprime seu resultado.
     * @param nomeRecurso
     */
    public Sala pesquisaRecurso(String nomeRecurso){
        Banco bancoDeDados = new Banco();
        final String recursoAtivo = "1";
        String sql = "SELECT rec_id, rec_nome, tipo, equip_marca, equip_modelo, sala_andar, sala_corredor, sala_numero, recurso_ativo " +
                "       FROM hpoa_recursos " +
                "       WHERE rec_nome = ? AND  recurso_ativo = ?";
        ResultSet result = null;
        int numeroLinhas = 0;
        String dadoTemporario;
        try {
            if (bancoDeDados.iniciaConexaoComBanco()) {
                PreparedStatement stm = bancoDeDados.getConexao().prepareStatement(sql);

                stm.setString(1, nomeRecurso);
                stm.setString(2, recursoAtivo);
                result = stm.executeQuery();
            }

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
            bancoDeDados.encerraConexao();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Retorna um objeto do tipo Sala, ou Equipamento.
     * @param id
     * @return
     */
    public Recurso pesquisaRecurso(int id) {
        Banco bancoDeDados = new Banco();

        Sala sala           = null;
        Equipamento equip   = null;
        boolean tipoSala    = true;

        String sql = "SELECT * " +
                "FROM hpoa_recursos " +
                "WHERE rec_id = ?";
        ResultSet result = null;

        try {
            if (bancoDeDados.iniciaConexaoComBanco()) {

                PreparedStatement stm = bancoDeDados.getConexao().prepareStatement(sql);

                stm.setString(1, Integer.toString(id));
                result = stm.executeQuery();
            }

            //Cria a instancia do objeto de acordo com o id selecionado
            if(result.next()) {
                if (Integer.parseInt(result.getString("tipo")) == 1 && Integer.parseInt(result.getString("recurso_ativo")) == 1) {
                    //Caso seja sala
                    sala = new Sala(
                            Integer.parseInt(result.getString("rec_id")),
                            result.getString("rec_nome"),
                            Integer.parseInt(result.getString("sala_andar")),
                            Integer.parseInt(result.getString("sala_corredor")),
                            Integer.parseInt(result.getString("sala_numero"))
                    );
                } else if (Integer.parseInt(result.getString("tipo")) == 0 && Integer.parseInt(result.getString("recurso_ativo")) == 1) {
                    //Caso seja equipamento
                    tipoSala = false;
                    equip = new Equipamento(
                            Integer.parseInt(result.getString("rec_id")),
                            result.getString("rec_nome"),
                            result.getString("equip_marca"),
                            result.getString("equip_modelo"));
                }

            }


            result.close();
            bancoDeDados.encerraConexao();

        } catch (Exception e) {
            e.printStackTrace();


        }
        if(tipoSala){
            return sala;
        }else{
            return equip;
        }
    }

    /**
     * 1 : Sala
     * 0 : Equipamento
     * -1: Nada encontrado
     * @param id
     * @return
     */
    public int pesquisaRecursoDuplicado(int id) {
        Banco bancoDeDados = new Banco();
        int tipoRecurso = -1;
        String sql = "SELECT *" +
                "       FROM hpoa_recursos " +
                "       WHERE rec_id = ?";
        ResultSet result = null;

        try {
            if (bancoDeDados.iniciaConexaoComBanco()) {
                PreparedStatement stm = bancoDeDados.getConexao().prepareStatement(sql);

                stm.setString(1, Integer.toString(id));
                result = stm.executeQuery();
            }

            //Cria a instancia do objeto de acordo com o id selecionado
            if(result.next()) {
                if(Integer.parseInt(result.getString("recurso_ativo")) == 1) {
                    if (Integer.parseInt(result.getString("tipo")) == 1) {

                        tipoRecurso = 1;

                    } else if (Integer.parseInt(result.getString("tipo")) == 0) {
                        tipoRecurso = 0;

                    }
                }
            }
            result.close();
            bancoDeDados.encerraConexao();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return tipoRecurso;
    }




}
