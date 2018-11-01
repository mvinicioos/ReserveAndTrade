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

    /***
     * Realiza busca de um recurso pelo nome e imprime seu resultado.
     * @param nomeRecurso
     */
    public void pesquisaRecurso(String nomeRecurso){
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
    }


    public ResultSet pesquisaRecurso(){
        Banco bancoDeDados = new Banco();
        final String recursoAtivo = "1";

        String sql = "SELECT rec_id, rec_nome, tipo, equip_marca, equip_modelo, sala_andar, sala_corredor, sala_numero, recurso_ativo " +
                "       FROM hpoa_recursos " +
                "       WHERE recurso_ativo = ?";
        ResultSet result = null;
        int numeroLinhas = 0;
        String dadoTemporario;
        try {
            if (bancoDeDados.iniciaConexaoComBanco()) {
                PreparedStatement stm = bancoDeDados.getConexao().prepareStatement(sql);
                stm.setString(1, recursoAtivo);
                result = stm.executeQuery();
            }

            bancoDeDados.encerraConexao();
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

}
