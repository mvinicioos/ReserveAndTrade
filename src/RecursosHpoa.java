import Model.UsuarioAdministrador;
import View.*;

public class RecursosHpoa {
    public static void main(String[] args) {
        CadastroSala sala = new CadastroSala();
        CadastroEquipamento usuario = new CadastroEquipamento();
        UsuarioAdministrador adm = new UsuarioAdministrador(1,"marcos", "silva", "msss@hot.com", "123",1);

        //usuario.iniciar();
        sala.iniciar();

    }
}
