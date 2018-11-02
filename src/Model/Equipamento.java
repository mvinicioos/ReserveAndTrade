package Model;

import Controller.Banco;

import java.sql.PreparedStatement;
import java.util.Date;

public class Equipamento extends Recurso {
    private String marca, modelo;
    private  Date garantia;

    public Equipamento(int id, String n, String m, String mo, Date g) {
        super(id, n);
        this.marca = m;
        this.modelo = mo;
        this.garantia = g;
    }

    public Date getGarantia() {
        return garantia;
    }

    public String getMarca() {
        return marca;
    }

    public String getModelo() {
        return modelo;
    }



    //---------------------------------------------- [SQL]

    /**
     * Apenas insere os dados no banco sem fazer validação.
     * 1º Instanciar um objeto Equiamento
     * 2º executar o método sqlInsereEquipamento()
     * @return TRUE caso dados inseridos
     */
    public String sqlInsereEquipamento() {
        Banco bancoDeDados = new Banco();
        String txtEquipamentoInserido = "";

        try{
            if(bancoDeDados.iniciaConexaoComBanco()) {
                PreparedStatement stm2 = bancoDeDados.getConexao().prepareStatement("INSERT INTO hpoa_recursos VALUES (null, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                stm2.setString(1, this.getNome()); //Nome
                stm2.setString(2, "0"); //tipo
                stm2.setString(3, this.getMarca()); //e_marca
                stm2.setString(4, this.getModelo()); //e_modelo
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



}
