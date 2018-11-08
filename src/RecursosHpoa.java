import Controller.BancoDAO;
import Model.*;
import View.*;

public class RecursosHpoa {
    public static void main(String[] args) {
        //CadastroSala sala = new CadastroSala();
        //CadastroEquipamento equip = new CadastroEquipamento();
        UsuarioAdministrador adm = new UsuarioAdministrador(1,"marcos", "silva", "msss@hot.com", "123",1);
        GerenciarRecursos ger = new GerenciarRecursos(adm);

        Equipamento equi = new Equipamento(1,"n","a","a");



        ger.mostrar();
        //sala.mostrar();
        //equip.mostrar();
        //sala.mostrar();
        //equip.mostrar();



    }
}
