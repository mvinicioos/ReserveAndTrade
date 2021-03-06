package Controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;



public class Banco {
    private final String host           = "jdbc:mysql://localhost";
    private final String porta          = "3306";
    private final String nomeDoBanco    = "hpoarecursos";
    private final String usuario        = "root";
    private final String senha          = "root";
    private final String url            = host+"/"+nomeDoBanco;

    private Connection conexao;
    private Statement stm;
    private ResultSet resultado;


    public Connection getConexao(){return this.conexao;}

    //Retorna true quando a conexão com o banco é estabelecida.
    public boolean iniciaConexaoComBanco(){
        boolean conexaoEstabelecida = true;

        try{
            this.conexao = DriverManager.getConnection(host+":"+porta+"/"+nomeDoBanco+"?autoReconnect=true&useSSL=false",
                    usuario,
                    senha);
        }catch (Exception e){
            conexaoEstabelecida = false;
            e.printStackTrace();
        }

        return conexaoEstabelecida;
    }


    public void encerraConexao(){
        try {
            this.conexao.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }



}
