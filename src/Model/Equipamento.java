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


    //---------------------------------------------- [VALIDADORES]

    /**
     * VALIDA:
     * > Verifica se o dado não é vazio.
     * @return TRUE em caso de marca validada
     */
    public boolean validaMarca(){
        boolean marcaValidada = true;
        if (this.getMarca().length() == 0){
            marcaValidada = false;
            System.out.println("Erro ! Insira uma marca válida.");
        }
        return marcaValidada;
    }

    /**
     * VALIDA:
     * > Verifica se o nome não é vazio
     * @return TRUE em caso de modelo validado.
     */
    public boolean validaModelo(){
        boolean modeloValidado = true;
        if(this.getModelo().length() == 0){
            modeloValidado = false;
            System.out.println("Erro ! Insira um modelo válido.");
        }
        return modeloValidado;
    }

    //---------------------------------------------- [SQL]

    /**
     * Apenas insere os dados no banco sem fazer validação.
     * 1º Instanciar um objeto Equiamento
     * 2º executar o método sqlInsereEquipamento()
     * @return TRUE caso dados inseridos
     */
    public boolean sqlInsereEquipamento() {
        Banco bancoDeDados = new Banco();
        boolean equipamentoInserido = true;

        try{
            if(bancoDeDados.iniciaConexaoComBanco()) {
                PreparedStatement stm2 = bancoDeDados.getConexao().prepareStatement("INSERT INTO hpoa_recursos VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
                stm2.setString(1, null); //AUTO_INCREMENT
                stm2.setString(2, this.getNome()); //Nome
                stm2.setString(3, "0"); //tipo
                stm2.setString(4, this.getMarca()); //e_marca
                stm2.setString(5, this.getModelo()); //e_modelo
                stm2.setString(6, null); //e_garantia
                stm2.setString(7, null); //sala_andar
                stm2.setString(8, null); //sala_corredor
                stm2.setString(9, null); //sala_numero
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



}
