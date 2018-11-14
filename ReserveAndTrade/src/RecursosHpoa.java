import Controller.BancoDAO;
import Model.*;
import View.*;


public class RecursosHpoa {
    public static void main(String[] args) {
        TabelaDeHorarios tabelaDeHorarios = new TabelaDeHorarios();
        LoginUsuario loginUsuario = new LoginUsuario(tabelaDeHorarios);

        loginUsuario.mostrar();
    }
}
