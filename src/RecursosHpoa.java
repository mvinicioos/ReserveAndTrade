import Controller.BancoDAO;
import Model.*;
import View.*;

public class RecursosHpoa {
    public static void main(String[] args) {
        UsuarioAdministrador adm = new UsuarioAdministrador(1,"Marcos", "silva", "msss@hot.com", "123",1);
        GerenciarRecursos ger = new GerenciarRecursos(adm);

        LoginUsuario loginUsuario = new LoginUsuario();

        loginUsuario.mostrar();



        //ger.mostrar();
        //sala.mostrar();
        //equip.mostrar();
        //sala.mostrar();
        //equip.mostrar();



    }
}
