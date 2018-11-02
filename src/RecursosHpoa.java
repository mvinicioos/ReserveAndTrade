import Model.Equipamento;
import Model.Reserva;
import Model.UsuarioAdministrador;
import View.*;

public class RecursosHpoa {
    public static void main(String[] args) {
        CadastroSala sala = new CadastroSala();
        CadastroEquipamento equip = new CadastroEquipamento();

        UsuarioAdministrador adm = new UsuarioAdministrador(1,"marcos", "silva", "msss@hot.com", "123",1);
        Equipamento equi = new Equipamento(1,"n","a","a");


        //sala.exibirCaixaDialog();
        equip.exibirCaixaDialog();
    }
}
